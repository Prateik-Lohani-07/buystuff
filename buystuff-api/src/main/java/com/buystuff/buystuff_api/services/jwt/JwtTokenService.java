package com.buystuff.buystuff_api.services.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.enums.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public String generateToken(UserPrincipal userPrincipal) {
        Instant now = Instant.now(); 
        Instant expiresAt = now.plus(1, ChronoUnit.DAYS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(userPrincipal.getId().toString())
				.claim("email", userPrincipal.getEmail())
				.claim("role", userPrincipal.getRole().toString())
                .build();

        var encoderParameters = JwtEncoderParameters.from(
			JwsHeader.with(MacAlgorithm.HS256).build(),
			claims
        );

        return encoder.encode(encoderParameters).getTokenValue();
    }

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

    public Long getExpirationTime(String token) {
        Jwt jwt = decoder.decode(token);
        var exp = jwt.getClaimAsInstant("exp");
        return exp.toEpochMilli();
    }

	public boolean isTokenExpired(String token) {
        Jwt jwt = decoder.decode(token);
        var exp = jwt.getClaimAsInstant("exp");
		return exp.isBefore(Instant.now());
	}

    public Role getRole(String token) {
        Jwt jwt = decoder.decode(token);
        return Role.valueOf(jwt.getClaimAsString("role"));
    }

    public UUID getAccountId(String token) {
        Jwt jwt = decoder.decode(token);
        return UUID.fromString(jwt.getClaimAsString("sub"));
    }

    public String getUsername(String token) {
        Jwt jwt = decoder.decode(token);
        return jwt.getClaimAsString("email");
    }
}

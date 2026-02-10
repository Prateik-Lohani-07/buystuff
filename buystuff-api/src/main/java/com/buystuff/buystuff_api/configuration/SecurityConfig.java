package com.buystuff.buystuff_api.configuration;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.enums.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final AuthEntryPointJwt authEntryPointJwt;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	private final UserDetailsService userDetailsService;

	public SecurityConfig(
		AuthEntryPointJwt authEntryPointJwt,
		CustomAccessDeniedHandler customAccessDeniedHandler,
		UserDetailsService userDetailsService
	) {
		this.authEntryPointJwt = authEntryPointJwt;
		this.userDetailsService = userDetailsService;
		this.customAccessDeniedHandler = customAccessDeniedHandler;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
	}

	@Bean
	public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtToUserPrincipalConverter() {
		return jwt -> {
			Collection<GrantedAuthority> authorities = List.of(
				new SimpleGrantedAuthority("ROLE_" + jwt.getClaimAsString("role").toUpperCase())
			);

			UUID id = UUID.fromString(jwt.getSubject());
			String email = jwt.getClaimAsString("email");
			Role role = Role.valueOf(jwt.getClaimAsString("role").toUpperCase());

			UserPrincipal principal = new UserPrincipal(id, email, null, role);

			return new UsernamePasswordAuthenticationToken(principal, jwt.getTokenValue(), authorities);
		};
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> {
					auth
						.requestMatchers(
							"/api/v1/auth/login",
							"/api/v1/auth/signup",
							"/api/v1/products",
							"/api/v1/categories",
							"/api/v1/products/*/reviews"
						).permitAll()
						.requestMatchers(
							"/api/v1/products/*/reviews/*"
						).authenticated()
						.anyRequest().authenticated();
				})
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer(oauth2 -> oauth2
						.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtToUserPrincipalConverter()))
						.authenticationEntryPoint(authEntryPointJwt)
						.accessDeniedHandler(customAccessDeniedHandler))
				.exceptionHandling(ex -> ex
						.authenticationEntryPoint(authEntryPointJwt)
						.accessDeniedHandler(customAccessDeniedHandler))
				.authenticationProvider(authenticationProvider())
				.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}

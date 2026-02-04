package com.buystuff.buystuff_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.buystuff.buystuff_api.entities.Account;
import com.buystuff.buystuff_api.entities.UserPrincipal;
import com.buystuff.buystuff_api.repositories.AccountRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	private final AccountRepository accountRepository;

	public SecurityConfig(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
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
							"/api/v1/products/*",
							"/api/v1/products/*/reviews"
						).permitAll()
						.requestMatchers(
							"/api/v1/products/*/reviews/*"
						).authenticated()
						.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> {
                    oauth2.jwt(Customizer.withDefaults());
                })
                .build();
    }

	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			Account account = accountRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Account not found."));
		
			return new UserPrincipal(account);
		};
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

    @Bean
    public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}
}

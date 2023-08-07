package com.sunx.mysprmdbapp1.configs;

import com.sunx.mysprmdbapp1.security.JwtAuthenticationEntryPoint;
import com.sunx.mysprmdbapp1.security.JwtAuthenticationFilter;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@EnableMethodSecurity
@SecurityScheme(name = "Bear Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class JwtSecurityConfig {

	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthenticationFilter authenticationFilter;

	public JwtSecurityConfig(UserDetailsService userDetailsService1,
			JwtAuthenticationEntryPoint authenticationEntryPoint1, JwtAuthenticationFilter authenticationFilter1) {
		this.userDetailsService = userDetailsService1;
		this.authenticationEntryPoint = authenticationEntryPoint1;
		this.authenticationFilter = authenticationFilter1;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// withDefaults()
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize
				// .requestMatchers(HttpMethod.POST, "/**").permitAll()
				// .requestMatchers(HttpMethod.GET, "/**").permitAll()
				.requestMatchers("/api/v1/auth/signup/**").permitAll()
				.requestMatchers("/api/v1/auth/signin/**").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/restapidocs/**").permitAll()
				.requestMatchers("/graphiql/**").permitAll()
				.requestMatchers("/graphql/**").permitAll()
				.anyRequest().authenticated())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
 
}




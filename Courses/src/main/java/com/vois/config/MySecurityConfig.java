package com.vois.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.vois.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig{

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin=User.withUsername("rsk").password(encoder.encode("rsk")).roles("ADMIN").build();
		UserDetails user1=User.withUsername("user1").password(encoder.encode("user1")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin,user1);
//		return new UserServiceImpl();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		try {
			return http.csrf().disable().authorizeHttpRequests().requestMatchers("/home").permitAll()
					.and().authorizeHttpRequests().requestMatchers("/welcome").authenticated().and().formLogin()
					.and().authorizeHttpRequests().requestMatchers("/courses/**").authenticated().and().formLogin().and().build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}

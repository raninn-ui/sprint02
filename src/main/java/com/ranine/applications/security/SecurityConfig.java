package com.ranine.applications.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	 public PasswordEncoder passwordEncoder () {
	 return new BCryptPasswordEncoder();
	 }

	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception
	{
	 http.authorizeHttpRequests((requests)->requests

	.requestMatchers("/showCreate","/saveApplication").hasAnyAuthority("ADMIN","AGENT")
	.requestMatchers("/modifierApplication","/supprimerApplication").hasAuthority("ADMIN")
	.requestMatchers("/ListeApplications").hasAnyAuthority("ADMIN","AGENT","USER")
	.anyRequest().authenticated())

	 .formLogin(Customizer.withDefaults())
	 .httpBasic(Customizer.withDefaults())
	 
	 .exceptionHandling((exception)->
	 exception.accessDeniedPage("/accessDenied"));
	 
	 return http.build();
	}

	@Bean
	 public InMemoryUserDetailsManager userDetailsService() {
	 PasswordEncoder passwordEncoder = passwordEncoder ();

	 UserDetails admin = User
	 .withUsername("admin")
	 .password(passwordEncoder.encode("123"))
	 .authorities("ADMIN")
	 .build();
	 UserDetails userRanine = User
	 .withUsername("ranine")
	 .password(passwordEncoder.encode("123"))
	 .authorities("AGENT","USER")
	 .build();
	 UserDetails user1 = User
	 .withUsername("user1")
	 .password(passwordEncoder.encode("123"))
	 .authorities("USER")
	 .build();

	 return new InMemoryUserDetailsManager(admin, userRanine,user1);
	 }


}

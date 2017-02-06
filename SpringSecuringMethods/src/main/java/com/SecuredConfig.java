package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

// @EnableGlobalMethodSecurity and GlobalMethodSecurityConfiguration
// require org.springframework.security:spring-security-config dependency 
@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecuredConfig extends GlobalMethodSecurityConfiguration {
	// It should set users' authentication (e.g. inmemory, from-DB, LDAP, or other) 
	// Secured Bean?
	
	// Add more.
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("user_a").password("password").roles("USER")
		.and()
		.withUser("user_b").password("password").roles("USER")
		.and()
		.withUser("user_c").password("password").roles("USER");
	}
	
	@Bean
	public MessageService userService() {
		return new SecuredMessageService();
	}

	
}

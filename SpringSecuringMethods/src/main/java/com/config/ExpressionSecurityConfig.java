package com.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.service.ExpressionSecurityMessageService;
import com.service.MessageService;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

// TODO: annotation, extends
//  - Override for authentication
//  - New bean wiring
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class ExpressionSecurityConfig extends GlobalMethodSecurityConfiguration {

	// Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("user_a").password("password").roles("USER")
		.and()
		.withUser("user_b").password("password").roles("USER")
		.and()
		.withUser("user_c").password("password").roles("USER");		
	}
	
	@Bean
	public MessageService messageService() {
		return new ExpressionSecurityMessageService();
	}
}

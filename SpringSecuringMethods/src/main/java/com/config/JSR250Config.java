package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.service.JSR250MessageService;
import com.service.MessageService;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class JSR250Config extends GlobalMethodSecurityConfiguration {
	
	// Authentication
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("user_a").password("password").roles("USER")
		.and()
		.withUser("user_b").password("password").roles("USER")
		.and()
		.withUser("user_c").password("password").roles("USER");
	}

	// Service Bean
	@Bean
	public MessageService messageService() {
		return new JSR250MessageService();
	}
}

package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.domain.Message;
import com.domain.WebUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SecuredConfig.class)

public class SecuredUserServiceSecurityTest {
	@Autowired
	MessageService messageService;
	
	/**
	 *  This function is to clear all security stuff before any test executions.  
	 */
	@Before
	public void clear() {
		SecurityContextHolder.clearContext();
		System.out.println("SecurityContextHolder clean");
	}
	
	@Test(expected=AuthenticationCredentialsNotFoundException.class)
	public void unathenticatedTest() {
		WebUser from = new WebUser("user_a");
		WebUser to = new WebUser("user_b");
		messageService.sendMessage(new Message(from, to, "ZZZ"));
	}
	
	// It MUST have ROLE_ prefix in both priviliges and in @Secured("ROLE_..."); 
	@Test
	public void role_admin() {
		setupUser("ROLE_ADMIN");
		
		WebUser from = new WebUser("user_a");
		WebUser to = new WebUser("user_b");
		messageService.sendMessage(new Message(from, to, "ZZZ"));
	}
	
	@Test
	public void role_user() {
		setupUser("ROLE_USER");
		
		WebUser from = new WebUser("user_a");
		WebUser to = new WebUser("user_b");
		messageService.sendMessage(new Message(from, to, "ZZZ"));
	}

	public void setupUser(String... priv) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		for (String p : priv) {
			grantedAuthorities.add(new SimpleGrantedAuthority(p));
		}
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken("user_a", "password", grantedAuthorities);
		securityContext.setAuthentication(authentication);
	}
	
}
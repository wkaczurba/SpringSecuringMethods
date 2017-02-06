package com.service;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.domain.Message;

public class JSR250MessageService implements MessageService {

	// @RolesALlowed require javax.annotation:jsr250-api dependency
	@Override
	@RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	public void sendMessage(Message msg) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		
		if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
			!msg.getFrom().getUserName().equals(username)) {
			throw new AccessDeniedException("Access is denied");
		}
		
		System.out.println("sendMessage (JSR-250) executing...");
	}

}

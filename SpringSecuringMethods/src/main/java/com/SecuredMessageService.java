package com;

import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.domain.Message;

public class SecuredMessageService implements MessageService {

	@Override
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public void sendMessage(Message msg) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		
		
		if (!authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) &&
			!msg.getFrom().getUserName().equals(username)) {
			throw new AccessDeniedException("Access is denied");
		}
		
		System.out.println("securedFunction executing...");
	}
}


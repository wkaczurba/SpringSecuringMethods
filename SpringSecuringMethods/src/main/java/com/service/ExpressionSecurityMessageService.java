package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.domain.Message;

public class ExpressionSecurityMessageService implements MessageService {

	@Override
	//@PreAuthorize("hasRole('ROLE_USER') and #msg.from.userName == principal.username or hasRole('ROLE_ADMIN')")
	@PreAuthorize("(hasRole('ROLE_USER') and #msg.from.userName == authentication.name) or hasRole('ROLE_ADMIN')")
	public void sendMessage(Message msg) {
		System.out.println("sendMessage ExpressionSecurityMessageService executing...");		
	}

}

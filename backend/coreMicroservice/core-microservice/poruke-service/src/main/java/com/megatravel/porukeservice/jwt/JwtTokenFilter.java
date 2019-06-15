package com.megatravel.porukeservice.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtTokenFilter extends GenericFilterBean {

	private JwtTokenUtils jwtTokenUtils;

	public JwtTokenFilter(JwtTokenUtils jwtTokenUtils) {
		this.jwtTokenUtils = jwtTokenUtils;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		
		String token = jwtTokenUtils.resolveToken((HttpServletRequest) req);
		if (token != null && jwtTokenUtils.validateToken(token)) {
			Authentication auth = token != null ? jwtTokenUtils.getAuthentication(token) : null;
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(req, res);
	}

}

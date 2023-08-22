package com.transportApp.users.filter;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.transportApp.users.service.MyUserDetailsService;
import com.transportApp.users.util.JWTUtil;



@Component
public class JWTFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private MyUserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader=httpServletRequest.getHeader("Authorization");
		String token=null;
		String userName=null;
		if(authorizationHeader != null&& authorizationHeader.startsWith("Bearer")) {
			token=authorizationHeader.substring(7);
			userName=jwtUtil.extractUsername(token);
		}
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=service.loadUserByUsername(userName);
			if(jwtUtil.validateToken(token, userDetails)) {
				UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		filterChain.doFilter(httpServletRequest, response);
	}

}

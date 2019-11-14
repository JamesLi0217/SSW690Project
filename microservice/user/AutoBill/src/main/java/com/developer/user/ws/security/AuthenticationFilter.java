package com.developer.user.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.developer.user.ws.SpringApplicationContext;
import com.developer.user.ws.service.UserService;
import com.developer.user.ws.service.impl.UserServiceImpl;
import com.developer.user.ws.shared.dto.UserDto;
import com.developer.user.ws.ui.model.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//	@Autowired
//	UserService userService;
//	@Autowired
//    private ApplicationContext applicationContext;
	
	private AuthenticationManager authenticationManager;

	private String contentType;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			contentType = req.getHeader("Accept");

			UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),
					UserLoginRequestModel.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
					creds.getUserEmail(),
					creds.getUserPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String userEmail = ((User) auth.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(userEmail)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//				.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
				.compact();
	        UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
//	        UserService userService = (UserService)SpringApplicationContext.getBean(UserService.class);
	        UserDto userDto = userService.getUser(userEmail);

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	        res.addIntHeader("UserID", userDto.getUserId());
//	        res.addHeader("UserEmail", userDto.getUserEmail());
	}
}

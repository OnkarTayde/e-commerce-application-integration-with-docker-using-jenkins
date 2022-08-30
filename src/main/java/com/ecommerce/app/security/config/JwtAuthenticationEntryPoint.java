package com.ecommerce.app.security.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
This class is used to return a 401 unauthorized error to clients that try to access
a protected resource without proper authentication. It implements Spring Security
AuthenticationEntryPoint interface. In this class we will be creating the HttpResponse
 which should be returned to the user in case of an exception.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		String message;

		if (authException.getCause() != null) {
			message = authException.getCause().toString() + " " + authException.getMessage();
			//System.out.println(message);
		} else {
			message = authException.getMessage();
		}

		byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));

		response.getOutputStream().write(body);
	}

}
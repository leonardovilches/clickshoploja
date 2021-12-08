package com.clickshop.loja.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.clickshop.loja.resources.CredentialResource;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
	
	@Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

		try {
			log.info("JWTAuthenticationFilter START : attemptAuthentication()");
			CredentialResource creds = new ObjectMapper()
	                .readValue(req.getInputStream(), CredentialResource.class);
	
	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());
	        
	        Authentication auth = authenticationManager.authenticate(authToken);
	        log.info("JWTAuthenticationFilter FINISH : attemptAuthentication()");
	        return auth;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		log.info("JWTAuthenticationFilter START : successfulAuthentication()");
		String username = ((UserSS) auth.getPrincipal()).getUsername();
		int ID = ((UserSS) auth.getPrincipal()).getId();
		
		String token = jwtUtil.generateToken(username);
		res.getWriter().append(jsonAccountBody(username, ID, token));
		res.addHeader("Authorization", "Bearer " + token);
		res.addHeader("access-control-expose-headers", "Authorization");
		log.info("JWTAuthenticationFilter FINISH : successfulAuthentication()");
 
	}
	
	private String jsonAccountBody(String email, int id, String token) {
    	return	"{\"ID\": \"" + id + "\", "
    			+ "\"Email\": \"" + email + "\", "
    			+ "\"Token\": \"" + token + "\"}";
    }
	
	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
		 
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
                throws IOException, ServletException {
            response.setStatus(401);
            response.setContentType("application/json"); 
            response.getWriter().append(json());
        }
        
        private String json() {
            long date = new Date().getTime();
            return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
        }
    }
}

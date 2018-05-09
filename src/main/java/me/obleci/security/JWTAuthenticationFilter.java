package me.obleci.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.SignatureAlgorithm;
import me.obleci.dto.UserAuthenticationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import io.jsonwebtoken.Jwts;

import static me.obleci.security.SecurityConstants.*;

/**
 * Created by Daniel on 07.12.2017.
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private AuthenticationManager authenticationManager;

	private static final String AccessControlExposeHeaders = "Access-Control-Expose-Headers";

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		try {
			UserAuthenticationBean cred = new ObjectMapper().readValue(request.getInputStream(), UserAuthenticationBean.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(cred.getUsername(), cred.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws
			IOException,
			ServletException {

		String token = Jwts.builder()
				.setSubject(((User) authResult.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();

		setAccessControlExposeHeaders(response);
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}

	private void setAccessControlExposeHeaders(HttpServletResponse res) {
		String acah = res.getHeader(AccessControlExposeHeaders);
		if ((acah!=null) && (acah.length()>0)) {
			acah = acah+", ";
		} else {
			acah="";
		}

		res.setHeader(AccessControlExposeHeaders, acah + "Authorization");
	}

}

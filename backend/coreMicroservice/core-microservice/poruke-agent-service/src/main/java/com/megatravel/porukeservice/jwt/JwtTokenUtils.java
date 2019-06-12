package com.megatravel.porukeservice.jwt;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.megatravel.porukeservice.model.TipOsobe;
import com.megatravel.porukeservice.security.AgentDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils {
	@Value("${security.jwt.token.secret-key:secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length:604800000}")
	private long validityInMilliseconds = 604800000;	// 7 days in ms

	@Autowired
	private AgentDetails agentDetails;
	
	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String email) {
		List<SimpleGrantedAuthority> tipoviKorisnika = new ArrayList<>();
		tipoviKorisnika.add(new SimpleGrantedAuthority(TipOsobe.AGENT.toString())); 

		Claims claims = Jwts.claims().setSubject(email);
		claims.put("auth", tipoviKorisnika);

		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = agentDetails.loadUserByUsername(getUsername(token));
		if (userDetails==null)
			return null;
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public String getUserToken(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getId();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			System.out.println("validating token...(" + token + ")");
			
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			
			ResponseEntity<Boolean> tokenOK = restTemplate.postForEntity("http://agent-global-service/agent/token", new HttpEntity<>(token), Boolean.class);
			if (tokenOK.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			if (!tokenOK.getBody()) {
				System.out.println("Token is revoked - security warning!");
				return false;
			}
			
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
			//throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
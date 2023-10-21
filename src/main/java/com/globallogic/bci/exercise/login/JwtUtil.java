package com.globallogic.bci.exercise.login;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.globallogic.bci.exercise.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	private final String SECRET_KEY = "mysecretkey";
	private final long ACCESS_TOKEN_VALIDITY = 60 * 60 * 1000;
	private final String TOKEN_HEADER = "Authorization";
	private final String TOKEN_PREFIX = "Bearer ";

	private final JwtParser jwtParser;

	public JwtUtil() {
		this.jwtParser = Jwts.parser().setSigningKey(SECRET_KEY);
	}

	public String createToken(User user) {
		Claims claims = Jwts.claims().setSubject(user.getEmail());
		claims.put("name", user.getName());
		Date tokenCreateTime = new Date();
		Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(ACCESS_TOKEN_VALIDITY));
		return Jwts.builder().setClaims(claims).setExpiration(tokenValidity)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	private Claims parseJwtClaims(String token) {
		return jwtParser.parseClaimsJws(token).getBody();
	}

	public Claims resolveClaims(HttpServletRequest req) {
		try {
			String token = resolveToken(req);
			if (token != null) {
				return parseJwtClaims(token);
			}
			return null;
		} catch (ExpiredJwtException ex) {
			req.setAttribute("expired", ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			req.setAttribute("invalid", ex.getMessage());
			throw ex;
		}
	}

	public String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader(TOKEN_HEADER);
		if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(TOKEN_PREFIX.length());
		}
		return null;
	}

	public boolean validateClaims(Claims claims) throws AuthenticationException {
		return claims.getExpiration().after(new Date());
	}

	public String getEmail(Claims claims) {
		return claims.getSubject();
	}

}

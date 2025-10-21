package edu.txts.sps131025.util;

package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

	private static final String SECRET_KEY = "supersecretkeysupersecretkeysupersecretkey";

	private static Key getSignKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public static String generateToken(String subject, long expirationMillis) {
		return Jwts.builder()
				.setSubject(subject)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public static String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public static boolean isTokenExpired(String token) {
		Date exp = Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
		return exp.before(new Date());
	}
}


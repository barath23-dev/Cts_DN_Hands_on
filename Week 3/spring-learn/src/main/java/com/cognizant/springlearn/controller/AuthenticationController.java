package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("START");
		LOGGER.debug("Authorization Header: {}", authHeader);
		String user = getUser(authHeader);
		String token = generateJwt(user);
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		LOGGER.info("END");
		return map;
	}

	private String getUser(String authHeader) {
		LOGGER.info("START");
		LOGGER.debug("Decoding Header: {}", authHeader);
		String encodedCredentials = authHeader.replace("Basic ", "");
		byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
		String decodedString = new String(decodedBytes);
		String[] split = decodedString.split(":");
		String user = split[0];
		LOGGER.debug("Decoded Username: {}", user);
		LOGGER.info("END");
		return user;
	}

	private String generateJwt(String user) {
		LOGGER.info("START");
		LOGGER.debug("Generating JWT for user: {}", user);
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 1200000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		LOGGER.debug("Generated Token: {}", token);
		LOGGER.info("END");
		return token;
	}

}

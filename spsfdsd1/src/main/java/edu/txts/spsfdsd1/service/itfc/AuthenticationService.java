package edu.txts.spsfdsd1.service.itfc;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import edu.txts.spsfdsd1.dto.request.AuthenticationRequest;
import edu.txts.spsfdsd1.dto.request.IntrospectRequest;
import edu.txts.spsfdsd1.dto.response.AuthenticationResponse;
import edu.txts.spsfdsd1.dto.response.IntrospectResponse;
import edu.txts.spsfdsd1.exception.AppException;
import edu.txts.spsfdsd1.exception.ErrorCode;
import edu.txts.spsfdsd1.repository.UserD2_Repository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
	UserD2_Repository userD2_Repository;
	@NonFinal
	protected static final String SIGNER_KEY = "o4BpJqARotzSbhFEu1FN6qzieX5kpeOI1V8d0nJ4W7awi7cFW7eIriSnHeuT7qsV";

	public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
		var token = introspectRequest.getToken();
		JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
		SignedJWT signedJWT = SignedJWT.parse(token);
		Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
		var verified = signedJWT.verify(verifier);
		return IntrospectResponse.builder()
				.valid(verified && expirationTime.after(new Date()))
				.build();

	}

	public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
		var username = userD2_Repository.findByUsername(authenticationRequest.getUsername()).orElseThrow(
				() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


		boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), username.getPassword());
		if (!authenticated) {
			throw new AppException(ErrorCode.AUTHENTICATED_EXCEPTION);
		}
		var token = generateToken(authenticationRequest.getUsername());
		return AuthenticationResponse.builder()
				.token(token)
				.authenticated(true)
				.build();
	}

	private String generateToken(String username) {
		JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
				.subject(username)
				.issuer("txts.edu")
				.issueTime(new Date())
				.expirationTime(new Date(
						Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
				))
				.claim("author", "Mr.TXT")
				.build();
		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
		JWSObject jwsObject = new JWSObject(jwsHeader, payload);
		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			log.error("Cannot sign JWT object", e);
			throw new RuntimeException(e);
		}
	}

}

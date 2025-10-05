package edu.txts.spsfdsd1.controller;

import com.nimbusds.jose.JOSEException;
import edu.txts.spsfdsd1.dto.ApiResponse;
import edu.txts.spsfdsd1.dto.request.AuthenticationRequest;
import edu.txts.spsfdsd1.dto.request.IntrospectRequest;
import edu.txts.spsfdsd1.dto.response.AuthenticationResponse;
import edu.txts.spsfdsd1.dto.response.IntrospectResponse;
import edu.txts.spsfdsd1.service.itfc.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping({"/auth"})
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
	AuthenticationService authenticationService;

	@PostMapping({"/token"})
	ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
		var result = authenticationService.authenticate(authenticationRequest);
		return ApiResponse.<AuthenticationResponse>builder()
				.result(result)
				.build();
	}

	@PostMapping({"/introspect"})
	ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
		var result = authenticationService.introspect(introspectRequest);
		return ApiResponse.<IntrospectResponse>builder()
				.result(result)
				.build();
	}
}

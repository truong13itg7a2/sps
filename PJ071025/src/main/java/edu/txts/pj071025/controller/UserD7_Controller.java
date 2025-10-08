package edu.txts.pj071025.controller;

import edu.txts.pj071025.dto.request.UserD7_Request;
import edu.txts.pj071025.dto.response.ApiResponse;
import edu.txts.pj071025.dto.response.ResponseWrapper;
import edu.txts.pj071025.dto.response.UserD7_Response;
import edu.txts.pj071025.model.UserD7;
import edu.txts.pj071025.service.ItfcUserD7_Service;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/v1/users"})
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserD7_Controller {
	ItfcUserD7_Service userD7Service;
	ResponseWrapper responseWrapper;

	/*public ResponseEntity<ApiResponse<>> createUser(@RequestBody UserD7_Request userD7_request) {
		ApiResponse<> apiResponse = ApiResponse.builder()
				.code(1000)
				.message(String.valueOf(HttpStatus.CREATED))
				.data(userD7Service.createUser(userD7_request))
				.build();
		return ResponseEntity.ok(apiResponse);*/

				/*ResponseEntity<ApiResponse
				.builder()
				.code(1000)
				.message(String.valueOf(HttpStatus.CREATED))
				.data(userD7Service.createUser(userD7_request))
				.build()>;*/

}

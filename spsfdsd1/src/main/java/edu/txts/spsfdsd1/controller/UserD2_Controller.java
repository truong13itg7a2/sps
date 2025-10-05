package edu.txts.spsfdsd1.controller;

import edu.txts.spsfdsd1.dto.ApiResponse;
import edu.txts.spsfdsd1.dto.request.UserD2Update;
import edu.txts.spsfdsd1.dto.request.UserD2_Create;
import edu.txts.spsfdsd1.dto.response.UserD2_Response;
import edu.txts.spsfdsd1.service.UserD2_Service;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api2/user"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserD2_Controller {
	UserD2_Service userD2_Service;

	@PostMapping
	public ApiResponse<UserD2_Response> createUserD2(@RequestBody @Valid UserD2_Create userD2_create) {
		ApiResponse<UserD2_Response> apiResponse = new ApiResponse<>();
		apiResponse.setResult(userD2_Service.createUserD2(userD2_create));
		return apiResponse;
	}

	@GetMapping({"/users"})
	public List<UserD2_Response> getAllUserD2() {
		return userD2_Service.getAllUserD2();
	}

	@GetMapping({"/{id}"})
	public UserD2_Response getUserD2(@PathVariable String id) {
		return userD2_Service.getUserD2(id);
	}

	@PutMapping({"/{id}"})
	public UserD2_Response updateUserD2(@PathVariable String id, @RequestBody UserD2Update userD2Update) {
		return userD2_Service.updateUserD2(id, userD2Update);
	}

	@DeleteMapping({"/{id}"})
	public String deleteUserD2(@PathVariable String id) {
		userD2_Service.deleteUserD2(id);
		return "Deleted UserD2";
	}
}

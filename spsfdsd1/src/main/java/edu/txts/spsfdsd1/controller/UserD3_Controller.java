package edu.txts.spsfdsd1.controller;

import edu.txts.spsfdsd1.dto.request.UserD3_Request_Create;
import edu.txts.spsfdsd1.dto.request.UserD3_Request_Update;
import edu.txts.spsfdsd1.dto.response.UserD3_Response;
import edu.txts.spsfdsd1.entity.UserD3;
import edu.txts.spsfdsd1.service.impl.UserD3_Service_impl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/v3/users"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor

public class UserD3_Controller {
	UserD3_Service_impl userD3_Service;

	@PostMapping
	public UserD3_Response createUserD3(@RequestBody UserD3_Request_Create userD3_Request_Create) {
		return userD3_Service.createUserD3(userD3_Request_Create);
	}

	@GetMapping
	public List<UserD3> getAllUserD3() {
		return userD3_Service.getAllUserD3v2();
	}

	@GetMapping({"/{id}"})
	public UserD3_Response getUserD3(@PathVariable String id) {
		return userD3_Service.getUserD3(id);
	}

	@PutMapping({"/{id}"})
	public UserD3_Response updateUserD3(@PathVariable String id,
	                                    @RequestBody UserD3_Request_Update userD3_Request_Update) {
		return userD3_Service.updateUserD3(id, userD3_Request_Update);
	}

	@DeleteMapping({"/{id}"})
	public void deleteUserD3(@PathVariable String id) {
		userD3_Service.deleteUserD3(id);
	}

}

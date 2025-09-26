package edu.txts.pj260925.controller;

import edu.txts.pj260925.dto.request.ApiResponse;
import edu.txts.pj260925.dto.request.UserCreate_260925v1;
import edu.txts.pj260925.dto.request.UserUpdate_260925v1;
import edu.txts.pj260925.model.User_260925v1;
import edu.txts.pj260925.service.Itfc_UserService_260925v1;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/apiv1/user"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserController_260925v1 {
	Itfc_UserService_260925v1 userService;
	@PostMapping
	public ApiResponse<User_260925v1> create(@Valid @RequestBody UserCreate_260925v1 user) {
		ApiResponse<User_260925v1> apiResponse = new ApiResponse<>();
		apiResponse.setResult(userService.create_user(user));

		return apiResponse;
	}

	@GetMapping({"/users"})
	public List<User_260925v1> get_users() {
		return userService.get_users();
	}

	@GetMapping({"/{id}"})
	public User_260925v1 get_user(@PathVariable String id) {
		return userService.get_user(id);
	}
	@PutMapping({"/{id}"})
	public User_260925v1 update_user(@PathVariable String id, @RequestBody UserUpdate_260925v1 user) {
		return userService.update_user(id, user);
	}

	@DeleteMapping({"/{id}"})
	public String delete(@PathVariable String id) {
		userService.delete_user(id);
		return "User deleted";

	}


}

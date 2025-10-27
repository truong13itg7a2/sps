package edu.txts.sps271025.v1.controller;

import edu.txts.sps271025.v1.dto.request.UserV1Create;
import edu.txts.sps271025.v1.dto.request.UserV1Update;
import edu.txts.sps271025.v1.dto.response.ApiResponse;
import edu.txts.sps271025.v1.entity.UserV1;
import edu.txts.sps271025.v1.service.UserV1Service;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 15:51
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@RestController
@RequestMapping({"/api/v1/users"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserV1Controller {
	UserV1Service userService;
	@PostMapping
	public ApiResponse<UserV1> createUser(@RequestBody @Valid UserV1Create userV1Create) {
		ApiResponse<UserV1> apiResponse = new ApiResponse<>();
		apiResponse.setData(userService.createUserV1(userV1Create));
		return apiResponse;
	}

	@GetMapping
	public List<UserV1> getAllUsers() {
		return userService.findAllUserV1();
	}

	@GetMapping({"/{id}"})
	public UserV1 getUserById(@PathVariable Long id) {
		return userService.findUserV1ById(id);
	}

	@PutMapping({"/{id}"})
	public UserV1 updateUser(@PathVariable Long id, @RequestBody UserV1Update userV1Update) {
		return userService.updateUserV1(id, userV1Update);
	}

	@DeleteMapping({"/{id}"})
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserV1(id);
		return "User deleted";
	}

	@DeleteMapping
	public String deleteAllUsers() {
		userService.deleteAllUserV1();
		return "All users deleted";
	}
}

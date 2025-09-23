package edu.txts.p220925.controller;

import edu.txts.p220925.dto.ApiResponse;
import edu.txts.p220925.model.User220925;
import edu.txts.p220925.service.UserServiceImplt;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/users"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class User220925Controller {

	UserServiceImplt userService;

	@PostMapping
	public ApiResponse<User220925> addUser(@RequestBody @Valid User220925 user) {
		ApiResponse<User220925> response = new ApiResponse<>();
		response.setResult(userService.addUser(user));
//		response.setCode(200);
//		response.setMessage("User added successfully");
		return response;
	}

	@GetMapping
	public ApiResponse<List<User220925>> getUsers() {

		ApiResponse<List<User220925>> response = new ApiResponse<>();
		response.setResult(userService.getAllUsers());
		return response;
	}

	@GetMapping({"/{id}"})
	public ApiResponse<User220925> getUser(@PathVariable String id) {
		ApiResponse<User220925> response = new ApiResponse<>();
		response.setResult(userService.getUserByID(id));
		return response;
	}

	@DeleteMapping({"/{id}"})
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}

	@PutMapping({"/{id}"})
	public ResponseEntity<User220925> updateUser(@PathVariable String id, @RequestBody User220925 user) {
		return ResponseEntity.ok(userService.updateUser(id, user));
	}

//	@GetMapping({""})
	@DeleteMapping({"/delete-all"})
	public ResponseEntity<String> deleteAllUsers(){
		userService.deleteAllUsers();
		return ResponseEntity.ok("All users deleted successfully");
	}


}

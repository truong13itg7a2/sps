package edu.txts.p220925.controller;

import edu.txts.p220925.model.User220925;
import edu.txts.p220925.service.UserServiceImplt;
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
	public ResponseEntity<User220925> addUser(@RequestBody User220925 user) {
		return ResponseEntity.ok(userService.addUser(user));
	}

	@GetMapping
	public ResponseEntity<List<User220925>> getUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping({"/{id}"})
	public ResponseEntity<User220925> getUser(@PathVariable String id) {
		return ResponseEntity.ok(userService.getUserByID(id));
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


}

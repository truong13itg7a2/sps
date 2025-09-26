package edu.txts.pj260925.controller;

import edu.txts.pj260925.dto.request.UserRequest_260925;
import edu.txts.pj260925.model.User_260925;
import edu.txts.pj260925.service.Itfc_UserService_260925;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api1/user"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController_260925 {
	@Autowired
	Itfc_UserService_260925 userService;
	@PostMapping()
	public User_260925 addUser(@RequestBody UserRequest_260925 user) {
		return userService.addUser(user);
	}

	@GetMapping({"/all-users"})
	public List<User_260925> getUsers() {
		return userService.getAllUsers();
	}

	@GetMapping({"/{id}"})
	public User_260925 getUserById(@PathVariable String id) {
		return userService.getUser(id);
	}

	@PutMapping({"/{id}"})
	public User_260925 updateUser(@PathVariable String id, @RequestBody UserRequest_260925 user) {
		return userService.updateUser(id, user);
	}

	@DeleteMapping({"/{id}"})
	public String deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return "User deleted";
	}
}

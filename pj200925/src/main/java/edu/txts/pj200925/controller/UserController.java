package edu.txts.pj200925.controller;

import edu.txts.pj200925.model.User;
import edu.txts.pj200925.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1"})
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserController {
	@Autowired
	UserService userService;
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(userService.getAllUser());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id){
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		return ResponseEntity.ok(userService.addUser(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
		return ResponseEntity.ok(userService.updateUser(id, user));
	}

	@DeleteMapping("/{id}")
		public String deleteUser(@PathVariable long id){
		 userService.deleteUser(id);
		 return "User deleted";
	}


}

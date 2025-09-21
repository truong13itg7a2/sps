package edu.txts.p210925.controller;

import edu.txts.p210925.dto.request.User210925_v2Request;
import edu.txts.p210925.dto.request.User210925_v2UpdateRequest;
import edu.txts.p210925.model_entity_domain.User210925_v2;
import edu.txts.p210925.service.User210925_v2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v2/users"})
public class User210925_v2Controller {
	@Autowired
	private User210925_v2Service user210925_v2Service;

	@PostMapping
	public User210925_v2 createUser210925_v2(@RequestBody User210925_v2Request userRq) {
		return user210925_v2Service.createUser(userRq);
	}

	@GetMapping
	public List<User210925_v2> getAllUser210925_v2() {
		return user210925_v2Service.getUsers();
	}

	@GetMapping({"/{id}"})
	public User210925_v2 getUser210925_v2(@PathVariable String id) {
		return user210925_v2Service.getUser(id);
	}

	@DeleteMapping({"/{id}"})
	public String deleteUser210925_v2(@PathVariable String id) {
		user210925_v2Service.deleteUser(id);
		return "User has been deleted";
	}

	@PutMapping({"/{id}"})
	public User210925_v2 updateUser210925_v2(@PathVariable String id, @RequestBody User210925_v2UpdateRequest userRq) {
		return user210925_v2Service.updateUser(id, userRq);
	}




}

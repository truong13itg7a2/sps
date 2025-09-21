package edu.txts.p210925.controller;

import edu.txts.p210925.model_entity_domain.User210925;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//return user1
//cmt2

@RestController
@RequestMapping({"/api/v1/users"})
public class User210925_Controller {

	@GetMapping
	public String hello() {
		User210925 user = User210925.builder()
				.id(123L)
				.name("Tran Xuan Truong Pro Max")
				.age(23)
				.weight(78.9)
				.build();
		return "Hello World" + user.toString();
	}


}

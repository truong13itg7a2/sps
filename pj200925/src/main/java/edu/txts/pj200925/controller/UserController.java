package edu.txts.pj200925.controller;

import edu.txts.pj200925.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1"})
public class UserController {

	@GetMapping
	public String getUser(){
		User user = User.builder().id(123L).name("Au Duong Vo Dich99").age(18).income(8.9)
		.build();
		return "Hello World" + user.toString();
//		return "Hello World";
	}
}

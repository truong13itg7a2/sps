package edu.txts.p210925.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringboot_Controller {
	@GetMapping({"/hello"})
	public String hello() {
		return "Hello Springboot!";
	}
}

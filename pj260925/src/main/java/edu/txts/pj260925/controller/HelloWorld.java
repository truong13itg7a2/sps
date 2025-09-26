package edu.txts.pj260925.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/hello"})
public class HelloWorld {
	@GetMapping
	public String hello() {
		return "Hello World";
	}
}

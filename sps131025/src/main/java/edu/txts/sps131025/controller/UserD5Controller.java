package edu.txts.sps131025.controller;

import edu.txts.sps131025.dto.request.UserD5Create;
import edu.txts.sps131025.dto.request.UserD5Update;
import edu.txts.sps131025.dto.response.UserD5Response;
import edu.txts.sps131025.entity.UserD5;
import edu.txts.sps131025.service.impl.UserD5ServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"apid5/users"})
@RequiredArgsConstructor
@Validated
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserD5Controller {
	UserD5ServiceImpl userD5Service;

	@PostMapping
	public UserD5Response createUserD5(@RequestBody UserD5Create userD5Create) {
		return userD5Service.createUserD5(userD5Create);
	}

	@PutMapping({"/{id}"})
	public UserD5Response updateUserD5(@PathVariable String id, @RequestBody UserD5Update userD5Update) {
		return userD5Service.updateUserD5(id, userD5Update);
	}

	@GetMapping
	public List<UserD5> getAllUserD5() {
		return userD5Service.getAllUserD5s();
	}

	@GetMapping({"/{id}"})
	public UserD5 getUserD5ById(@PathVariable String id) {
		return userD5Service.getUserD5ById(id);
	}

	@DeleteMapping({"/{id}"})
	public String deleteUserD5(@PathVariable String id) {
		userD5Service.deleteUserD5ById(id);
		return "User " + id + " has been deleted...";
	}

	@DeleteMapping({"/delete-all"})
	public String deleteAllUserD5() {
		userD5Service.deleteAllUserD5s();
		return "All deleted...";
	}

}

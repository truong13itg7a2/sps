package edu.txts.p210925.service;

import edu.txts.p210925.dto.request.User210925_v2Request;
import edu.txts.p210925.dto.request.User210925_v2UpdateRequest;
import edu.txts.p210925.model_entity_domain.User210925_v2;
import edu.txts.p210925.repository.User210925_v2Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User210925_v2Service {
	@Autowired
	private User210925_v2Repo user210925_v2Repo;

	public User210925_v2 createUser(User210925_v2Request userRq) {
		User210925_v2 user = new User210925_v2();
		user.setUsername(userRq.getUsername());
		user.setPassword(userRq.getPassword());
		user.setFirstName(userRq.getFirstName());
		user.setLastName(userRq.getLastName());
		user.setDob(userRq.getDob());
		return user210925_v2Repo.save(user);
	}

	public User210925_v2 getUser(String id) {
		return user210925_v2Repo.findById(id).orElseThrow(() -> new RuntimeException("User with id " + id + " not " +
				"found"));
	}

	public List<User210925_v2> getUsers() {
		return user210925_v2Repo.findAll();
	}

	public void deleteUser(String id) {
		user210925_v2Repo.deleteById(id);
	}

	public User210925_v2 updateUser(String id, User210925_v2UpdateRequest userRq) {
		User210925_v2 user = getUser(id);
//		user.setUsername(userRq.getUsername());
		user.setPassword(userRq.getPassword());
		user.setFirstName(userRq.getFirstName());
		user.setLastName(userRq.getLastName());
		user.setDob(userRq.getDob());
		return user210925_v2Repo.save(user);
	}

}

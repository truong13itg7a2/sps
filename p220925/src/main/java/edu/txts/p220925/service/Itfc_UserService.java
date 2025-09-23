package edu.txts.p220925.service;

import edu.txts.p220925.model.User220925;

import java.util.List;

public interface Itfc_UserService {
	User220925 getUserByID(String id);
	List<User220925> getAllUsers();
	User220925 addUser(User220925 user);
	User220925 updateUser(String id, User220925 user);
	void deleteUser(String id);
	void deleteAllUsers();
}

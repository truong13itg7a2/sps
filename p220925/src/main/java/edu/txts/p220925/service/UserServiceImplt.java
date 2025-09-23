package edu.txts.p220925.service;

import edu.txts.p220925.exception.AppException;
import edu.txts.p220925.exception.ErrorCode;
import edu.txts.p220925.model.User220925;
import edu.txts.p220925.repository.User220925Repo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImplt implements Itfc_UserService{
	User220925Repo user220925Repo;
	/**
	 * @param id 
	 * @return
	 */
	@Override
	public User220925 getUserByID(String id) {
		return user220925Repo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
	}

	/**
	 * @return 
	 */
	@Override
	public List<User220925> getAllUsers() {
		return user220925Repo.findAll();
	}

	/**
	 * @param user 
	 * @return
	 */
	@Override
	public User220925 addUser(User220925 user) {
		if (user220925Repo.existsUser220925ByUsername(user.getUsername())) {
			throw new AppException(ErrorCode.USER_EXISTED);
		}
		return user220925Repo.save(user);
	}

	/**
	 * @param id 
	 * @param user
	 * @return
	 */
	@Override
	public User220925 updateUser(String id, User220925 user) {
		User220925 user220925 = getUserByID(id);
		user220925.setUsername(user.getUsername());
		user220925.setPassword(user.getPassword());
		user220925.setFirstName(user.getFirstName());
		user220925.setLastName(user.getLastName());
		user220925.setAge(user.getAge());
		user220925.setDateOfBirth(user.getDateOfBirth());
		user220925.setIncome(user.getIncome());
		return user220925Repo.save(user220925);
	}

	/**
	 * @param id 
	 */
	@Override
	public void deleteUser(String id) {
//		User220925 user220925 = getUserByID(id);
		user220925Repo.deleteById(id);
	}

	/**
	 *
	 */
	@Override
	public void deleteAllUsers() {
		user220925Repo.deleteAll();
	}
}

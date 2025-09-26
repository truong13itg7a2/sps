package edu.txts.pj260925.repository;

import edu.txts.pj260925.model.User_260925;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo_260925 extends JpaRepository<User_260925, String> {
	User_260925 findByIdContainingIgnoreCase(String id);

	List<User_260925> id(String id);

	boolean findByUsername(String username);
}

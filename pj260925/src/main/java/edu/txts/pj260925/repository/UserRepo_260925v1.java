package edu.txts.pj260925.repository;

import edu.txts.pj260925.model.User_260925v1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo_260925v1 extends JpaRepository<User_260925v1, String> {
	boolean existsByUsername(String username);
}

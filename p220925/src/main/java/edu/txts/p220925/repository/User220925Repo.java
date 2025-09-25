package edu.txts.p220925.repository;

import edu.txts.p220925.model.User220925;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface User220925Repo extends JpaRepository<User220925, String> {
	boolean existsUser220925ByUsername(String username);

//	Optional<User220925> findUser220925ByUsername(String username);
	Optional<User220925> findByUsername(@Size(min = 6, message = "USERNAME_INVALID") String username);
}

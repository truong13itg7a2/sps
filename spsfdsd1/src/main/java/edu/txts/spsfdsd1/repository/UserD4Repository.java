package edu.txts.spsfdsd1.repository;

import edu.txts.spsfdsd1.entity.UserD4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserD4Repository extends JpaRepository<UserD4, String> {
	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	UserD4 findByUsernameOrEmail(String username, String email);
}

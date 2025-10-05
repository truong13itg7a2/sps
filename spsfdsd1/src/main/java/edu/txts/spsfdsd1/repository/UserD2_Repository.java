package edu.txts.spsfdsd1.repository;

import edu.txts.spsfdsd1.entity.UserD2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserD2_Repository extends JpaRepository<UserD2, String> {
	boolean existsByUsername(String username);

	Optional<UserD2> findByUsername(String username);
}

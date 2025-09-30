package edu.txts.spsfdsd1.repository;

import edu.txts.spsfdsd1.entity.UserD1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserD1_Repository extends JpaRepository<UserD1, String> {
	boolean existsByUsername(String username);
}

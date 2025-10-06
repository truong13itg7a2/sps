package edu.txts.spsfdsd1.repository;

import edu.txts.spsfdsd1.entity.UserD3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserD3_Repository extends JpaRepository<UserD3, String> {
	boolean existsByUsername(String username);
}

package edu.txts.pj071025.repository;

import edu.txts.pj071025.model.UserD7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserD7_Repository extends JpaRepository<UserD7, String> {
	UserD7 findByUsername(String username);
	boolean existsByUsername(String username);
}

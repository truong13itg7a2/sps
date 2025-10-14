package edu.txts.sps131025.repository;

import edu.txts.sps131025.model.UserD5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserD5Repository extends JpaRepository<UserD5, String> {
	boolean existsByUsername(String username);
}

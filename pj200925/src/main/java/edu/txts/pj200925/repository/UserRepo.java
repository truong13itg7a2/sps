package edu.txts.pj200925.repository;

import edu.txts.pj200925.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}

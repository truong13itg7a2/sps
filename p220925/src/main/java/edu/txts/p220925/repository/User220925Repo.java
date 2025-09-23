package edu.txts.p220925.repository;

import edu.txts.p220925.model.User220925;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User220925Repo extends JpaRepository<User220925, String> {
}

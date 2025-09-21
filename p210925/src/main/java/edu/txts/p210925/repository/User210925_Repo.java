package edu.txts.p210925.repository;

import edu.txts.p210925.model.User210925;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User210925_Repo extends JpaRepository<User210925,Long> {
}

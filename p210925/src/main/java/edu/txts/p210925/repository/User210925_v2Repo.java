package edu.txts.p210925.repository;

import edu.txts.p210925.model_entity_domain.User210925_v2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User210925_v2Repo extends JpaRepository<User210925_v2, String> {
	User210925_v2 findUser210925_v2ById(String id);
}

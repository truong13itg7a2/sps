package edu.txts.sps131025.repository;

import edu.txts.sps131025.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, CustomUserRepository{
	@Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.active = true")
	Optional<UserEntity> findActiveUser(@Param("username") String username);

	@Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
	Optional<UserEntity> findByEmailNative(String email);


	Optional<UserEntity> findByUsername(String username);
	boolean existsByEmail(String email);
}

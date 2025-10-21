package edu.txts.sps131025.repository;

import edu.txts.sps131025.entity.UserD5;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserD5Repository extends JpaRepository<UserD5, String> {
	// Check existence
	boolean existsByUsername(String username);
	boolean existsByUsernameIgnoreCase(String username);
	boolean existsByEmail(String email);
	boolean existsByEmailIgnoreCase(String email);
	boolean existsByUsernameOrEmail(String username, String email);

	// Basic findByUsername
	@Override
	Optional<UserD5> findById(String s);
	Optional<UserD5> findByEmail(String email);

	@Override
	<S extends UserD5> S save(S entity);
	// Find with specific conditions

	Optional<UserD5> findByUsernameIgnoreCase(String username);
	Optional<UserD5> findByUsernameAndEnabledTrue(String username);
	Optional<UserD5> findByUsernameAndEnabled(String username, boolean enabled);

	// Find multiple users with username pattern
	List<UserD5> findByUsernameContaining(String username);
	List<UserD5> findByUsernameContainingIgnoreCase(String username);
	List<UserD5> findByUsernameStartingWith(String prefix);
	List<UserD5> findByUsernameEndingWith(String suffix);

	// With pagination
	Page<UserD5> findByUsernameContaining(String username, Pageable pageable);

	// Custom query with @Query
	@Query("SELECT u FROM User u WHERE u.username = :username AND u.enabled = true")
	Optional<User> findActiveUserByUsername(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
	Optional<User> findUserByUsernameCaseInsensitive(@Param("username") String username);

	// Projection - chỉ lấy một số field
	@Query("SELECT u.username, u.email, u.enabled FROM User u WHERE u.username = :username")
	Optional<UserProjection> findUserProjectionByUsername(@Param("username") String username);

	// Native query
	@Query(value = "SELECT * FROM users WHERE username = :username AND enabled = true", nativeQuery = true)
	Optional<User> findActiveUserByUsernameNative(@Param("username") String username);
}


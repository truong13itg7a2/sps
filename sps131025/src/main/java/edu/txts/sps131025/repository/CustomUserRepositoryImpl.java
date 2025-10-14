package edu.txts.sps131025.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Custom repository interface
public interface CustomUserRepository {
	Optional<User> findByUsernameWithRoles(String username);
	Optional<User> findByUsernameWithRolesAndPermissions(String username);
	List<User> findSimilarUsernames(String username, double similarityThreshold);
}

// Custom repository implementation
@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

	private final EntityManager entityManager;

	@Override
	public Optional<User> findByUsernameWithRoles(String username) {
		String jpql = """
            SELECT u FROM User u 
            LEFT JOIN FETCH u.roles 
            WHERE u.username = :username
            """;

		try {
			User user = entityManager.createQuery(jpql, User.class)
					.setParameter("username", username)
					.getSingleResult();
			return Optional.ofNullable(user);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findByUsernameWithRolesAndPermissions(String username) {
		String jpql = """
            SELECT DISTINCT u FROM User u 
            LEFT JOIN FETCH u.roles r 
            LEFT JOIN FETCH r.permissions 
            WHERE u.username = :username
            """;

		try {
			User user = entityManager.createQuery(jpql, User.class)
					.setParameter("username", username)
					.getSingleResult();
			return Optional.ofNullable(user);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<User> findSimilarUsernames(String username, double similarityThreshold) {
		// Sử dụng database function cho similarity search (PostgreSQL)
		String sql = """
            SELECT *, similarity(username, :username) as score 
            FROM users 
            WHERE similarity(username, :username) > :threshold 
            ORDER BY score DESC
            """;

		return entityManager.createNativeQuery(sql, User.class)
				.setParameter("username", username)
				.setParameter("threshold", similarityThreshold)
				.getResultList();
	}
}

// Extended repository interface
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
	// Các method từ Spring Data JPA và custom
	Optional<User> findByUsername(String username);
}

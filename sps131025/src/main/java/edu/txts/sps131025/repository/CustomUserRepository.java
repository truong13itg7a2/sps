package edu.txts.sps131025.repository;

public interface CustomUserRepository {
	Optional<User> findByUsernameWithRoles(String username);
	Optional<User> findByUsernameWithRolesAndPermissions(String username);
	List<User> findSimilarUsernames(String username, double similarityThreshold);
}

package edu.txts.sps131025.repository;

import edu.txts.sps131025.entity.UserEntity;

import java.util.List;

public interface CustomUserRepository {
	Optional<User> findByUsernameWithRoles(String username);
	Optional<User> findByUsernameWithRolesAndPermissions(String username);
	List<UserEntity> findActiveUsersCustom();
	List<User> findSimilarUsernames(String username, double similarityThreshold);
}

package edu.txts.sps131025.service;

import edu.txts.sps131025.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserD5ServiceV2 {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final CacheManager cacheManager;

	private static final String USER_CACHE = "users";

	@Cacheable(value = USER_CACHE, key = "#username", unless = "#result == null")
	@Transactional(readOnly = true)
	public User findByUsername(String username) {
		log.debug("Finding user by username: {}", username);

		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
	}

	@Transactional(readOnly = true)
	public Optional<User> findByUsernameOptional(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public User findByUsernameWithRoles(String username) {
		return userRepository.findByUsernameWithRoles(username)
				.orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
	}

	@Transactional(readOnly = true)
	public User findActiveUserByUsername(String username) {
		return userRepository.findByUsernameAndEnabledTrue(username)
				.orElseThrow(() -> new UserNotActiveException("User is not active or not found: " + username));
	}

	@Transactional(readOnly = true)
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Transactional(readOnly = true)
	public UserProfileResponse getUserProfile(String username) {
		User user = findByUsernameWithRoles(username);
		return UserProfileResponse.from(user);
	}

	public User createUser(CreateUserRequest request) {
		// Validate username uniqueness
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new UsernameAlreadyExistsException("Username already exists: " + request.getUsername());
		}

		User user = User.builder()
				.username(request.getUsername())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.enabled(true)
				.build();

		User savedUser = userRepository.save(user);

		// Clear cache
		evictUserCache(savedUser.getUsername());

		log.info("User created successfully: {}", savedUser.getUsername());
		return savedUser;
	}

	public void updateUser(String username, UpdateUserRequest request) {
		User user = findByUsername(username);

		if (request.getEmail() != null) {
			user.setEmail(request.getEmail());
		}

		if (request.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(request.getPassword()));
		}

		userRepository.save(user);
		evictUserCache(username);

		log.info("User updated successfully: {}", username);
	}

	public void deleteUser(String username) {
		User user = findByUsername(username);
		userRepository.delete(user);
		evictUserCache(username);

		log.info("User deleted successfully: {}", username);
	}

	@Transactional(readOnly = true)
	public Page<UserResponse> searchUsers(String keyword, Pageable pageable) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return userRepository.findAll(pageable)
					.map(UserResponse::from);
		}

		return userRepository.findByUsernameContaining(keyword, pageable)
				.map(UserResponse::from);
	}

	@Transactional(readOnly = true)
	public List<User> findSimilarUsernames(String username) {
		return userRepository.findSimilarUsernames(username, 0.3);
	}

	private void evictUserCache(String username) {
		Cache cache = cacheManager.getCache(USER_CACHE);
		if (cache != null) {
			cache.evict(username);
		}
	}
}

// Custom Exceptions
class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
	}
}

class UsernameAlreadyExistsException extends RuntimeException {
	public UsernameAlreadyExistsException(String message) {
		super(message);
	}
}

class UserNotActiveException extends RuntimeException {
	public UserNotActiveException(String message) {
		super(message);
	}
}

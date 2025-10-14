package edu.txts.sps131025.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD5 {
	@Id
			@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	@Column(unique = true, nullable = false)
	String username;

	@Column(nullable = false)
	String password;

	@Column(nullable = false)
	String fullName;

	@Column(nullable = false)
	String email;

	@Column(nullable = false)
	String phone;

	@Column(nullable = false)
	LocalDate birthday;
	private boolean enabled;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserD5Role role;

	@Column(name = "password_updated_at")
	private LocalDateTime passwordUpdatedAt;

	@Column(name = "failed_login_attempts")
	private int failedLoginAttempts;

	@Column(name = "account_locked")
	private boolean accountLocked;

	// Factory method for creating new user
	public static User create(String email, String rawPassword, PasswordEncoder encoder, UserRole role) {
		User user = new User();
		user.email = email;
		user.encodePassword(rawPassword, encoder);
		user.role = role;
		user.passwordUpdatedAt = LocalDateTime.now();
		return user;
	}

	// Encode password
	public void encodePassword(String rawPassword, PasswordEncoder encoder) {
		if (rawPassword == null || rawPassword.trim().isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty");
		}
		this.password = encoder.encode(rawPassword);
		this.passwordUpdatedAt = LocalDateTime.now();
	}

	// Verify password
	public boolean verifyPassword(String rawPassword, PasswordEncoder encoder) {
		return encoder.matches(rawPassword, this.password);
	}

	// Change password
	public void changePassword(String oldPassword, String newPassword, PasswordEncoder encoder) {
		if (!verifyPassword(oldPassword, encoder)) {
			throw new InvalidPasswordException("Current password is incorrect");
		}
		validateNewPassword(newPassword);
		encodePassword(newPassword, encoder);
		resetFailedLoginAttempts();
	}

	// Validate password strength
	private void validateNewPassword(String password) {
		if (password.length() < 8) {
			throw new InvalidPasswordException("Password must be at least 8 characters long");
		}

		// Add more validation rules as needed
		if (!password.matches(".*[A-Z].*")) {
			throw new InvalidPasswordException("Password must contain at least one uppercase letter");
		}

		if (!password.matches(".*[a-z].*")) {
			throw new InvalidPasswordException("Password must contain at least one lowercase letter");
		}

		if (!password.matches(".*\\d.*")) {
			throw new InvalidPasswordException("Password must contain at least one digit");
		}
	}

	// Security methods
	public void recordFailedLoginAttempt() {
		this.failedLoginAttempts++;
		if (this.failedLoginAttempts >= 5) {
			this.accountLocked = true;
		}
	}

	public void resetFailedLoginAttempts() {
		this.failedLoginAttempts = 0;
		this.accountLocked = false;
	}

	public boolean isPasswordExpired() {
		return passwordUpdatedAt.isBefore(LocalDateTime.now().minusMonths(3));
	}
}

// Custom exception
class InvalidPasswordException extends RuntimeException {
	public InvalidPasswordException(String message) {
		super(message);
	}
}





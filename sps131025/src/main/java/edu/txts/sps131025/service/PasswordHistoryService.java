package edu.txts.sps131025.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PasswordHistoryService {

	private final PasswordHistoryRepository passwordHistoryRepository;
	private final PasswordEncoder passwordEncoder;

	public void recordPasswordChange(Long userId, String encodedPassword) {
		PasswordHistory history = PasswordHistory.builder()
				.userId(userId)
				.encodedPassword(encodedPassword)
				.changedAt(LocalDateTime.now())
				.build();

		passwordHistoryRepository.save(history);

		// Keep only last 5 passwords
		passwordHistoryRepository.deleteOldPasswords(userId, 5);
	}

	public boolean isPasswordInHistory(Long userId, String newRawPassword) {
		List<PasswordHistory> recentPasswords =
				passwordHistoryRepository.findTop5ByUserIdOrderByChangedAtDesc(userId);

		return recentPasswords.stream()
				.anyMatch(history -> passwordEncoder.matches(newRawPassword, history.getEncodedPassword()));
	}

	public boolean validatePasswordNotInHistory(Long userId, String newRawPassword) {
		return !isPasswordInHistory(userId, newRawPassword);
	}
}

@Entity
@Table(name = "password_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "encoded_password", nullable = false, length = 255)
	private String encodedPassword;

	@Column(name = "changed_at", nullable = false)
	private LocalDateTime changedAt;

	@Builder
	public PasswordHistory(Long userId, String encodedPassword, LocalDateTime changedAt) {
		this.userId = userId;
		this.encodedPassword = encodedPassword;
		this.changedAt = changedAt;
	}
}

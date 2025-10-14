package edu.txts.sps131025.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

		if (!user.isActive()) {
			throw new UserAccountLockedException("User account is locked");
		}

		if (user.isPasswordExpired()) {
			throw new PasswordExpiredException("Password has expired");
		}

		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.authorities(user.getRole().getAuthorities())
				.accountExpired(false)
				.accountLocked(user.isAccountLocked())
				.credentialsExpired(user.isPasswordExpired())
				.disabled(!user.isActive())
				.build();
	}
}

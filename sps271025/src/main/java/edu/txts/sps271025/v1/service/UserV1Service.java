package edu.txts.sps271025.v1.service;

import edu.txts.sps271025.v1.dto.request.UserV1Create;
import edu.txts.sps271025.v1.dto.request.UserV1Update;
import edu.txts.sps271025.v1.entity.UserV1;
import edu.txts.sps271025.v1.exception.AppException;
import edu.txts.sps271025.v1.exception.ErrorCode;
import edu.txts.sps271025.v1.repository.UserV1Repository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 16:10
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserV1Service {
	UserV1Repository userV1Repository;

	public UserV1 createUserV1(UserV1Create request) {
		if (userV1Repository.existsByUsername(request.getUsername())) {
			throw new AppException(ErrorCode.USER_EXISTED);
		}
		UserV1 user = new UserV1();
		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setFullName(request.getFullName());
		user.setBirthDate(request.getBirthDate());
		return userV1Repository.save(user);

	}

	public List<UserV1> findAllUserV1() {
		return userV1Repository.findAll();
	}

	public UserV1 findUserV1ById(Long id) {
		return userV1Repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public UserV1 updateUserV1(Long id, UserV1Update userV1Update){
		UserV1 user = findUserV1ById(id);
		user.setPassword(userV1Update.getPassword());
		user.setFullName(userV1Update.getFullName());
		user.setBirthDate(userV1Update.getBirthDate());
		return userV1Repository.save(user);
	}

	public void deleteUserV1(Long id) {
		userV1Repository.deleteById(id);
	}

	public void deleteAllUserV1() {
		userV1Repository.deleteAll();
	}
}

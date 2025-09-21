package edu.txts.p210925.service;

import edu.txts.p210925.repository.User210925_Repo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User210925_Service {
	User210925_Repo repo;
}

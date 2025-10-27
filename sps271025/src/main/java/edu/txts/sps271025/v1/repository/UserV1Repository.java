package edu.txts.sps271025.v1.repository;

import edu.txts.sps271025.v1.entity.UserV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 16:06
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/
@Repository
public interface UserV1Repository extends JpaRepository<UserV1, Long> {
	boolean existsByUsername(String username);
}

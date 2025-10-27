package edu.txts.sps271025.v1.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/*******************************************************************************
 * CODE NÀY VIẾT RA KHÔNG PHẢI ĐỂ HIỂU – MÀ ĐỂ KHIẾN NGƯỜI KHÁC KHÓC THÉT.
 * Author   : TXTsG7
 * Created  : 27 Oct 2025 16:31
 * Org      : Republic of Bugs - Where Bugs Are Born!!!
 ******************************************************************************/

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserV1Update {
    String password;
    String fullName;
    LocalDate birthDate;
}

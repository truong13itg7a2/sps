package edu.txts.pj071025.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserD7 {
	@Id
			@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate dob;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ?
				((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
				((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() :
				this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		UserD7 userD7 = (UserD7) o;
		return getId() != null && Objects.equals(getId(), userD7.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ?
				((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() :
				getClass().hashCode();
	}
}

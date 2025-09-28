package edu.txts.pj260925.model;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
public class User_260925v1 {
	@Id
			@GeneratedValue(strategy = GenerationType.UUID)
	String id;
	String username;
	String password;
	String firstName;
	String lastName;
	LocalDate birthday;

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		User_260925v1 that = (User_260925v1) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}

package edu.txts.sps131025.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@MappedSuperclass → không phải bảng thật trong DB, nhưng các entity kế thừa sẽ có các cột này.*/
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

	@Column(name = "created_at")
	private java.time.LocalDateTime createdAt;

	@Column(name = "updated_at")
	private java.time.LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = java.time.LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = java.time.LocalDateTime.now();
	}
}


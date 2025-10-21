package edu.txts.sps131025.repository;

import edu.txts.sps131025.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	Page<ProductEntity> findAll(Pageable pageable);
	List<ProductEntity> findByNameContainingIgnoreCase(String name);
}

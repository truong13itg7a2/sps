package edu.txts.sps131025.mapper;

public interface ProductMapper {
	Product toEntity(ProductRequest request);
	ProductResponse toResponse(Product entity);
}

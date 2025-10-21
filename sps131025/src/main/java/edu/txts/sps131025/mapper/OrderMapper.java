package edu.txts.sps131025.mapper;

public interface OrderMapper {
	@Mapping(target = "userId", source = "user.id")
	OrderResponse toResponse(Order entity);

	@Mapping(target = "user", ignore = true) // ví dụ không map ngược lại
	Order toEntity(OrderRequest request);
}

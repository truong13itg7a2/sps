package edu.txts.pj071025.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResponse<T> {
	private final List<T> content;
	private final PaginationInfo pagination;

	public static <T> PageResponse<T> of(List<T> content, Page<?> page) {
		return PageResponse.<T>builder()
				.content(content)
				.pagination(PaginationInfo.from(page))
				.build();
	}

	public static <T> PageResponse<T> of(List<T> content, int page, int size, long totalElements) {
		return PageResponse.<T>builder()
				.content(content)
				.pagination(PaginationInfo.of(page, size, totalElements))
				.build();
	}

	@Getter
	@Builder
	@AllArgsConstructor
	public static class PaginationInfo {
		private final int currentPage;
		private final int pageSize;
		private final long totalElements;
		private final int totalPages;
		private final boolean first;
		private final boolean last;

		public static PaginationInfo from(Page<?> page) {
			return PaginationInfo.builder()
					.currentPage(page.getNumber())
					.pageSize(page.getSize())
					.totalElements(page.getTotalElements())
					.totalPages(page.getTotalPages())
					.first(page.isFirst())
					.last(page.isLast())
					.build();
		}

		public static PaginationInfo of(int page, int size, long totalElements) {
			int totalPages = (int) Math.ceil((double) totalElements / size);
			return PaginationInfo.builder()
					.currentPage(page)
					.pageSize(size)
					.totalElements(totalElements)
					.totalPages(totalPages)
					.first(page == 0)
					.last(page >= totalPages - 1)
					.build();
		}
	}
}

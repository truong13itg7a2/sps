package edu.txts.sps131025.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public static class FieldError {
	private final String field;
	private final String message;
	private final Object rejectedValue;

	public static FieldError of(String field, String message, Object rejectedValue) {
		return FieldError.builder()
				.field(field)
				.message(message)
				.rejectedValue(rejectedValue)
				.build();
	}
}


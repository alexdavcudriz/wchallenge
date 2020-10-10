package com.wolox.wchallenge.commons.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public final class ResultDTO {
	private LocalDateTime timestamp;
	private String code;
	private List<String> details;

	public static ResultDTO getSuccessfulDTO() {
		List<String> details = new ArrayList<>();
		details.add("Successful transaction.");

		return new ResultDTO(LocalDateTime.now(), "C0000", details);
	}
}

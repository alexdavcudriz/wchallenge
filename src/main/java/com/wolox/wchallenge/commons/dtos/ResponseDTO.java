package com.wolox.wchallenge.commons.dtos;

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
public final class ResponseDTO {
    private ResultDTO result;
    private Object data;
    
	public ResponseDTO(ResultDTO result) {
		super();
		this.result = result;
	}
}

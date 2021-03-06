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
public class PhotosDTO {

	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;
	
}

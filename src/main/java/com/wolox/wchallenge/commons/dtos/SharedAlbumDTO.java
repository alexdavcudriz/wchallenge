package com.wolox.wchallenge.commons.dtos;

import com.wolox.wchallenge.commons.enums.PermissionLevelEnum;

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
public class SharedAlbumDTO {

	private Integer albumId;
	private Integer userId;
	private PermissionLevelEnum permissionLevel;

}

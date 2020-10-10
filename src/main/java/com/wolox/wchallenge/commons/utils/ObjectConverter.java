package com.wolox.wchallenge.commons.utils;

import com.wolox.wchallenge.commons.dtos.SharedAlbumDTO;
import com.wolox.wchallenge.commons.entities.SharedAlbumsEntity;

public class ObjectConverter {
	
	public static SharedAlbumsEntity toEntity(SharedAlbumDTO object) {
		return SharedAlbumsEntity.builder()
				.AlbumId(object.getAlbumId())
				.UserId(object.getUserId())
				.PermissionLevel(object.getPermissionLevel())
				.build();
	}
	
	public static SharedAlbumDTO toDTO(SharedAlbumsEntity object) {
		return SharedAlbumDTO.builder()
				.albumId(object.getAlbumId())
				.userId(object.getUserId())
				.permissionLevel(object.getPermissionLevel())
				.build();
	}

}

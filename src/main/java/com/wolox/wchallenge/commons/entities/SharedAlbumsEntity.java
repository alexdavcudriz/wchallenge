package com.wolox.wchallenge.commons.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity(name = "SharedAlbums")
public class SharedAlbumsEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long SharedAlbumsId;
	private int AlbumId;
	private int UserId;
	private PermissionLevelEnum PermissionLevel;

}

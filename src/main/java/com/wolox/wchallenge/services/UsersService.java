package com.wolox.wchallenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.AlbumsDTO;
import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.commons.dtos.UsersDTO;
import com.wolox.wchallenge.commons.entities.SharedAlbumsEntity;
import com.wolox.wchallenge.commons.exceptions.NotFoundException;
import com.wolox.wchallenge.dao.clients.AlbumsClient;
import com.wolox.wchallenge.dao.clients.UsersClient;
import com.wolox.wchallenge.dao.repositories.SharedAlbumsRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersClient client;
	
	@Autowired
	private AlbumsClient albumClient;
	
	@Autowired
	private SharedAlbumsRepository sharedAlbumRepo;
	
	public ResponseDTO findAll(String albumId) throws NotFoundException {
		List<AlbumsDTO> albumsFound = albumClient.getAlbumsById(albumId.toString());
		if (albumsFound.isEmpty()) throw new NotFoundException("Album id not found");
		if ("0".equals(albumId)) return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getUsers());
		return findAllByPermission(Integer.parseInt(albumId));
		
	}
	
	public ResponseDTO findAllByPermission(int albumId) {
		List<UsersDTO> response = new ArrayList<>();
		List<SharedAlbumsEntity> foundList = findAlbumsByPermission(albumId);
		if (!foundList.isEmpty()) {
			for (SharedAlbumsEntity album : foundList) {
				response.addAll(client.getUserById(album.getUserId().toString()));
			}
		}
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), response);
	}
	
	public List<SharedAlbumsEntity> findAlbumsByPermission(int albumId) {
		Example<SharedAlbumsEntity> example = Example.of(SharedAlbumsEntity.builder()
				.AlbumId(albumId)
				.build());
		return sharedAlbumRepo.findAll(example);
	}
}

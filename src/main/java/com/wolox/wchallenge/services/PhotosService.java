package com.wolox.wchallenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.AlbumsDTO;
import com.wolox.wchallenge.commons.dtos.PhotosDTO;
import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.dao.clients.AlbumsClient;
import com.wolox.wchallenge.dao.clients.PhotosClient;

@Service
public class PhotosService {
	
	@Autowired
	private PhotosClient client;
	
	@Autowired
	private AlbumsClient albumsClient;
	
	public ResponseDTO findAll(String userId) {
		if ("0".equals(userId)) return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getPhotos());
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), getPhotosByUserIdService(userId));
	}
	
	private List<PhotosDTO> getPhotosByUserIdService(String userId) {
		List<AlbumsDTO> albumsByUserId = albumsClient.getAlbumsByUserId(userId);
		List<PhotosDTO> response = new ArrayList<PhotosDTO>();
		for (AlbumsDTO album : albumsByUserId) {
			response.addAll(client.getPhotosByAlbumId(Integer.toString(album.getId())));
		}
		return response;
	}

}

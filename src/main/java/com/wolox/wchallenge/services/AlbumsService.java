package com.wolox.wchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.dao.clients.AlbumsClient;

@Service
public class AlbumsService {
	
	@Autowired
	private AlbumsClient client;
	
	public ResponseDTO findAll(String userId) {
		if ("0".equals(userId)) return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getAlbums());
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getAlbumsByUserId(userId));
	}

}

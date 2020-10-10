package com.wolox.wchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.dao.clients.PhotosClient;

@Service
public class PhotosService {
	
	@Autowired
	private PhotosClient client;
	
	public ResponseDTO findAll() {
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getPhotos());
	}

}

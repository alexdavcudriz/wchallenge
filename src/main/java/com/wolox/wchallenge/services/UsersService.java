package com.wolox.wchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.dao.clients.UsersClient;

@Service
public class UsersService {
	
	@Autowired
	private UsersClient client;
	
	public ResponseDTO findAll() {
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getUsers());
	}
}

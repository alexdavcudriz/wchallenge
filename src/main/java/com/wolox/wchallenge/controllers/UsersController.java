package com.wolox.wchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.exceptions.NotFoundException;
import com.wolox.wchallenge.services.UsersService;

@RestController
@RequestMapping("/v1/users")
public class UsersController {
	
	@Autowired
	private UsersService service;

	@GetMapping()
	public ResponseEntity<ResponseDTO> findAll(@RequestParam(required = false, defaultValue = "0") String albumId) throws NotFoundException {
		return new ResponseEntity<>(service.findAll(albumId), HttpStatus.OK);
	}
}

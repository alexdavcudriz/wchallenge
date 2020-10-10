package com.wolox.wchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.SharedAlbumDTO;
import com.wolox.wchallenge.commons.exceptions.NotFoundException;
import com.wolox.wchallenge.services.AlbumsService;

@RestController
@RequestMapping("/v1/albums")
public class AlbumsController {
	
	@Autowired
	private AlbumsService service;

	@GetMapping()
	public ResponseEntity<ResponseDTO> findAll(@RequestParam(required = false, defaultValue = "0") String userId) {
		return new ResponseEntity<>(service.findAll(userId), HttpStatus.OK);
	}

	@PostMapping("/shareds")
	public ResponseEntity<ResponseDTO> saveSharedAlbum(@RequestBody SharedAlbumDTO dto) throws NotFoundException {
		return new ResponseEntity<>(service.saveSharedAlbum(dto), HttpStatus.OK);
	}

}

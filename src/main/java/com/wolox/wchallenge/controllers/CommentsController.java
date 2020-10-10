package com.wolox.wchallenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.services.CommentsService;

@RestController
@RequestMapping("/v1/comments")
public class CommentsController {
	
	@Autowired
	private CommentsService service;

	@GetMapping()
	public ResponseEntity<ResponseDTO> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
}

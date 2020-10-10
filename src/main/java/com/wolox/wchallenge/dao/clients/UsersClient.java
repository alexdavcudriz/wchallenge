package com.wolox.wchallenge.dao.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wolox.wchallenge.commons.dtos.UsersDTO;

@Component
public class UsersClient {

	@Value("${client.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	public List<UsersDTO> getUsers() {
		ResponseEntity<List<UsersDTO>> response = restTemplate.exchange(url.concat("users"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UsersDTO>>() {});
		return response.getBody();
	}

	public List<UsersDTO> getUserById(String id) {
		ResponseEntity<List<UsersDTO>> response = restTemplate.exchange(url.concat("users?id=").concat(id), 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<UsersDTO>>() {});
		return response.getBody();
	}
}

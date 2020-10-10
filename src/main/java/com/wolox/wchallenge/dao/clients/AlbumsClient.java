package com.wolox.wchallenge.dao.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wolox.wchallenge.commons.dtos.AlbumsDTO;

@Component
public class AlbumsClient {

	@Value("${client.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	public List<AlbumsDTO> getAlbums() {
		ResponseEntity<List<AlbumsDTO>> response = restTemplate.exchange(url.concat("albums"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AlbumsDTO>>() {});
		return response.getBody();
	}

	public List<AlbumsDTO> getAlbumsById(String id) {
		ResponseEntity<List<AlbumsDTO>> response = restTemplate.exchange(url.concat("albums?id=").concat(id), 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AlbumsDTO>>() {});
		return response.getBody();
	}

	public List<AlbumsDTO> getAlbumsByUserId(String userId) {
		ResponseEntity<List<AlbumsDTO>> response = restTemplate.exchange(url.concat("albums?userId=").concat(userId), 
				HttpMethod.GET, null,
				new ParameterizedTypeReference<List<AlbumsDTO>>() {});
		return response.getBody();
	}

}

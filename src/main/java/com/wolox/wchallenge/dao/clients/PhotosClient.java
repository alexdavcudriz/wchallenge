package com.wolox.wchallenge.dao.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.wolox.wchallenge.commons.dtos.PhotosDTO;

@Component
public class PhotosClient {

	@Value("${client.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	public List<PhotosDTO> getPhotos() {
		ResponseEntity<List<PhotosDTO>> response = restTemplate.exchange(url.concat("photos"), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<PhotosDTO>>() {});
		return response.getBody();
	}

}

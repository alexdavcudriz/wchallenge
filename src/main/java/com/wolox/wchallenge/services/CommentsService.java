package com.wolox.wchallenge.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.CommentsDTO;
import com.wolox.wchallenge.commons.dtos.PostsDTO;
import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.dao.clients.CommentsClient;
import com.wolox.wchallenge.dao.clients.PostsClient;

@Service
public class CommentsService {
	
	@Autowired
	private CommentsClient client;
	
	@Autowired
	private PostsClient postsClient;
	
	public ResponseDTO findAll(String name, String userId) {
		if (!"".equals(name)) return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getCommentsByName(name));
		if (!"0".equals(userId)) return new ResponseDTO(ResultDTO.getSuccessfulDTO(), findAllByUserId(userId));
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getComments());
	}
	
	private List<CommentsDTO> findAllByUserId(String userId) {
		List<PostsDTO> postsByUserId = postsClient.getPostsByUserId(userId);
		List<CommentsDTO> response = new ArrayList<CommentsDTO>();
		for (PostsDTO post : postsByUserId) {
			response.addAll(client.getCommentsByPostId(Integer.toString(post.getId())));
		}
		return response;
	}

}

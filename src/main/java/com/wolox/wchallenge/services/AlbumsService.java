package com.wolox.wchallenge.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.wolox.wchallenge.commons.dtos.AlbumsDTO;
import com.wolox.wchallenge.commons.dtos.ResponseDTO;
import com.wolox.wchallenge.commons.dtos.ResultDTO;
import com.wolox.wchallenge.commons.dtos.SharedAlbumDTO;
import com.wolox.wchallenge.commons.dtos.UsersDTO;
import com.wolox.wchallenge.commons.entities.SharedAlbumsEntity;
import com.wolox.wchallenge.commons.exceptions.NotFoundException;
import com.wolox.wchallenge.commons.utils.ObjectConverter;
import com.wolox.wchallenge.dao.clients.AlbumsClient;
import com.wolox.wchallenge.dao.clients.UsersClient;
import com.wolox.wchallenge.dao.repositories.SharedAlbumsRepository;

@Service
public class AlbumsService {
	
	@Autowired
	private AlbumsClient client;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private SharedAlbumsRepository sharedAlbumRepo;
	
	public ResponseDTO findAll(String userId) {
		if ("0".equals(userId)) return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getAlbums());
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), client.getAlbumsByUserId(userId));
	}
	
	public ResponseDTO saveSharedAlbum(SharedAlbumDTO dto) throws NotFoundException {
		validateSharedAlbumDTO(dto);
		Optional<SharedAlbumsEntity> findOne = findOne(dto);
		if (findOne.isEmpty()) {
			sharedAlbumRepo.save(ObjectConverter.toEntity(dto));
		} else {
			SharedAlbumsEntity entity = ObjectConverter.toEntity(dto);
			entity.setSharedAlbumsId(findOne.get().getSharedAlbumsId());
			sharedAlbumRepo.save(entity);
		}
		return new ResponseDTO(ResultDTO.getSuccessfulDTO(), Boolean.TRUE);
	}
	
	private Optional<SharedAlbumsEntity> findOne(SharedAlbumDTO dto) {
		Example<SharedAlbumsEntity> example = Example.of(SharedAlbumsEntity.builder()
				.UserId(dto.getUserId())
				.AlbumId(dto.getAlbumId())
				.build());
		return sharedAlbumRepo.findOne(example);
	}
	
	private void validateSharedAlbumDTO(SharedAlbumDTO dto) throws NotFoundException {
		List<AlbumsDTO> albumsFound = client.getAlbumsById(dto.getAlbumId().toString());
		if (albumsFound.isEmpty()) throw new NotFoundException("Album id not found");
		List<UsersDTO> usersFound = usersClient.getUserById(dto.getUserId().toString());
		if (usersFound.isEmpty()) throw new NotFoundException("User id not found");
	}

}

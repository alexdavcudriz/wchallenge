package com.wolox.wchallenge.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wolox.wchallenge.commons.entities.SharedAlbumsEntity;

public interface SharedAlbumsRepository extends JpaRepository<SharedAlbumsEntity, Long>{

}

package com.softtek.authorization.transaction.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtek.authorization.transaction.model.dto.AuthorizationDto;

public interface AuthorizationDao extends JpaRepository<AuthorizationDto, Integer>{

	
}

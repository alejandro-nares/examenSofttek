package com.softtek.authorization.transaction.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.softtek.authorization.transaction.commons.util.ServiceException;
import com.softtek.authorization.transaction.model.dto.AuthorizationDto;

import lombok.extern.log4j.Log4j;

@Log4j
@SpringBootTest
@ContextConfiguration
public class AuthorizationServiceTest {
	
	@Autowired AuthorizationServiceImpl authorizationServiceImpl;
	
	@Test
	public void saveAutorization() throws ServiceException {
		String response = null;
		
		AuthorizationDto authorizationDto = new AuthorizationDto();

		authorizationDto.setId(3);
		authorizationDto.setDescription("Test Service");
		authorizationDto.setAmount(new BigDecimal(11.02));
		authorizationDto.setEstatus("Aprobado");
		
		response = authorizationServiceImpl.saveAutorization(authorizationDto);
		
		log.debug("response :: " + response);
		
	}
	
	@Test
	public void updateAutorization() throws ServiceException {
		String response = null;
		
		AuthorizationDto authorizationDto = new AuthorizationDto();

		authorizationDto.setId(4);
		authorizationDto.setDescription("Test Service Update");
		
		response = authorizationServiceImpl.updateAutorization(authorizationDto);
		
		log.debug("response :: " + response);
		
	}
	
	
	@Test
	public void findAllAutorizations() {
		
		List<AuthorizationDto> response = null; 
		
		response = authorizationServiceImpl.findAllAutorizations();
		
		log.debug("response :: " + response);
		
		
	}
	
	@Test
	public void findAutorizationWhithId() {
		
		AuthorizationDto response = null; 
		
		Integer id = 2;
		
		response = authorizationServiceImpl.findAutorizationWhithId(id);
		
		log.debug("response :: " + response);
		
		
	}

}

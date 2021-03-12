package com.softtek.authorization.transaction.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.softtek.authorization.transaction.commons.util.ServiceException;
import com.softtek.authorization.transaction.commons.vo.AuthorizationVo;

import lombok.extern.log4j.Log4j;

@Log4j
@SpringBootTest
@ContextConfiguration
public class AuthorizationFacadeTest {
	
	@Autowired AuthorizationFacade authorizationFacade;
	
	@Test
	public void saveAutorization() throws ServiceException {
		String response = null;
		
		AuthorizationVo authorizationVo = new AuthorizationVo();

		authorizationVo.setId("3");
		authorizationVo.setDescription("Test Service");
		authorizationVo.setAmount("11.02");
		authorizationVo.setEstatus("Aprobado");
		
		response = authorizationFacade.saveAutorization(authorizationVo);
		
		log.debug("response :: " + response);
		
	}
	
	@Test
	public void updateAutorization() throws ServiceException {
		String response = null;
		
		AuthorizationVo authorizationVo = new AuthorizationVo();

		authorizationVo.setId("4");
		authorizationVo.setDescription("Test facade Update");
		
		response = authorizationFacade.updateAutorizationWhithId(authorizationVo);
		
		log.debug("response :: " + response);
		
	}
	
	@Test
	public void updateAutorizationIdInvalid() throws ServiceException {
		String response = null;
		
		AuthorizationVo authorizationVo = new AuthorizationVo();

		authorizationVo.setId("5555");
		authorizationVo.setDescription("Test facade Update");
		
		response = authorizationFacade.updateAutorizationWhithId(authorizationVo);
		
		log.debug("response :: " + response);
		
	}
	
	
	@Test
	public void findAllAutorizations() {
		
		String response = null; 
		
		response = authorizationFacade.findAutorizationWhithId(null);
		
		log.debug("response :: " + response);
		
		
	}
	
	@Test
	public void findAutorizationWhithId() {
		
		String response = null; 
		
		Integer id = 2;
		
		response = authorizationFacade.findAutorizationWhithId(id);
		
		log.debug("response :: " + response);
		
		
	}
	
	@Test
	public void findAutorizationWhithIdInvalid() {
		
		String response = null; 
		
		Integer id = 233;
		
		response = authorizationFacade.findAutorizationWhithId(id);
		
		log.debug("response :: " + response);
		
	}
	

	
	@Test
	public void sumarListaNumeros() {
		
		BigDecimal response = null; 
		
		List<BigDecimal> listBigDecimal = new ArrayList<BigDecimal>();
		
		listBigDecimal.add(new BigDecimal("11.01"));
		listBigDecimal.add(new BigDecimal("11.01"));
		listBigDecimal.add(new BigDecimal("11.01"));
		listBigDecimal.add(new BigDecimal("11.01"));
		
		response = authorizationFacade.sumarListaNumeros(listBigDecimal);
		
		log.debug("response :: " + response);
		
	}
}

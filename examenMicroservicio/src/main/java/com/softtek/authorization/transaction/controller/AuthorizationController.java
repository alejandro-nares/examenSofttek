package com.softtek.authorization.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softtek.authorization.transaction.commons.vo.AuthorizationVo;
import com.softtek.authorization.transaction.facade.AuthorizationFacade;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/api/task2")
public class AuthorizationController {

	@Autowired
	private AuthorizationFacade autorizacionTransaccionFacade;

	/**
	 * Proposito: Save an transaction autorization.
	 *
	 * @author alejandro.nares
	 * @version 1.0.0, 11/03/2021
	 * @param authorizationVo Information to save autorization
	 * 
	 */
	@PostMapping(path = "/saveAutorization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveAutorization(@RequestBody AuthorizationVo authorizationVo) {
		log.debug("Inicio");

		ResponseEntity<Object> result = null;
		String serviceResponse = null;

		serviceResponse = autorizacionTransaccionFacade.saveAutorization(authorizationVo);

		result = new ResponseEntity<>(serviceResponse, HttpStatus.OK);

		log.debug("Fin");
		return result;
	}

	/**
	 * Proposito: update an transaction autorization.
	 *
	 * @author alejandro.nares
	 * @version 1.0.0, 11/03/2021
	 * @param autorozacionTransaccionVo Information to update autorization.
	 * 
	 */
	@PatchMapping(path = "/updateAutorization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAutorization(@RequestBody AuthorizationVo authorizationVo) {
		log.debug("Inicio");

		ResponseEntity<Object> result = null;
		String serviceResponse = null;

		serviceResponse = autorizacionTransaccionFacade.updateAutorizationWhithId(authorizationVo);

		result = new ResponseEntity<>(serviceResponse, HttpStatus.OK);

		log.debug("Fin");
		return result;
	}

	/**
	 * Proposito: Recover an transaction autorization for you id.
	 *
	 * @author alejandro.nares
	 * @version 1.0.0, 11/03/2021
	 * @param id Information to recover an autorization.
	 * 
	 */
	@GetMapping(path = "/recoverAutorization", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> recoverAutorizationWhithId(@RequestParam(name = "id", required = false) Integer id) {
		log.debug("Inicio");

		ResponseEntity<Object> result = null;
		String serviceResponse = null;

		serviceResponse = autorizacionTransaccionFacade.findAutorizationWhithId(id);

		result = new ResponseEntity<>(serviceResponse, HttpStatus.OK);

		log.debug("Fin");
		return result;
	}

}

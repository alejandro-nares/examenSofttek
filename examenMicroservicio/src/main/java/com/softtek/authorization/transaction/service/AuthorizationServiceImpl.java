package com.softtek.authorization.transaction.service;

import java.util.List;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.softtek.authorization.transaction.commons.util.Constants;
import com.softtek.authorization.transaction.helper.ValidatorHelper;
import com.softtek.authorization.transaction.model.dao.AuthorizationDao;
import com.softtek.authorization.transaction.model.dto.AuthorizationDto;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	
	@Autowired AuthorizationDao authorizationDao;

	@Override
	public String saveAutorization(AuthorizationDto authorizationDto) {
		log.debug(Constants.LOG_INICIO);
		
		String result = null;
		JsonObjectBuilder jsonObject = null;
		
		
		//Validations
		ValidatorHelper.validateObjectAndThrowException(authorizationDto.getId(), "id", HttpStatus.CONFLICT);
		ValidatorHelper.validateObjectAndThrowException(authorizationDto.getAmount(), "id", HttpStatus.CONFLICT);
		ValidatorHelper.validateStringAndThrowException(authorizationDto.getEstatus(), "estatus", HttpStatus.CONFLICT);		
		ValidatorHelper.validateStringAndThrowException(authorizationDto.getDescription(), "description", HttpStatus.CONFLICT);
		
		authorizationDao.save(authorizationDto);
			
		jsonObject = Json.createObjectBuilder();
		jsonObject.add("message", "Authorization has been saved successfully.");
		jsonObject.add("code", Constants.CODE_success_200);
		
		result = jsonObject.build().toString();
		
		log.debug(Constants.LOG_FIN);
		return result;
	}

	@Override
	public String updateAutorization(AuthorizationDto authorizationDtoActualizado) {
		log.debug(Constants.LOG_INICIO);
		
		String result = null;
		JsonObjectBuilder jsonObject = null;
		Optional<AuthorizationDto> authorizationDtoOptional = null;
		AuthorizationDto authorizationDto = null;
		
		
		//Validations
		ValidatorHelper.validateObjectAndThrowException(authorizationDtoActualizado.getId(), "id", HttpStatus.CONFLICT);
		

        authorizationDtoOptional = authorizationDao.findById(authorizationDtoActualizado.getId());

        authorizationDto = ValidatorHelper.validateObjectOptional(authorizationDtoOptional);

		jsonObject = Json.createObjectBuilder();
		
        if(authorizationDto != null) {
            
            authorizationDto.setDescription(ValidatorHelper.validateNewString(authorizationDtoActualizado.getDescription(), authorizationDto.getDescription()));
            authorizationDto.setAmount(ValidatorHelper.validateNewBigDecimal(authorizationDtoActualizado.getAmount(), authorizationDto.getAmount()));
            authorizationDto.setEstatus(ValidatorHelper.validateNewString(authorizationDtoActualizado.getEstatus(), authorizationDto.getEstatus()));
    		
    		authorizationDao.save(authorizationDto);
    			
    		jsonObject.add("message", "Authorization has been successfully updated.");
    		jsonObject.add("code", Constants.CODE_success_200);
    		
    		result = jsonObject.build().toString();
    		
        }else {
        	
    		jsonObject.add("message", "Authorization not found.");
    		jsonObject.add("code", Constants.CODE_ERROR_422);
    		
    		result = jsonObject.build().toString();
        }
		
		log.debug(Constants.LOG_FIN);
		return result;
		
	}
	
	

	@Override
	public AuthorizationDto findAutorizationWhithId(Integer id) {
		
		Optional<AuthorizationDto> authorizationDtoOptional = null;
		AuthorizationDto response = null;


		ValidatorHelper.validateObjectAndThrowException(id, "id", HttpStatus.CONFLICT);
		
		
        authorizationDtoOptional = authorizationDao.findById(id);
        
        response = ValidatorHelper.validateObjectOptional(authorizationDtoOptional);
		
		return response;
	}

	
	@Override
	public List<AuthorizationDto> findAllAutorizations() {
		
		List<AuthorizationDto> response = null;		
		
        response = authorizationDao.findAll();
        
		
		return response;
	}

}

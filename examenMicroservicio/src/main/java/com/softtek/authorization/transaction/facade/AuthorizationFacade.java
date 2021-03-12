package com.softtek.authorization.transaction.facade;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softtek.authorization.transaction.commons.util.Constants;
import com.softtek.authorization.transaction.commons.vo.AuthorizationVo;
import com.softtek.authorization.transaction.helper.ValidatorHelper;
import com.softtek.authorization.transaction.model.dto.AuthorizationDto;
import com.softtek.authorization.transaction.service.AuthorizationServiceImpl;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Component
public class AuthorizationFacade {

    @Autowired private ModelMapper modelMapper;
	@Autowired private AuthorizationServiceImpl authorizationServiceImpl;

    
    
    /**
     * Proposito: Save an transaction autorization.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * @param authorizationVo Information to save transaction autorization.
     * @return String Response of the service
     * 
     */
    public String saveAutorization(AuthorizationVo authorizationVo) {
        log.debug(Constants.LOG_INICIO);

        String result = null; 
        AuthorizationDto authorizationDto = null;
        String validate = null;
        
        validate = ValidatorHelper.validateObjectAndThrowException(authorizationVo, "authorizationVo", HttpStatus.CONFLICT);
        
        if(validate != null) {
			return validate;
		}
        
        authorizationDto = this.authorizationVoToauthorizationDto(authorizationVo);
        
        try {

    		result = authorizationServiceImpl.saveAutorization(authorizationDto);
    		
        }catch (ServiceException serviceException) {
			throw serviceException;
		}
		
        return result;
    }
    
    /**
     * Proposito: Update an transaction autorization.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * @param authorizationVo Information to update transaction autorization.
     * @return String Response of the service
     * 
     */
    public String updateAutorizationWhithId(AuthorizationVo authorizationVo) {
        log.debug(Constants.LOG_INICIO);

        String result = null; 
        AuthorizationDto authorizationDto = new AuthorizationDto();

        String validate = null;
        
        //VALIDACIONES
        validate = ValidatorHelper.validateObjectAndThrowException(authorizationVo, "authorizationVo", HttpStatus.CONFLICT);
        
        if(validate != null) {
			return validate;
		}

        authorizationDto = this.authorizationVoToauthorizationDto(authorizationVo);
        
		result = authorizationServiceImpl.updateAutorization(authorizationDto);

        log.debug(Constants.LOG_FIN);
        return result;
    }
    
    

    /**
     * Proposito: Recover an transaction autorization with id.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * 
     * @param authorizationVo Information to recover transaction autorization.
     * @return String Response of the service
     */
	public String findAutorizationWhithId(Integer id) {
        log.debug(Constants.LOG_INICIO);

        List<AuthorizationVo> listAuthorizationVo = null; 
        AuthorizationDto authorizationDto = null;
        AuthorizationVo authorizationVo = null;
        List<AuthorizationDto> listAuthorizationDto = null; 
		JsonArray jsonArray = null;
		JsonReader jsonReader = null;
		JsonObjectBuilder jsonObjectBuilder = null;
		String result = null;
		String json = null;

		listAuthorizationVo = new ArrayList<AuthorizationVo>();
    	
    	GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gsonBuilder.create();

		jsonObjectBuilder = Json.createObjectBuilder();
        if(id == null) {
        	
        	listAuthorizationDto = authorizationServiceImpl.findAllAutorizations();
        	
        	for(AuthorizationDto authorization : listAuthorizationDto) {

        		authorizationVo = new AuthorizationVo();
        		authorizationVo = this.authorizationDtoToAuthorizationVo(authorization);

        		listAuthorizationVo.add(authorizationVo);
            	
        	}


    		json = gson.toJson(listAuthorizationVo);

    		jsonReader = Json.createReader(new StringReader(json));
    		jsonArray = jsonReader.readArray();
    		jsonReader.close();

    		jsonObjectBuilder.add("autorizations", jsonArray);
    		
			result = jsonObjectBuilder.build().toString();
    		
        }else {
        	
        	authorizationDto = authorizationServiceImpl.findAutorizationWhithId(id);
        	
        	if(authorizationDto != null) {

        		authorizationVo = this.authorizationDtoToAuthorizationVo(authorizationDto);
            	
        		listAuthorizationVo.add(authorizationVo);
        		
        		json = gson.toJson(listAuthorizationVo);

        		jsonReader = Json.createReader(new StringReader(json));
        		jsonArray = jsonReader.readArray();
        		jsonReader.close();

        		jsonObjectBuilder.add("autorizations", jsonArray);
        		
    			result = jsonObjectBuilder.build().toString();
        		
        	}else {
        		
        		jsonObjectBuilder.add("message", "Authorization not found.");
        		jsonObjectBuilder.add("code", Constants.CODE_ERROR_422);
        		
        		result = jsonObjectBuilder.build().toString();
        	}
    		
        	
        }
        


        log.debug(Constants.LOG_FIN);
        return result;
    }
    
    
	/**
	 * Proposito: Convertir un objeto DistributionListVo a DistributionListEntity
	 * 
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * 
	 * @param distributionListVo - Objeto a transformar
	 * 
	 * @return Objeto DistributionListEntity - Objeto transformado
	 */
	public AuthorizationDto authorizationVoToauthorizationDto(AuthorizationVo authorizationVo) {
	    log.debug(Constants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
	    AuthorizationDto authorizationDto = null;	
	    
        //VLIDACION DE NULIDAD
        ValidatorHelper.validateObjectAndThrowException(authorizationVo, "authorizationVo", httpStatus);    	    

        // SE INSTANCIA MODELMAPPER
        modelMapper = new ModelMapper();

        // SE TRANSFORMA EL OBJETO VO
        authorizationDto = modelMapper.map(authorizationVo, AuthorizationDto.class);
	    
	    
	    log.debug(Constants.LOG_FIN);
	    return authorizationDto;
	}
	

    /**
     * Proposito: Convertir un objeto DistributionListEntity a DistributionListVo
     * 
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * 
     * @param distributionListEntity - Objeto a transformar
     * 
     * @return DistributionListVo - Objeto transformado
     */
    public AuthorizationVo authorizationDtoToAuthorizationVo(AuthorizationDto authorizationDto) {
        log.debug(Constants.LOG_INICIO);
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        AuthorizationVo authorizationVo = null;

        //VLIDACION DE NULIDAD
        ValidatorHelper.validateObjectAndThrowException(authorizationDto, "authorizationDto", httpStatus);

        // SE INSTANCIA MODELMAPPER
        modelMapper = new ModelMapper();

        // SE TRANSFORMA EL OBJETO ENTITY
        authorizationVo = modelMapper.map(authorizationDto, AuthorizationVo.class);

        log.debug(Constants.LOG_FIN);
        return authorizationVo;
    }
    

    public BigDecimal sumarListaNumeros(List<BigDecimal> listBigDecimal) {
        log.debug(Constants.LOG_INICIO);
        BigDecimal suma = new BigDecimal(0);
        
        for(BigDecimal number : listBigDecimal) {
        	suma = suma.add(number);
        }
        
        log.debug(Constants.LOG_FIN);
        return suma;
    }
    
}

package com.softtek.authorization.transaction.helper;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Optional;

import org.apache.commons.validator.GenericValidator;
import org.springframework.http.HttpStatus;

import com.softtek.authorization.transaction.commons.util.Constants;
import com.softtek.authorization.transaction.commons.util.ServiceException;
import com.softtek.authorization.transaction.model.dto.AuthorizationDto;

import lombok.extern.log4j.Log4j;

@Log4j
public class ValidatorHelper {

    /**
     * Proposito: Validar si el objeto es nulo
     * 
     * @author  alejandro.nares, Habil MX
     * @version 1.0.0 - 22/07/2020
     * @param object - Objeto a evaluar
     * 
     * @return Boolean - Estatus del entity
     */
    public static Boolean isNullObject(Object object) {
        
        return object == null;
    }
    
    
    /**
     * Proposito: Validar si el objeto es nulo y si lo es lanzar excepcion
     * 
     * @author  alejandro.nares
     * @version 1.0.0 - 11/03/2021
     * 
     * @param param - Valor del campo a evaluar
     * @param fieldName - Nombre del campo evaluado
     * @param httpStatus - Estatus de la petición realizada
     * 
     */
    public static void validateObjectAndThrowException(Object param, String name, HttpStatus httpStatus) {
        log.debug(Constants.LOG_INICIO);
        String mensajeExcepcion = null;

        if(Constants.VALUE_TRUE.equals(ValidatorHelper.isNullObject(param))){
            //SE OBTIENE EL MENSAJE DE EXCEPCION
            mensajeExcepcion = MessageFormat.format(Constants.MESSAGES_VALIDATION_EMPTY_NULL, name);
 
            log.debug("mensajeExcepcion  ::  " + mensajeExcepcion);
          throw new ServiceException(mensajeExcepcion, "400",httpStatus);
//          throw new ServiceException(mensajeExcepcion);

        }
        
        log.debug(Constants.LOG_FIN);
    }

    
    /**
     * Proposito : Validar si el campo de tipo string es nulo o vacio y si lo es lanzar excepcion
     * 
     * @author  alejandro.nares
	 * @version 1.0.0 - 11/03/2021
     * 
     * @param value - Valor del campo a evaluar
     * @param fieldName - Nombre del campo evaluado
     * @param httpStatus - Estatus de la petición realizada
     *
     */
    public static void validateStringAndThrowException(String value, String fieldName, HttpStatus httpStatus){
        log.debug(Constants.LOG_INICIO);
        String mensajeExcepcion = null;
        
        if(GenericValidator.isBlankOrNull(value)){
        	
            //SE OBTIENE EL MENSAJE DE EXCEPCION
            mensajeExcepcion = MessageFormat.format(Constants.MESSAGES_VALIDATION_EMPTY_NULL, fieldName);

            log.debug("mensajeExcepcion  ::  " + mensajeExcepcion);
            throw new ServiceException(mensajeExcepcion, "400",httpStatus);
//            throw new Exception(mensajeExcepcion, new NullPointerException(mensajeExcepcion),httpStatus);
        }
        log.debug(Constants.LOG_FIN);
    }
    


    /**
     * Proposito : Validar si el valor del campos newValue es distinto de nulo o vacio y setear su valor en caso de serlo 
     * 
     * @author  alejandro.nares
	 * @version 1.0.0 - 11/03/2021
     * 
     * @param newValue - Valor a validar
     * @param oldValue - Valor en caso de no no cumplirse la validacion
     * 
     * @return String - Obejto que contenra el valor de newValue u oldValue
     *
     */
    public static BigDecimal validateNewBigDecimal(BigDecimal newValue, BigDecimal oldValue){
        log.debug(Constants.LOG_INICIO);
        BigDecimal value = null;
        value = ValidatorHelper.isNullObject(newValue).equals(Constants.VALUE_FALSE) ? newValue: oldValue;
        log.debug(Constants.LOG_FIN);
        return value;
    }
    
    
    /**
     * Proposito : Validar si el valor del campos newValue es distinto de nulo o vacio y setear su valor en caso de serlo 
     * 
     * @author  alejandro.nares
	 * @version 1.0.0 - 11/03/2021
     * 
     * @param newValue - Valor a validar
     * @param oldValue - Valor en caso de no no cumplirse la validacion
     * 
     * @return String - Obejto que contenra el valor de newValue u oldValue
     *
     */
    public static String validateNewString(String newValue, String oldValue){
        log.debug(Constants.LOG_INICIO);
        String value = null;
        value = ValidatorHelper.isNullOrEmptyString(newValue).equals(Constants.VALUE_FALSE) ? newValue: oldValue;
        log.debug(Constants.LOG_FIN);
        return value;
    }
    

    /**
    * Proposito: Validar si la cadena es nulo o vacio
    * 
    * @author  alejandro.nares
	* @version 1.0.0 - 11/03/2021
	* 
    * @param atributo - Cadena a evaluar
    * 
    * @return Boolean - Estatus del atributo
    */
   public static Boolean isNullOrEmptyString(String atributo) {
       
       return atributo == null || atributo.isBlank();
   }

   /**
    * Proposito : Validar que el objeto optional contenga un registro en caso de tenerlo, obtenerlo y si no retorna un nulo.
    * 
    * @author  alejandro.nares
	* @version 1.0.0 - 11/03/2021
    * 
    * 
    * @param distributionListOptional - Objeto a validar
    * 
    * @return AuthorizationDto - Objeto obtenido del objeto Optional
    */
   public static AuthorizationDto validateObjectOptional(Optional<AuthorizationDto> authorizationDtoOptional){
       log.debug(Constants.LOG_INICIO);
       AuthorizationDto authorizationDto = null;

       authorizationDto = (authorizationDtoOptional.isPresent()) ? authorizationDtoOptional.get() : null;
       
       log.debug(Constants.LOG_FIN);
       return authorizationDto;
   }
    
}

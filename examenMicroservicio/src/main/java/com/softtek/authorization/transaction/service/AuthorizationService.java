package com.softtek.authorization.transaction.service;

import java.util.List;
import java.util.Optional;

import com.softtek.authorization.transaction.model.dto.AuthorizationDto;


public interface AuthorizationService {
    
    /**
     * Proposito: Save an transaction autorization.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * @param authorizationDto Information to save transaction autorization.
     * @return String Response of the service
     * 
     */
    public String saveAutorization(AuthorizationDto authorizationDto);
    
    /**
     * Proposito: Update an transaction autorization.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * @param authorizationDto Information to update transaction autorization.
     * @return String Response of the service
     * 
     */
    public String updateAutorization(AuthorizationDto authorizationDto);

    /**
     * Proposito: Recover an transaction autorization with id.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021
     * @param authorizationDto Information to recover transaction autorization.
     * @return String Response of the service
     */
    public AuthorizationDto findAutorizationWhithId(Integer id);

    /**
     * Proposito: Recover all autorization.
     *
     * @author alejandro.nares
     * @version 1.0.0, 11/03/2021

     * @return String Response of the service
     */
    public List<AuthorizationDto> findAllAutorizations();
    
    
    
}

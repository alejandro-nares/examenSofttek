package com.softtek.authorization.transaction.model.dto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Table(name="Authorization")
@Entity
public class AuthorizationDto {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO) Integer id;
	private Integer id;
	
	private String description;
    private BigDecimal amount;
    private String estatus;
    
    
}

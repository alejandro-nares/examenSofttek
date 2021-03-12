package com.softtek.authorization.transaction.commons.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorizationVo implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -7743966235167506650L;

	private String id;
	private String description;
    private String amount;
    private String estatus;
	
}

package com.emiage.s12018.noteReminder.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//exception levée lors la création d'un utilisateur dont le nom est déja utilisé par un autre
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://emiage2018s1.com/courses}002_ACTION_NOT_AUTHORIZED")
public class UserNameUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 3518170101751491969L;

	public UserNameUnavailableException(String message) {
		super(message);
	}
}

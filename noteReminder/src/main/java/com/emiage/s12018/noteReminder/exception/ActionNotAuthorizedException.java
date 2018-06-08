package com.emiage.s12018.noteReminder.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

//exception levée si le user en cours n'est pas autorisé a fait ce qu'il veut
@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://emiage2018s1.com/courses}002_ACTION_NOT_AUTHORIZED")
public class ActionNotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 3518170101751491969L;

	public ActionNotAuthorizedException(String message) {
		super(message);
	}
}

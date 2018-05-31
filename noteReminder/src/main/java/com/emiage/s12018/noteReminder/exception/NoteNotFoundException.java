package com.emiage.s12018.noteReminder.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://emiage2018s1.com/courses}001_NOTE_NOT_FOUND")
public class NoteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3518170101751491969L;

	public NoteNotFoundException(String message) {
		super(message);
	}
}

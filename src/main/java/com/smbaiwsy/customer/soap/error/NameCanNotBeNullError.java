package com.smbaiwsy.customer.soap.error;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
/**
 * The wrapper for the name can not be null error
 * @author ana mattuzzi-stojanovic
 *
 */
@SoapFault(faultCode = FaultCode.SENDER, faultStringOrReason = "Can't create customer without the name")
public class NameCanNotBeNullError extends RuntimeException{

	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 3336050780965009856L;
	
	public NameCanNotBeNullError() {
		super("Can't create customer without the name");
	}

}

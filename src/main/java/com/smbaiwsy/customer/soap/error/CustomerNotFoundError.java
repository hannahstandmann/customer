package com.smbaiwsy.customer.soap.error;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
/**
 * The wrapper for the customer not found error
 * @author ana mattuzzi-stojanovic
 *
 */
@SoapFault(faultCode = FaultCode.RECEIVER, faultStringOrReason = "Customer not found!")
public class CustomerNotFoundError extends RuntimeException{

	/**
	 * the generated serial id
	 */
	private static final long serialVersionUID = 2898286022183242614L;
	
	public CustomerNotFoundError() {
		super("Customer not found!");
	}
}

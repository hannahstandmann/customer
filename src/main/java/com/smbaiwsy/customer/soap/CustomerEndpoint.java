package com.smbaiwsy.customer.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.smbaiwsy.customer.soap.error.CustomerNotFoundError;
import com.smbaiwsy.customer.soap.error.NameCanNotBeNullError;

import com.smbaiwsy.customer.jaxb.Customer;
import com.smbaiwsy.customer.jaxb.CustomerDetails;
import com.smbaiwsy.customer.jaxb.GetCustomerRequest;
import com.smbaiwsy.customer.jaxb.GetCustomerResponse;
import com.smbaiwsy.customer.jaxb.SetCustomerDetailsRequest;
import com.smbaiwsy.customer.jaxb.SetCustomerDetailsResponse;
import com.smbaiwsy.customer.jaxb.SetCustomerRequest;
import com.smbaiwsy.customer.jaxb.SetCustomerResponse;
import com.smbaiwsy.customer.service.CustomerService;

/**
 * An implementation of a SOAP Endpoint
 * 
 * @author ana mattuzzi-stojanovic
 *
 */
@Endpoint
public class CustomerEndpoint {
	private static final String NAMESPACE_URI = "http://smbaiwsy.com/2016/complete-task";
	private static final Logger log = LoggerFactory.getLogger(CustomerEndpoint.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * For the received instance of {@see GetCustomerRequest} returns corresponding
	 * {@see GetCustomerResponse}
	 * 
	 * @param request an instance of {@see GetCustomerRequest}
	 * @return an instance of {@see GetCustomerResponse}
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerRequest")
	@ResponsePayload
	public GetCustomerResponse getCustomer(@RequestPayload GetCustomerRequest request) {
		long requestId = request.getId();
		if (requestId <= 0) {
			throw new CustomerNotFoundError();
		}
		Customer customer = customerService.findCustomerById(requestId);
		if (customer.getCustomerId() == -1L) {
			throw new CustomerNotFoundError();
		}
		GetCustomerResponse response = new GetCustomerResponse();
		response.setCustomer(customer);

		return response;
	}

	/**
	 * For the received instance of {@see SetCustomerRequest} returns corresponding
	 * {@see SetCustomerResponse}
	 * 
	 * @param request an instance of {@see SetCustomerRequest}
	 * @return an instance of {@see SetCustomerResponse}
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCustomerRequest")
	@ResponsePayload
	public SetCustomerResponse setCustomer(@RequestPayload SetCustomerRequest request) {
		Customer customer = request.getCustomer();
		log.info(customer.getName());
		if (customer.getName() == null) {
			throw new NameCanNotBeNullError();
		}
		Customer cust = customerService.createOrUpdateCustomer(customer);
		SetCustomerResponse response = new SetCustomerResponse();
		response.setCustomer(cust);

		return response;
	}

	/**
	 * For the received instance of {@see SetCustomerDetailsRequest} returns
	 * corresponding {@see SetCustomerDetailsResponse}
	 * 
	 * @param request an instance of {@see SetCustomerDetailsRequest}
	 * @return an instance of {@see SetCustomerDetailsResponse}
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCustomerDetailsRequest")
	@ResponsePayload
	public SetCustomerDetailsResponse setCustomer(@RequestPayload SetCustomerDetailsRequest request) {
		CustomerDetails customerDetails = request.getCustomerDetails();
		log.info("Details: " + customerDetails.getName());
		if (customerDetails.getName() == null) {
			throw new NameCanNotBeNullError();
		}
		Customer customer = customerService.fromCustomerDetails(customerDetails);
		Customer cust = customerService.createOrUpdateCustomer(customer);
		SetCustomerDetailsResponse response = new SetCustomerDetailsResponse();
		response.setCustomer(cust);

		return response;
	}
}

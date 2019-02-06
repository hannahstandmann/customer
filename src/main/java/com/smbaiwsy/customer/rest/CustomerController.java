package com.smbaiwsy.customer.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smbaiwsy.customer.jaxb.Customer;
import com.smbaiwsy.customer.service.CustomerService;
import com.smbaiwsy.customer.soap.error.CustomerNotFoundError;
import com.smbaiwsy.customer.soap.error.NameCanNotBeNullError;
/**
 * The rest controller
 * @author ana mattuzzi-stojanovic
 *
 */
@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;
	/**
	 * REST Endpoint to get customer by id 
	 * @param id the id of the customer
	 * @return the {@see Customer} DTO for the given id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
	public Customer getCustomer(@PathVariable(value = "id") long id) {
		Customer customer = customerService.findCustomerById(id);
		if (customer.getCustomerId() == -1L) {
			throw new CustomerNotFoundError();
		}
		return customer;
	}
    
	/**
	 * updates the customer
	 * @param customerId the id of the customer
	 * @param customer the {@see Customer} DTO
	 * @return the updated {@see Customer}
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/customers/{id}")
	public Customer setCustomer(@PathVariable(value = "id", required = true) long customerId,
			@RequestBody @Valid final Customer customer) {
		if (customerService.findCustomerById(customerId).getCustomerId() == -1L) {
			throw new CustomerNotFoundError();
		}
		customer.setCustomerId(customerId);
		Customer cust = customerService.createOrUpdateCustomer(customer);
		return cust;
	}

	/**
	 * REST Endpoint to create the customer
	 * @param customer the {@see Customer} DTO
	 * @return the {@see Customer} DTO converted from the created entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/customers")
	public Customer postCustomer(@RequestBody @Valid final Customer customer) {
		log.info(customer.getName());
		if (customer.getName() == null) {
			throw new NameCanNotBeNullError();
		}
		log.info("-------------------------------");
		Customer cust = customerService.createOrUpdateCustomer(customer);
		return cust;
	}

	/**
	 * reacts upon exception
	 * @param response the received response
	 * @throws IOException upon the reception of the bad request
	 */
	@ExceptionHandler(value = {CustomerNotFoundError.class, NameCanNotBeNullError.class})
	void handleBadRequests(HttpServletResponse response, RuntimeException exception) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
	}

}
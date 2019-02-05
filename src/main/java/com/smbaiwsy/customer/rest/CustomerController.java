package com.smbaiwsy.customer.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smbaiwsy.customer.data.CustomerDAO;
import com.smbaiwsy.customer.jaxb.Customer;
/**
 * The rest controller
 * @author ana mattuzzi-stojanovic
 *
 */
@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerDAO customerDAO;

	/*@Autowired
	public CustomerController(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}*/

	/**
	 * the endpoint to get customer by id - should be "/customers/{id}"
	 * @param id the id of the customer
	 * @return the {@see Customer} DTO for the given id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/customer/{id}")
	public Customer getCustomer(@PathVariable(value = "id") long id) {
		log.info("Customers id " + id);
		log.info("-------------------------------");
		Customer customer = customerDAO.findCustomerById(id);
		log.info("After " + customer.getCustomerId());
		if (customer.getCustomerId() == -1L) {
			throw new RuntimeException("Customer not found!");
		}
		return customer;
	}
    
	/**
	 * updates the customer, should be RequestMethod.PUT "/customers/{id}"
	 * @param customerId the id of the customer
	 * @param name the name of the customer 
	 * @param phoneNumber the phone number of the customer
	 * @param email the e-mail address of the customer
	 * @param dob the date of birth of the customer
	 * @return the updated {@see Customer}
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/customer/mend")
	public Customer setCustomer(@RequestParam(value = "customerId", required = false) long customerId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "DOB", required = false) XMLGregorianCalendar dob) {
		log.info(name);
		log.info("-------------------------------");
		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setPhoneNumber(phoneNumber);
		customer.setDOB(dob);
		Customer cust = customerDAO.createOrUpdateCustomer(customer);
		log.info("xmlcalendar 2" + cust.getDOB());
		return cust;
	}

	/**
	 * REST Endpoint to create the customer. Should be "/customers"
	 * @param customer the {@see Customer} DTO
	 * @return the {@see Customer} DTO converted from the created entity
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/customer/add")
	public Customer postCustomer(@RequestBody @Valid final Customer customer) {
		log.info(customer.getName());
		if (customer.getName() == null) {
			throw new RuntimeException("Can't create customer without the name");
		}
		log.info("-------------------------------");
		Customer cust = customerDAO.createOrUpdateCustomer(customer);
		log.info("xmlcalendar 2" + cust.getDOB());
		return cust;
	}

	/**
	 * reacts upon exception
	 * @param response the received response
	 * @throws IOException upon the reception of the bad request
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again and with a non-empty string as 'name'");
	}

}
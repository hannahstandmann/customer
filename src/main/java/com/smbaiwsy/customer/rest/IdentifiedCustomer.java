package com.smbaiwsy.customer.rest;

import com.smbaiwsy.customer.jaxb.Customer;

/**
 * Wrapper for the customer object
 * 
 * @author ana mattuzzi-stojanovic
 *
 */
public class IdentifiedCustomer {
	/**
	 * the id of the customer
	 */
	private long id;
	/**
	 * customer details
	 */
	private Customer customer;

	/**
	 * getter for the id
	 * 
	 * @return the id in the database
	 */
	public long getId() {
		return id;
	}

	/**
	 * setter for the id
	 * 
	 * @param id in the database
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * getter for the customer
	 * 
	 * @return the {@see Customer} DTO
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * setter for the customer
	 * 
	 * @param customer the instance of {@see Customer} DTO
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

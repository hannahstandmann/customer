package com.smbaiwsy.customer.data;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Customer entity
 * 
 * @author ana mattuzzi-stojanovic
 *
 */
@Entity
@Table(name = "Customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String phoneNumber;
	private String email;
	private Date DOB;

	/**
	 * default constructor
	 */
	protected CustomerEntity() {
	}

	/**
	 * overridden constructor
	 * 
	 * @param name the name of the customer
	 */
	public CustomerEntity(String name) {
		this.setName(name);
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, name='%s', phoneNumber='%s', email='%s', DOB='%s']", id, name,
				phoneNumber, email, DOB);
	}

	/**
	 * the id getter
	 * 
	 * @return the database id
	 */
	public long getId() {
		return id;
	}

	/**
	 * the id setter
	 * 
	 * @param id the database id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * the customer name getter
	 * 
	 * @return the customer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * the customer name setter
	 * 
	 * @param name the customer name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * the customer phone number getter
	 * 
	 * @return the customer phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * the customer phone number setter
	 * 
	 * @param phoneNumber the customer phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * the customer e-mail getter
	 * 
	 * @return the customer email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * the customer e-mail setter
	 * 
	 * @param email the customer e-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * the customer date of birth getter
	 * 
	 * @return the customer date of birth
	 */
	public Date getDOB() {
		return DOB;
	}

	/**
	 * the customer date of birth setter
	 * 
	 * @param phoneNumber the customer date of birth
	 */
	public void setDOB(Date dOB) {
		DOB = dOB;
	}

}

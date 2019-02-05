package com.smbaiwsy.customer.data;

import java.util.GregorianCalendar;
import java.util.Optional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smbaiwsy.customer.jaxb.Customer;
import com.smbaiwsy.customer.jaxb.CustomerDetails;
/**
 * Actually the service
 * @author ana mattuzzi-stojanovic
 *
 */
@Component
public class CustomerDAO {
	@Autowired
	private CustomerRepository customerRepository;

	private static final Logger log = LoggerFactory.getLogger(CustomerDAO.class);

	/**
	 * converts java.sql.Date to javax.xml.datatype.XMLGregorianCalendar
	 * 
	 * @param date the date represented as java.sql.Date
	 * @return date represented in other format 
	 */
	public XMLGregorianCalendar toXMLGregorianCalendar(java.sql.Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		return toXMLGregorianCalendar(gCalendar);
	}
	
	/**
	 * converts GregorianCalendar to javax.xml.datatype.XMLGregorianCalendar
	 * 
	 * @param gCalendar the date represented as GregorianCalendar
	 * @return date represented in the XMLGregorianCalendar format 
	 */
	private XMLGregorianCalendar toXMLGregorianCalendar(GregorianCalendar gCalendar) {
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
			log.info("xmlcalendar 1 " + xmlCalendar);
		} catch (DatatypeConfigurationException ex) {
			log.error("Severe", ex);
		}
		return xmlCalendar;
	}

	/**
	 * converts date from XMLGregorianCalendar to java.sql.Data
	 * @param date represented as XMLGregorianCalendar
	 * @return date represented as java.sql.Date
	 */
	public java.sql.Date toSQLDate(XMLGregorianCalendar dateOfBirth) {
		java.util.Date date = dateOfBirth.toGregorianCalendar().getTime();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		log.info("Date" + sqlDate);
		return sqlDate;
	}

	/**
	 * Converts the entity into the Customer DTO 
	 * @param entity the instance of {@see CustomerEntity} found in the database
	 * @return the instance of {@see Customer} DTO
	 */
	public Customer fromEntity(CustomerEntity entity) {
		Customer customer = new Customer();
		if (entity != null) {
			customer.setCustomerId(entity.getId());
			customer.setName(entity.getName());
			customer.setPhoneNumber(entity.getPhoneNumber());
			customer.setEmail(entity.getEmail());
			customer.setDOB(toXMLGregorianCalendar(
					Optional.ofNullable(entity.getDOB()).orElse(new java.sql.Date(System.currentTimeMillis()))));
		} else {
			customer.setCustomerId(-1L);
		}
		return customer;
	}

	/**
	 * Creates new or updates an existing {@see CustomerEntity}
	 * @param customer the {@see Customer} DTO
	 * @return {@see Customer} DTO converted from an existing {@see CustomerEntity} or from the received object
	 */
	public Customer createOrUpdateCustomer(Customer customer) {
		CustomerEntity entity = null;
		if (customer.getCustomerId() != 0) {
			entity = customerRepository.getOne(customer.getCustomerId());
		} else {
			entity = new CustomerEntity(customer.getName());
		}
		log.info("entity created or found");
		entity.setPhoneNumber(customer.getPhoneNumber());
		entity.setEmail(customer.getEmail());
		XMLGregorianCalendar dateOfBirth = customer.getDOB();
		if (dateOfBirth != null) {
			entity.setDOB(toSQLDate(dateOfBirth));
		}
		entity = customerRepository.save(entity);
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (CustomerEntity cust : customerRepository.findAll()) {
			log.info(cust.toString());
		}
		log.info("");
		return fromEntity(entity);
	}

	/**
	 * finds the {@see Customer} with the given id
	 * @param id the id of the customer
	 * @return the {@see Customer} of the found customer
	 */
	public Customer findCustomerById(long id) {
		Optional<CustomerEntity> entity = customerRepository.findById(id);
		//return entity.map(o -> fromEntity(o))
	     // .orElseThrow(CustomerNotFoundError::new);
		if (entity.isPresent()) {
			return fromEntity(entity.get());
		}
		return fromEntity(null);
		
	}
	/**
	 * creates the customer object from the details object
	 * @param details a {@see CustomerDetails} object
	 * @return customer a corresponding {@see Customer} object 
	 */
	public Customer fromCustomerDetails(CustomerDetails details) {
		Customer customer = new Customer();
		if (details.getCustomerId() != null && details.getCustomerId() > 0) {
			customer.setCustomerId(details.getCustomerId());
		}
		customer.setName(details.getName());
		if (details.getPhoneNumber() != null) {
			customer.setPhoneNumber(details.getPhoneNumber());
		}
		if (details.getEmail() != null) {
			customer.setEmail(details.getEmail());
		}
		if (details.getDOB() != null) {
			customer.setDOB(details.getDOB());
		}
		return customer;
	}
}

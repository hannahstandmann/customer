package com.smbaiwsy.customer.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * The Customer repository
 * @author ana mattuzzi-stojanovic
 *
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    /**
     * finds customer by name
     * @param name the name of the customer the 
     * @return the list of {@see CustomerEntity}-es of the customers with the given name
     */
	List<CustomerEntity> findByName(String name);
}

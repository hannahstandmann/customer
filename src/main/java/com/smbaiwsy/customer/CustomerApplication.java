package com.smbaiwsy.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.smbaiwsy.customer.data.CustomerEntity;
import com.smbaiwsy.customer.data.CustomerRepository;
/**
 * The main application and the database initializer
 * @author ana mattuzzi-stojanovic
 *
 */
@SpringBootApplication
public class CustomerApplication {

	
	private static final Logger log = LoggerFactory.getLogger(CustomerApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(CustomerApplication.class, args);
	}
	
	/**
	 * Database initializer
	 * @param repository the repository of all customers
	 * @return Callback used to run the bean
	 */
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new CustomerEntity("Chuck Bartowski"));
			repository.save(new CustomerEntity("Sarah Walker"));
			repository.save(new CustomerEntity("Morgan Grimes"));
			repository.save(new CustomerEntity("Ellie Bartowski"));
			repository.save(new CustomerEntity("Devon Woodcomb"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (CustomerEntity customer : repository.findAll()) {
				log.info(customer.toString());
			}
            log.info("");

			// fetch an individual customer by ID
			CustomerEntity customer = repository.getOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
            log.info("");

			// fetch customers by last name
			log.info("Customer found with findByName('Chuck Bartowski')");
			log.info("--------------------------------------------");
			for (CustomerEntity bauer : repository.findByName("Chuck Bartowski")) {
				log.info(bauer.toString());
			}
            log.info("--------------------------------------------");
		};
	}

}


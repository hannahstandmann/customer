//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.06 at 04:31:19 PM CET 
//


package com.smbaiwsy.customer.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.smbaiwsy.customer.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.smbaiwsy.customer.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomerDetailsResponse }
     * 
     */
    public GetCustomerDetailsResponse createGetCustomerDetailsResponse() {
        return new GetCustomerDetailsResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link SetCustomerDetailsRequest }
     * 
     */
    public SetCustomerDetailsRequest createSetCustomerDetailsRequest() {
        return new SetCustomerDetailsRequest();
    }

    /**
     * Create an instance of {@link GetCustomerDetailsRequest }
     * 
     */
    public GetCustomerDetailsRequest createGetCustomerDetailsRequest() {
        return new GetCustomerDetailsRequest();
    }

    /**
     * Create an instance of {@link SetCustomerDetailsResponse }
     * 
     */
    public SetCustomerDetailsResponse createSetCustomerDetailsResponse() {
        return new SetCustomerDetailsResponse();
    }

}

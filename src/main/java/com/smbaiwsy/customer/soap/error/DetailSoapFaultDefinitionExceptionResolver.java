package com.smbaiwsy.customer.soap.error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;

import com.smbaiwsy.customer.soap.CustomerEndpoint;
/**
 * An instance of {@see SoapFaultAnnotationExceptionResolver}
 * @author ana mattuzzi-stojanovic
 *
 */
@Component
public class DetailSoapFaultDefinitionExceptionResolver extends
        SoapFaultAnnotationExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(CustomerEndpoint.class);

    public DetailSoapFaultDefinitionExceptionResolver() {
        super();
    }

    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
    	log.debug("TEST OK !");
    }

}

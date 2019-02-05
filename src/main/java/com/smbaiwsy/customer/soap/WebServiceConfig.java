package com.smbaiwsy.customer.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Web service configuration
 * 
 * @author ana mattuzzi-stojanovic
 *
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	/**
	 * registers an Endpoint for the SOAP web service
	 * 
	 * @param applicationContext an instance of {@ApplicationContext}
	 * @return an instance of the {@see ServletRegistrationBean}
	 */
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}

	/**
	 * configures the SOAP Endpoint
	 * 
	 * @param customerSchema the xsd schema used to define requests and responses
	 * @return an instance of {@see DefaultWsdl11Definition} bean
	 */
	@Bean(name = "customer")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customerSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CustomerPort");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://smbaiwsy.com/2016/complete-task");
		wsdl11Definition.setSchema(customerSchema);
		return wsdl11Definition;
	}

	/**
	 * the the xsd schema used to define requests and responses
	 * 
	 * @return the wrapper object for the XSD Schema
	 */
	@Bean
	public XsdSchema customerSchema() {
		return new SimpleXsdSchema(new ClassPathResource("customer.xsd"));
	}

}
package com.smbaiwsy.customer.rest;

//import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * a simple implementation of an error controller
 * @author ana mattuzzi-stojanovic
 *
 */
public class MaskWhiteLabelErrorController implements ErrorController{
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Greshka";
    }

	@Override
	public String getErrorPath() {
		return PATH;
	}

}

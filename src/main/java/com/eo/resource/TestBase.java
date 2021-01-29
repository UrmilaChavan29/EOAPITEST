package com.eo.resource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	public static RequestSpecification httpRequest;
	public static Response response;
	public static Logger log=LogManager.getLogger(TestBase.class.getName());

	public void setup(){
		
	//	DOMConfigurator.configure("log4j2.xml"); //added logger
		//DMConfigurator.configure("log4j.xml");
		//log.atLevel(Level.TRACE);
		
	}
}

package com.eo.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetUsersPrintHeaders {
	public static Logger log=LogManager.getLogger(GetUsersPrintHeaders.class);

	@Test
	  public void getAllHeadersPrint() {
		log.debug("Test");
		
		//Specify base URI
		RestAssured.baseURI ="https://reqres.in";
		log.info("Requested endpoint");
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET, "/api/users");
		log.info("Pass Request method with URI");
		//print response in console window
		String responseBody=response.getBody().asString();
		log.info("Response body is"+ responseBody);
		//verify status code
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		//Capture all headers from response
		Headers allHeaders=response.headers();
		for (Header header:allHeaders) {
			System.out.println(header.getName()+": "+header.getValue());
		}	
		//Validate headers
		//capture details of header content type
		String contentType=response.header("Content-Type");
		log.info("Header Content Encoding value is :"+ contentType);
		Assert.assertEquals("application/json; charset=utf-8", contentType);
		//capture details of header transfer Encoding
		String transferEn=response.header("Transfer-Encoding");
		log.info("Header Content Encoding value is :"+ transferEn);
		Assert.assertEquals("chunked", transferEn);
		//capture details of header content encoding
		String contentEncoding=response.header("Content-Encoding");
		log.info("Header Content Encoding value is :"+ contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);	
		log.info("All headers printed and validated");
		
	}
}

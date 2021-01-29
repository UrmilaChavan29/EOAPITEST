package com.eo.tests;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetUsers {	
	public static Logger log=LogManager.getLogger(GetUsers.class);
	
	
	@BeforeMethod
	public void setUp() 
	{			
		//Specify base URI
		RestAssured.baseURI ="https://reqres.in";
		log.info("Base URI requested");
	}
	
	@Test(priority=0)
	public void getUsersValidEP_TC001() 
	{				
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET, "/api/users");
		//print response in console window
		String responseBody=response.getBody().asString();
		log.info("Response body is"+ responseBody);
		//verify status code
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		//verify object total=12;
		int total=response.jsonPath().get("total");
		log.info("Value of object parameter total:"+ total);
		Assert.assertEquals(total, 12);
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
		log.info("Validated Get users request with valid Endpoint");
	}
	
	@Test(priority=1)
	  public void getUsersCount_TC002() {
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET, "/api/users");
		JsonPath jsonPathEvaluator = response.jsonPath();
		//list all objects
		List<Integer> allIds = jsonPathEvaluator.getList("data.id");
		log.info(allIds.size());	
		for(int id:allIds) {
			System.out.println("Id present:"+ id);
		}
		Assert.assertEquals(allIds.size(), 12);
		System.out.println("Total users count should be 12");
		//Verify status code response
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 404);
		log.info("Validated all users count in requested endpoint");
	}
	
	@Test(priority=2)
	  public void getUsersIncorrectEP_TC003() {
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET, "/api/users123");
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 404);
		log.info("404 error response when requested incorrect endpoint");
	}
	
}
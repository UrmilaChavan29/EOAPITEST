package com.eo.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetUsers {	
	@BeforeMethod
	public void setUp() 
	{			
		//Specify base URI
		RestAssured.baseURI ="https://reqres.in";
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
		System.out.println("Response body is"+ responseBody);
		//verify status code
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		//verify object total=12;
		int total=response.jsonPath().get("total");
		System.out.println("Value of object parameter total:"+ total);
		Assert.assertEquals(total, 12);
		//Validate headers
		//capture details of header content type
		String contentType=response.header("Content-Type");
		System.out.println("Header Content Encoding value is :"+ contentType);
		Assert.assertEquals("application/json; charset=utf-8", contentType);
		//capture details of header transfer Encoding
		String transferEn=response.header("Transfer-Encoding");
		System.out.println("Header Content Encoding value is :"+ transferEn);
		Assert.assertEquals("chunked", transferEn);
		//capture details of header content encoding
		String contentEncoding=response.header("Content-Encoding");
		System.out.println("Header Content Encoding value is :"+ contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);		
	}
	
	@Test(priority=1)
	  public void getUsersCount_TC002() {
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET, "/api/users");
		JsonPath jsonPathEvaluator = response.jsonPath();
		//list all objects
		List<Integer> allIds = jsonPathEvaluator.getList("data.id");
		System.out.println(allIds.size());	
		for(int id:allIds) {
			System.out.println("Id present:"+ id);
		}
		Assert.assertEquals(allIds.size(), 12);
		System.out.println("Total users count should be 12");
		//Verify status code response
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 404);
	}
	
	@Test(priority=2)
	  public void getUsersIncorrectEP_TC003() {
		RequestSpecification httpRequest=RestAssured.given();
		//Response object
		Response response=httpRequest.request(Method.GET, "/api/users123");
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 404);
	}
	
}
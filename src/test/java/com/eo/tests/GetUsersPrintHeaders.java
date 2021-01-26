package com.eo.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class GetUsersPrintHeaders {

	@Test
	  public void getAllHeadersPrint() {
		//Specify base URI
		RestAssured.baseURI ="https://reqres.in";
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
		Assert.assertEquals(200, statusCode);
		//Capture all headers from response
		Headers allHeaders=response.headers();
		for (Header header:allHeaders) {
			System.out.println(header.getName()+": "+header.getValue());
		}	
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
}

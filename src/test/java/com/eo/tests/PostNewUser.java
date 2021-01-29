package com.eo.tests;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.eo.util.XUtility;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostNewUser {
	public static Logger log=LogManager.getLogger(PostNewUser.class);
	
	@BeforeMethod
	public void setUp() {
		//Specify base URI
				RestAssured.baseURI ="https://reqres.in";
				log.info("Base URI requested");
				
		}
	@Test(priority=0, dataProvider="getUserData_TC004")
	  public void createNewUser_TC004(String userName, String userJob) {	
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestPara=new JSONObject();
		requestPara.put("name", userName);
		requestPara.put("job", userJob);
		httpRequest.header("Content-Type", "application/json");
		log.info("Request parameters and header added");
		httpRequest.body(requestPara.toJSONString());
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users");
		//print response in console window
		String responseBody=response.getBody().asString();
		log.info("Response body is"+ responseBody);
		//verify status code
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(201, statusCode);
		log.info("Verified status code.");
		//verify id generated for data
		String idValue=response.jsonPath().get("id");
		Assert.assertNotNull(idValue);
		log.info("Verified 'id' generated for post request in response body");
		//verify body post is created in response
		Assert.assertTrue(response.jsonPath().get("name").equals(userName));
		Assert.assertTrue(response.jsonPath().get("job").equals(userJob));
		log.info("Verified posted request parameters in response body");
		//Verify headers
		String contentType=response.header("Content-Type");
		Assert.assertEquals("application/json; charset=utf-8", contentType);
		log.info("Verified header value in response body");	
	  }
	
	@Test(priority=1)
	  public void createNewUserIncorrectEP_TC005() {
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestPara=new JSONObject();
		//hard coding parameter values
		requestPara.put("name", "xyz");
		requestPara.put("job", "tester");
		httpRequest.header("Content-Type", "application/json");
		log.info("Request parameters and header added");
		httpRequest.body(requestPara.toJSONString());
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users123");
		log.info("Incorrect endpoint requested");
		//verify status code
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 404);
		log.info("Verified that new user (post request) is not created with incorrect endpoint address");
	}
	
	@Test (priority=2, dataProvider="getUserData_TC006")
	  public void createNewUserIncorrectParamValues_TC006_007(Object name, Object job) {
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		JSONObject requestPara=new JSONObject();
		requestPara.put("name", name);
		requestPara.put("job", job);
		log.info("Incorrect request parameters (integer values/ special characters instead of sring object) added into body");
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestPara.toJSONString());
		log.info("Request parameters and header added");
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users");
		//verify status code
		int statusCode=response.getStatusCode();
		log.info("Status code: " + statusCode);
		Assert.assertEquals(statusCode, 400);
		//verify id not generated for data
		String idValue=response.jsonPath().get("id");
		Assert.assertNull(idValue);	
		log.info("Validated that incorrect request parameter type objects are not accepted");
	}
	
	//Read data from excel
	@DataProvider 
		String [][] getUserData_TC004() throws IOException {
		String path="C:/Users/Asus/workspace/RestAssuredAPITestEO/src/main/java/com/eo/util/DataUtil.xlsx";
		System.out.println("File Path = "+ path);
		int rownum=XUtility.getRowCount(path, "Sheet1");
		int columncount=XUtility.getCellCount(path, "Sheet1", 1);
		
		String data[][]=new String[rownum][columncount];
		for(int i=1; i<=rownum; i++) {			
			for(int j=0; j< columncount; j++) {			
				data[i-1][j]=XUtility.getCellData(path, "Sheet1",i,j);
				log.info("Iterate each value from excel cell"+ XUtility.getCellData(path, "Sheet1",i,j));
			}
		}
		return data;
	}
	
	@DataProvider	
	Object[][] getUserData_TC006(){
		Object data[][]=  {{123,456},{"$%*", "@&^"}};
		return data;
		}
	
	/*
	  //Using DataProvider hard code the values here
	@DataProvider	
		String[][] getUserData_TC004(){
		String userData[][]=  {{"xyz","tester"},{"Sachin","Developer"},{"Nilesh","Admin"}};
		return userData;
		}
		*/
		
	
}

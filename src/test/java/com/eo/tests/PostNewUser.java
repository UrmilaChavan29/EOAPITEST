package com.eo.tests;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostNewUser {
	//static ExcelUtils excelUtils = new ExcelUtils();
	@BeforeMethod
	public void setUp() {
		//Specify base URI
				RestAssured.baseURI ="https://reqres.in";
		}
	@Test(priority=0, dataProvider="getUserData")
	  public void createNewUser_TC004(String userName, String userJob) {	
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestPara=new JSONObject();
		
		requestPara.put("name", userName);
		requestPara.put("job", userJob);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestPara.toJSONString());
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users");
		
		//print response in console window
		String responseBody=response.getBody().asString();
		System.out.println("Response body is"+ responseBody);
		
		//verify status code
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(201, statusCode);
		//verify id generated for data
		String idValue=response.jsonPath().get("id");
		Assert.assertNotNull(idValue);
		//verify body post is created in response
		Assert.assertTrue(response.jsonPath().get("name").equals(userName));
		Assert.assertTrue(response.jsonPath().get("job").equals(userJob));
		//Verify headers
		String contentType=response.header("Content-Type");
		Assert.assertEquals("application/json; charset=utf-8", contentType);
				
	  }
	
	@Test(priority=1)
	  public void createNewUserIncorrectEP_TC005() 
	{	
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestPara=new JSONObject();
		
		requestPara.put("name", "xyz");
		requestPara.put("job", "tester");
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestPara.toJSONString());
		
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users123");
		
		//verify status code
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(404, statusCode);					
	}
	
	@Test(priority=2)
	  public void createNewUserIncorrectParamValuesInteger_TC006() 
	{	
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestPara=new JSONObject();
		
		requestPara.put("name", 123);
		requestPara.put("job", 456);
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestPara.toJSONString());
		
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users");
		
		//verify status code
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(400, statusCode);
		//verify id not generated for data
		String idValue=response.jsonPath().get("id");
		Assert.assertNull(idValue);			
	}
	
	@Test(priority=3)
	  public void createNewUserIncorrectParamValuesSpecChar_TC007() {	
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		JSONObject requestPara=new JSONObject();
		
		requestPara.put("name", "$%*");
		requestPara.put("job", "@&^");
		
		httpRequest.header("Content-Type", "application/json");
		
		httpRequest.body(requestPara.toJSONString());
		
		//Response object
		Response response=httpRequest.request(Method.POST, "/api/users");
		
		//verify status code
		int statusCode=response.getStatusCode();
		System.out.println("Status code: " + statusCode);
		Assert.assertEquals(400, statusCode);
		//verify id not generated for data
		String idValue=response.jsonPath().get("id");
		Assert.assertNull(idValue);			
	}

	@DataProvider
	
	//Using DataProvider hard code the values here

	String[][] getUserData(){
	String userData[][]=  {{"xyz","tester"}};
	return userData;
	}
	
	//Read data from excel
	/* 
		String [][] getUserDa() throws IOException {
		String path="C:/Users/Asus/workspace/RestAssuredAPITestEO/src/main/java/com/eo/util/DataUtil.xlsx";
		System.out.println("File Path = "+ path);
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int columncount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String data[][]=new String[rownum][columncount];
		for(int i=0; i<=rownum; i++) {
			for(int j=0; j< columncount; j++) {
				data[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return data;
		
	}
	*/
}

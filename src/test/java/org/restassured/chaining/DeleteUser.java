package org.restassured.chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class DeleteUser {
	
	@Test
	void testGetUser(ITestContext context) {
				
		String bearerToken = "thisIsSomeRandomBearerToken";
		
			
		int id =(int) context.getSuite().getAttribute("user_Id");
		
		given()
			.header("Authorization", "Bearer " +bearerToken )
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
			
		
		
	}

}

package org.restassured.chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import net.minidev.json.JSONObject;

public class GetUser {
	
	@Test
	void testGetUser(ITestContext context) {
				
		String bearerToken = "thisIsSomeRandomBearerToken";
		
			
		int id =(int) context.getSuite().getAttribute("user_Id");
		
		given()
			.header("Authorization", "Bearer " +bearerToken )
			.pathParam("id", id)

		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
			
		
		
	}

}

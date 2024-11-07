package org.restassured.chaining;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import net.minidev.json.JSONObject;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class UpdateUser {
	
	
	@Test
	void testUpdateUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().firstName());
		data.put("gender", "male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "thisIsSomeRandomBearerToken";
		
		int id =(int) context.getSuite().getAttribute("user_Id");
		
		 given()
			.header("Authorization", "Bearer " +bearerToken )
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id", id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(203)
			.log().all();
		
		
	}
	

}

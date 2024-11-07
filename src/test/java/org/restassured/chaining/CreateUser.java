package org.restassured.chaining;

import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreateUser {

	
	@Test
	void testSignUp(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().firstName());
		data.put("gender", "female");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "thisIsSomeRandomBearerToken";
		
		
		int id = given()
			.header("Authorization", "Bearer " +bearerToken )
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		context.getSuite().setAttribute("user_Id", id);
	}
	
}

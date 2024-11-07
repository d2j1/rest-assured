	package org.restassured;
	
	import static io.restassured.RestAssured.*;
	import static io.restassured.matcher.RestAssuredMatchers.*;
	import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;
	

public class HttpRequests {
	
	int id=0;
		
		@Test(priority = 0)
		void getUsers() {
//				
				given()
				.when()
					.get("https://reqres.in/api/users?page=2")
				
				.then()
					.statusCode(200)
					.body("page",equalTo(2))
					.log().all();
		}
		
		@Test(priority = 1)
		void createUser() {
			
			HashMap<String, String> data = new HashMap<>();
			
			data.put("name", "morpheus");
			data.put("job", "leader");
			
//			given()
//				.contentType("application/json")
//				.body(data)
//			
//			.when()
//				.post("https://reqres.in/api/users")
//				
//			.then()
//				.statusCode(201)
//				.log().all();
			
			id = given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
			System.out.println("so the value of the id is " +id);
		}
		
		@Test(priority = 2, dependsOnMethods = {"createUser"})
		void updateUser() {
				
			HashMap<String, String> data = new HashMap<>();
			data.put("name", "morpheus");
			data.put("job", "Bablu Tablu");
			
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.put("https://reqres.in/api/users/"+id)
			.then()
				.statusCode(200)
				.log().all();
		}
		
		
		@Test(priority = 3, dependsOnMethods = {"createUser", "updateUser"})
		void deleteUser() {
			
			given()
				
			.when()
				.delete("https://reqres.in/api/users/"+id)
			
			.then()
				.statusCode(204)
				.log().all();
		}
		
	
		
		
}



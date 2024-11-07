package org.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONTokener;
import org.testng.annotations.Test;

import org.json.JSONObject;


public class MultipleWaysToCreateRequestBody {
	
	@Test(priority=1)
	void testPostWithHashMap() {
		
		HashMap data = new HashMap<>();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
		
	}
	
	@Test
	void testPostusingJSONLibrary() {
			
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
		given()
		.contentType("application/json")
		.body(data.toString())
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	
		
	}
	
	@Test
	void testPostUsingPojoClass() {
			
		Pojo_post_class data = new Pojo_post_class();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhoneNo("123456");
		data.setCourses(new String[]{"C", "C++"});

		
		given()
		.contentType("application/json")
		.body(data)
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	
		
	}
	

	@Test
	void testPostUsingExternalJsonFile() throws FileNotFoundException {
			
		File file = new File(".\\body.json");
		FileReader reader = new FileReader(file);
		
		JSONTokener jt = new JSONTokener(reader);
		
		JSONObject data = new JSONObject(jt);

		
		given()
		.contentType("application/json")
		.body(data.toString())
	.when()
		.post("http://localhost:3000/students")
	.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]", equalTo("C"))
		.body("courses[1]", equalTo("C++"))
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	
		
	}
	
	
	@Test( priority = 2)
	void deleteStudentWithID() {
		
		given()
			.contentType("application/json")
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(203)
			.log().all();
	}

}

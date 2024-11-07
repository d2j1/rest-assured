package org.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class HeadersDemo {
	
	@Test
	void testHeaders() {
		
		given()
		.when()
			.get("https://www.google.com")
			.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws");

		/*
		 or we can do like below
		 
		 		given()
		.when()
			.get("https://www.google.com")
			.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
		 
		 
		 
		 */
	}
	
	@Test
	void getHeaders() {
		
		 Response res = given()
		.when()
			.get("https://www.google.com");

//		  get single header information
		 String headerValue = res.getHeader("Content-Type");
		 System.out.println("The value of content type header is " +headerValue);
		 
		 // get all the headers
		 Headers headers = res.getHeaders();
		
		 for( Header hd : headers) {
			 System.out.println(hd.getName() + " : " +hd.getValue() + " : " +hd.getClass());
		 }
	}

}

package org.restassured;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {
	
	@Test
	void testCookies() {
		
		given()
		.when()
			.get("https://www.google.com")
			.then()
				.cookie("AEC","asjkdfasjkdfhasdfkasdfjkasdfuieafbsa")
				.log().all();
		
//		here we are verifying if cookie AEC has value same as provided or not
//		 this test should fail cookcie value of the AEC keeps changing each time we re load the page
	}
	
	@Test
	void getCookieInfo() {
		
		Response response = given()
		.when()
			.get("https://www.google.com");
		
		// getting single cookie info 
		String cookie = response.getCookie("AEC");
		System.out.println("Value of cookie is " +cookie);
		
		// get all cookies info
		
			Map<String, String> cookies = response.getCookies();
			
			for( Map.Entry<String, String> entry : cookies.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
		
			for( String key : cookies.keySet()) {
				System.out.println( key+ " : " + response.getCookie(key));
			}
		
	}

}

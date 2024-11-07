package org.restassured;

import org.testng.annotations.Test;

import io.restassured.http.Headers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;




public class Authentications {
	
	@Test
	void BasicAuth() {
		
		given()
			.auth().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
			
	}
	
	@Test
	void DigestAuth() {
		
		given()
			.auth().digest("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
			
	}

	@Test
	void preemptiveAuth() {
		
		given()
			.auth().preemptive().basic("postman","password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
			
	}
	
	@Test
	void bearerTokenAuth() {
		
		String bearerToken = "askdfjhasdgfklhdghafotwrajnf_asdfkhasdjf_gsdfrgoier";
		

		
		given()
			.headers("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/users/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testOAuth1() {
		String consumerKey = "dummyConsumerKey123";
		String consumerSecret = "dummyConsumerSecret456";
		String accessToken = "dummyAccessToken789";
		String tokenSecret = "dummyTokenSecret012";
		
		
		given()
			.auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
			
			.when()
				.get("some url")
			.then()
				.statusCode(200)
				.log().all();
	

	}
	
	@Test
	void testOAuth2() {
		String oAuth2Token = "SomeRandomTokenValue";
		
		
		given()
			.auth().oauth2(oAuth2Token)
			
			.when()
				.get("some url")
			.then()
				.statusCode(200)
				.log().all();
	

	}
	
	@Test
	void testAPIKeyMethod1() {
		
		given()
			.queryParam("apikey", "SomeRandomAPIKeyToAccessTheResource")
		.when()
			.get("api.openweather.com/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testAPIKeyMethod2() {
		
		given()
			.queryParam("apikey", "SomeRandomAPIKeyToAccessTheResource")
			.pathParam("myPath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", 7)
		.when()
			.get("api.openweather.com/{myPath}")
		.then()
			.statusCode(200)
			.log().all();
	}

}

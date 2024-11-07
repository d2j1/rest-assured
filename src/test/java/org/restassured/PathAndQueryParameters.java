package org.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {
	
	
	@Test
	void testPathAndQueryParameters() {
		
//		https://reqres.in/api/users?page=2&id=5

given()
    .pathParam("myPath","users")
    .queryParam("page",2)
    .queryParam("id",5)
.when()
    .get("https://reqres.in/api/{myPath}")
.then()
	.statusCode(200)
	.log().all();
		
	}

}

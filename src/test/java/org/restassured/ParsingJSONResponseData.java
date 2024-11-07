package org.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;


public class ParsingJSONResponseData {
	
	@Test
	void testJsonResponse() {
		
		given()
			.contentType("ContentType.JSON")
			
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[2].title", equalTo("The Lord Of The Ring"));
		
	}
	
	@Test
	void testJsonResponseApproach2() {
		
	
		Response response = given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8" );
		Assert.assertEquals(response.getStatusCode(), 200);
		
		String bookName	 = response.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord Of The Rings");
	}

	
	@Test
	void testJsonResponseApproach2Example2() {
		
		/*
		 response body data - 
		 
		 {
  "books": [
    {
      "title": "To Kill a Mockingbird",
      "author": "Harper Lee",
      "yearPublished": 1960,
      "genre": "Fiction",
      "ISBN": "9780061120084"
    },
    {
      "title": "1984",
      "author": "George Orwell",
      "yearPublished": 1949,
      "genre": "Dystopian",
      "ISBN": "9780451524935"
    },
    {
      "title": "Pride and Prejudice",
      "author": "Jane Austen",
      "yearPublished": 1813,
      "genre": "Romance",
      "ISBN": "9780141040349"
    },
    {
      "title": "The Catcher in the Rye",
      "author": "J.D. Salinger",
      "yearPublished": 1951,
      "genre": "Literature",
      "ISBN": "9780316769488"
    },
    {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "yearPublished": 1925,
      "genre": "Classic",
      "ISBN": "9780743273565"
    }
  ]
}

		 
		 
		 
		 */
		Response response = given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
		
		
		// getting data in the json object
		JSONObject jo = new JSONObject(response.toString());
		
//		print the titles of all the books
		for( int i =0; i < jo.getJSONArray("book").length(); i++) {
			String bookName = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.print(bookName);
		}
		
		// checking if particular book is present or not
		boolean status = false;
		for( int i =0; i < jo.getJSONArray("book").length(); i++) {
			String bookName = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			if( bookName.equals("The Great Gatsby")) {
				status = true;
				break;
			}
		}
		
		
		Assert.assertTrue(status);
		
		// or we can write
		Assert.assertEquals(status, true);
		
		
	}
	
	@Test
	void testJsonResponseApproach2Example3() {
		
		/*
		 response body data - 
		 
		 {
  "books": [
    {
      "title": "To Kill a Mockingbird",
      "author": "Harper Lee",
      "yearPublished": 1960,
      "genre": "Fiction",
      "ISBN": "9780061120084",
      "price":"100"
    },
    {
      "title": "1984",
      "author": "George Orwell",
      "yearPublished": 1949,
      "genre": "Dystopian",
      "ISBN": "9780451524935",
      "price":"100"
    },
    {
      "title": "Pride and Prejudice",
      "author": "Jane Austen",
      "yearPublished": 1813,
      "genre": "Romance",
      "ISBN": "9780141040349",
      "price":"100"
    },
    {
      "title": "The Catcher in the Rye",
      "author": "J.D. Salinger",
      "yearPublished": 1951,
      "genre": "Literature",
      "ISBN": "9780316769488",
      "price":"100"
    },
    {
      "title": "The Great Gatsby",
      "author": "F. Scott Fitzgerald",
      "yearPublished": 1925,
      "genre": "Classic",
      "ISBN": "9780743273565",
      "price":"100"
    }
  ]
}

		 
		 */
		
		
		
		Response response = given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/store");
		
		
		// getting data in the json object
		JSONObject jo = new JSONObject(response.toString());

		// checking if particular book is present or not
		int totalPrice =0;
		for( int i =0; i < jo.getJSONArray("book").length(); i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalPrice = totalPrice + Integer.parseInt(price);
		}
		
		
		Assert.assertEquals(totalPrice, 500);
		
		
	}

}

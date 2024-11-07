package org.restassured;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import io.restassured.module.jsv.JsonSchemaValidator;



public class JSONSchemaValidation {

	/*
	 Response data in json format - 
	 
	 {
  "products": [
    {
      "id": 1,
      "name": "Tractor",
      "description": "Powerful tractor for farming needs, suitable for large-scale agriculture.",
      "category": "Machinery",
      "price": 25000,
      "availableForRent": true,
      "rentPricePerDay": 500,
      "location": {
        "country": "India",
        "state": "Maharashtra",
        "district": "Pune",
        "village": "Shirwal"
      },
      "seller": {
        "name": "Farmer John",
        "contact": "+91-9876543210",
        "email": "farmer.john@example.com"
      },
      "dateListed": "2024-11-01",
      "images": [
        "https://example.com/images/tractor1.jpg",
        "https://example.com/images/tractor2.jpg"
      ]
    },
    {
      "id": 2,
      "name": "Irrigation Pump",
      "description": "Efficient irrigation pump for small and medium-sized farms.",
      "category": "Machinery",
      "price": 15000,
      "availableForRent": false,
      "rentPricePerDay": null,
      "location": {
        "country": "India",
        "state": "Punjab",
        "district": "Ludhiana",
        "village": "Jalandhar"
      },
      "seller": {
        "name": "Green Fields Co.",
        "contact": "+91-9988776655",
        "email": "contact@greenfields.com"
      },
      "dateListed": "2024-10-29",
      "images": [
        "https://example.com/images/pump1.jpg",
        "https://example.com/images/pump2.jpg"
      ]
    },
    {
      "id": 3,
      "name": "Organic Fertilizer",
      "description": "Natural fertilizer for soil enrichment and plant growth.",
      "category": "Supplies",
      "price": 800,
      "availableForRent": false,
      "rentPricePerDay": null,
      "location": {
        "country": "India",
        "state": "Uttar Pradesh",
        "district": "Agra",
        "village": "Fatehpur Sikri"
      },
      "seller": {
        "name": "Agro Supplies Inc.",
        "contact": "+91-9123456789",
        "email": "sales@agrosupplies.com"
      },
      "dateListed": "2024-10-20",
      "images": [
        "https://example.com/images/fertilizer.jpg"
      ]
    }
  ]
}

	 */
	
	
	@Test
	void JsonSchemaValidation() {
		
		given()
		
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json_schema.json"));
		
	}
}

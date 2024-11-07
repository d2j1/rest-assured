package org.restassured;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {
	
/*
 
 <?xml version="1.0" encoding="UTF-8"?>
<response>
    <status>success</status>
    <message>Products retrieved successfully</message>
    <products>
        <product>
            <id>1</id>
            <name>Tractor</name>
            <description>Powerful tractor for farming needs, suitable for large-scale agriculture.</description>
            <category>Machinery</category>
            <price currency="USD">25000</price>
            <availableForRent>true</availableForRent>
            <rentPricePerDay currency="USD">500</rentPricePerDay>
            <location>
                <country>India</country>
                <state>Maharashtra</state>
                <district>Pune</district>
                <village>Shirwal</village>
            </location>
            <seller>
                <name>Farmer John</name>
                <contact>+91-9876543210</contact>
                <email>farmer.john@example.com</email>
            </seller>
            <dateListed>2024-11-01</dateListed>
            <images>
                <image>https://example.com/images/tractor1.jpg</image>
                <image>https://example.com/images/tractor2.jpg</image>
            </images>
        </product>
        <product>
            <id>2</id>
            <name>Irrigation Pump</name>
            <description>Efficient irrigation pump for small and medium-sized farms.</description>
            <category>Machinery</category>
            <price currency="USD">15000</price>
            <availableForRent>false</availableForRent>
            <location>
                <country>India</country>
                <state>Punjab</state>
                <district>Ludhiana</district>
                <village>Jalandhar</village>
            </location>
            <seller>
                <name>Green Fields Co.</name>
                <contact>+91-9988776655</contact>
                <email>contact@greenfields.com</email>
            </seller>
            <dateListed>2024-10-29</dateListed>
            <images>
                <image>https://example.com/images/pump1.jpg</image>
                <image>https://example.com/images/pump2.jpg</image>
            </images>
        </product>
        <product>
            <id>3</id>
            <name>Organic Fertilizer</name>
            <description>Natural fertilizer for soil enrichment and plant growth.</description>
            <category>Supplies</category>
            <price currency="USD">800</price>
            <availableForRent>false</availableForRent>
            <location>
                <country>India</country>
                <state>Uttar Pradesh</state>
                <district>Agra</district>
                <village>Fatehpur Sikri</village>
            </location>
            <seller>
                <name>Agro Supplies Inc.</name>
                <contact>+91-9123456789</contact>
                <email>sales@agrosupplies.com</email>
            </seller>
            <dateListed>2024-10-20</dateListed>
            <images>
                <image>https://example.com/images/fertilizer.jpg</image>
            </images>
        </product>
    </products>
</response>
 
 */
	
	@Test
	void xmlValidation() {
		
		given()
		
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xml_schema.xsd"));
	}

}

package org.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	
	
	/* 
	 
	 XML response data - 
	 
	 <TravelerinformationResponse xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <page>1</page>
    <per_page>10</per_page>
    <totalrecord>22975</totalrecord>
    <total_pages>2298</total_pages>
    <travelers>
        <Travelerinformation>
            <id>32461</id>
            <name>Vijay Bharath Reddy</name>
            <email>vijaybharaths@gmail.com</email>
            <address>USA</address>
            <createdat>2021-02-22T05:46:31.9708791</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>32471</id>
            <name>Vinod Sharma</name>
            <email>ankuz225sharma@gmail.com</email>
            <address>USA</address>
            <createdat>2021-02-22T07:55:38.0586604</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>32491</id>
            <name>Marco Ordoñez</name>
            <email>marco.ordonez@outlook.com.pe</email>
            <address>USA</address>
            <createdat>2021-02-22T08:50:51.8742344</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>32506</id>
            <name>Dheeraj Dheeru Rawat</name>
            <email>7516232944795269</email>
            <address>USA</address>
            <createdat>2021-02-22T10:55:58.8989824</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>32531</id>
            <name>Naji Kolakkaden</name>
            <email>naji.kolakkaden@gmail.com</email>
            <address>USA</address>
            <createdat>2021-02-22T14:43:41.6265862</createdat>
        </Travelerinformation>
        <Travelerinformation>
            <id>32544</id>
            <name>Lennart Richard</name>
            <email>lennartrichard14@gmail.com</email>
            <address>USA</address>
            <createdat>2021-02-22T17:02:00.8267685</createdat>
        </Travelerinformation>
    </travelers>
</TravelerinformationResponse>
	 
	 */
	
	
	@Test
	void testXMLResponseAppraoch1() {
		
		given()
		
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/xml; charset=utf-8")
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Vijay Bharath Reddy"));
			
	}
	
	@Test
	void testXMLResponseAppraoch2() {
		
		Response response = given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/xml; charset=utf-8");
		Assert.assertEquals(response.getHeader("Content-Type"), "application/xml; charset=utf-8");
		
		String pageNo = response.xmlPath().get("TravelerinformationResponse.page").toString();
		Assert.assertEquals(pageNo, "1");
		
		String travellerName = response.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		Assert.assertEquals(travellerName, "Vijay Bharath Reddy");
			
	}
	
	@Test
	void testXMLResponseAppraoch3() {
		
		Response response = given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
			
		
		XmlPath xmlObj = new XmlPath(response.asString());
		List<String> travellers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		
//		verify total no of travellers in the list
		Assert.assertEquals(travellers.size(), 6);
		
		// print all the traveller names
		List<String> traveller_names = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		
		for(String a : traveller_names) {
			System.out.println(a);
		}
		
		
//		verify particular traveller name is present in the response
		boolean status =false;
		for(String a : traveller_names) {
			if(a.equals("Vijay Bharath Reddy")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		// or we can write this
		Assert.assertTrue(status);
			
	}
	

}

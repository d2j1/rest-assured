package org.restassured;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertingPojoToJson {
	
	@Test
	void pojoToJson() throws JsonProcessingException {
		
		Student sarang = new Student(1,"Sarang","SCOE");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sarang);
		
		System.out.println(jsonData);
	}
	
	@Test
	void jsonToPojo() throws JsonMappingException, JsonProcessingException {
		
		String json = "{\n"
				+ "  \"id\" : 1,\n"
				+ "  \"name\" : \"Sarang\",\n"
				+ "  \"college\" : \"SCOE\"\n"
				+ "}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		Student deserialized = mapper.readValue(json, Student.class);
		
		
		
	}
}

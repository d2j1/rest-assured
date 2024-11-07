package org.restassured;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUploadDownload {
	
	
	
	@Test
	void singleFileUpload() {
	
		File myFile = new File("/home/dj/Pictures/Screenshots/img.png");
		
		
		given()
			.multiPart("file", myFile)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("img.png"))
			.log().all();
	}
	
	@Test
	void multipleFileUpload() {
	
		File myFile = new File("/home/dj/Pictures/Screenshots/img.png");
		File myFile2 = new File("/home/dj/Pictures/Screenshots/img2.png");
		
		
		given()
			.multiPart("files", myFile)
			.multiPart("files", myFile2)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/uploadMultipleFile")
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("img.png"))
			.body("[1].fileName", equalTo("img2.png"))
			.log().all();
		
		// in the response the array response name is not mentioned thatas why we are directly 
//		calling the array index instead of the array name
	}
	
	@Test
	void multipleFileUploadApproach2() {
		
		// this approach only work if backend supports the files array as a input
		
		File myFile = new File("/home/dj/Pictures/Screenshots/img.png");
		File myFile2 = new File("/home/dj/Pictures/Screenshots/img2.png");
	
		File[] fileArr = {myFile, myFile2};
		
		given()
			.multiPart("files", fileArr)
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:3000/uploadMultipleFile")
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("img.png"))
			.body("[1].fileName", equalTo("img2.png"))
			.log().all();
		
		// in the response the array response name is not mentioned thatas why we are directly 
//		calling the array index instead of the array name
	}
	
	@Test
	void singleFileDownload() {
		
		
		given()
			
		.when()
			.get("http://localhost:3000/downloadFile/img.png")
		.then()
			.statusCode(200)
			.log().body();
		
		
	}

}

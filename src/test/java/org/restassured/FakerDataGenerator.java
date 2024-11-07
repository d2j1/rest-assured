package org.restassured;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	void generateTestDummyData() {
		Faker faker = new Faker();
		
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String username = faker.name().username();
		String password = faker.internet().password();
		
		String phoneNo = faker.phoneNumber().cellPhone();
		String email = faker.internet().safeEmailAddress();
		String creditCardNo = faker.business().creditCardNumber();
		
		System.out.println(fullName);
		System.out.println(email);
	}
}

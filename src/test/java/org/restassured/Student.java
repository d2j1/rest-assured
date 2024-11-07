package org.restassured;

public class Student {

	public int id;
	public String name;
	public String college;
	
	public Student(int id,String name, String college) {
		this.college = college;
		this.id = id;
		this.name = name;
			
	}
}

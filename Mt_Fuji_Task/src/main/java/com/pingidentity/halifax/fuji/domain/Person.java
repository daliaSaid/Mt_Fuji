package com.pingidentity.halifax.fuji.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private String firstName;
	private String lastName;
	private int age;
	private int height;

	// Constructors
	public Person(){

	}
	public Person(String firstName, String lastName, int age, int height){

		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;

	}

	// Instance Variables
    public long getId() {
        return id;
    }
	public String getFirstName(){
		return firstName;
	}
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
	public String getLastName(){
		return lastName;
	}
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	public int getAge(){
		return age;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
}

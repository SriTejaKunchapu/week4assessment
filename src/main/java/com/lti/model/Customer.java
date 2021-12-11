package com.lti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name ="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="age",nullable = false)
	private int age;
	
	@Column(name="address",nullable = false)
	private String address;
	
	
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", typeofAccount="
				+ typeofAccount + "]";
	}

	public Customer() {
		super();
	}

	

	public Customer(int id, String name, int age, String address, String typeofAccount) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.typeofAccount = typeofAccount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	
	

	public String getTypeofAccount() {
		return typeofAccount;
	}

	public void setTypeofAccount(String typeofAccount) {
		this.typeofAccount = typeofAccount;
	}

	@Column(name="typeofAccount",nullable = false)
	private String typeofAccount;



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}

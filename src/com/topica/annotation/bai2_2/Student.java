package com.topica.annotation.bai2_2;

@dataDB(name = "student")
public class Student {
	@dataDB(name = "id")
	private Integer id;
	@dataDB(name = "name")
	private String name;
	@dataDB(name = "age")
	private Integer age;
	@dataDB(name = "address")
	private String address;

	public Student(Integer id, String name, Integer age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	@dataDB
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@dataDB
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@dataDB
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@dataDB
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

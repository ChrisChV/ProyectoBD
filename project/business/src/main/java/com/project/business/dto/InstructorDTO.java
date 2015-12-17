package com.project.business.dto;

public class InstructorDTO {

	private String id;
	
	private String dptName;
	
	private String name;
	
	private long salary;

	public InstructorDTO(){}
	
	public InstructorDTO(String id, String dptName, String name, long salary) {
		this.id = id;
		this.dptName = dptName;
		this.name = name;
		this.setSalary(salary);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}
	
}

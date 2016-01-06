package com.project.business.dto;

public class StudentDTO {

	private String id;
	
	private String dptName;
	
	private String name;
	
	private int totCred;

	public StudentDTO(){}
	
	public StudentDTO(String id, String dptName, String name, int totCred) {
		this.id = id;
		this.dptName = dptName;
		this.name = name;
		this.totCred = totCred;
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

	public int getTotCred() {
		return totCred;
	}

	public void setTotCred(int totCred) {
		this.totCred = totCred;
	}
}

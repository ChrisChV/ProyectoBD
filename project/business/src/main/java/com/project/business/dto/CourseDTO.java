package com.project.business.dto;

public class CourseDTO {

	private String id;
	
	private String dptName;
	
	private String title;
	
	private int credits;

	public CourseDTO(){}
	
	public CourseDTO(String id, String dptName, String title, int credits){
		this.id = id;
		this.dptName = dptName;
		this.title = title;
		this.credits = credits;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	
	
	
}

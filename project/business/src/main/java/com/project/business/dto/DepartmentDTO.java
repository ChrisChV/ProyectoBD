package com.project.business.dto;

public class DepartmentDTO {

	private String dptName;
	
	private String building;
	
	private long budget;

	public DepartmentDTO() {}
	
	public DepartmentDTO(String dptName, String building, long budget){
		this.dptName = dptName;
		this.building = building;
		this.budget = budget;
	}
	
	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}
	
}

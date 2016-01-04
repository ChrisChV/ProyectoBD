package com.project.business.dto;

public class AdvisorDTO {

	private String instructorId;
	
	private String studentId;
	
	public AdvisorDTO(){
		
	}
	
	public AdvisorDTO(String instructorId, String studentId) {
		this.instructorId = instructorId;
		this.studentId = studentId;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	
}

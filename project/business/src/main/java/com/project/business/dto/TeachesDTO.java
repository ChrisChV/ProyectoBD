package com.project.business.dto;

public class TeachesDTO {

	private String instructorId;
	
	private String courseId;
	
	private String secId;
	
	private String semester;
	
	private short year;
	
	public TeachesDTO() {}
	
	public TeachesDTO(String instructorId, String courseId, String secId, String semester, short year) {
		this.instructorId = instructorId;
		this.courseId = courseId;
		this.secId = secId;
		this.semester = semester;
		this.year = year;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}
	
}

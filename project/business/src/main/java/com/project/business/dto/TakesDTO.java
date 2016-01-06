package com.project.business.dto;

public class TakesDTO {

	
	private String courseId;
	
	private String secId;
	
	private String semester;
	
	private int year;
	
	private String studentId;
	
	private String grade;

	public TakesDTO(String courseId, String secId, String semester, int year, String studentId,
			String grade) {
		this.courseId = courseId;
		this.secId = secId;
		this.semester = semester;
		this.year = year;
		this.studentId = studentId;
		this.grade = grade;
	}
	
	public TakesDTO(){}

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}

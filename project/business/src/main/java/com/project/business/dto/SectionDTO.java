package com.project.business.dto;

public class SectionDTO {
	
	
	private String courseId;
	
	private String secId;
	
	private String semester;
	
	private short year;
	
	private String dptName;
	
	private String courseTitle;
	
	private int courseCredits;
	
	private String building;
	
	private String roomNumber;
	
	private String timeSlotId;
	
	private String takeGrade;

	public SectionDTO(){}
	
	public SectionDTO(String courseId, String secId, String semester, short year
			, String dptName, String courseTitle, int courseCredits, String building
			, String roomNumber, String timeSlotId, String takeGrade) {
		this.courseId = courseId;
		this.secId = secId;
		this.semester = semester;
		this.year = year;
		this.dptName = dptName;
		this.courseTitle = courseTitle;
		this.courseCredits = courseCredits;
		this.building = building;
		this.roomNumber = roomNumber;
		this.timeSlotId = timeSlotId;
		this.takeGrade = takeGrade;
	}
	
	public SectionDTO(String courseId, String secId, String semester, short year
			, String dptName, String courseTitle, int courseCredits, String building
			, String roomNumber, String timeSlotId) {
		this.courseId = courseId;
		this.secId = secId;
		this.semester = semester;
		this.year = year;
		this.dptName = dptName;
		this.courseTitle = courseTitle;
		this.courseCredits = courseCredits;
		this.building = building;
		this.roomNumber = roomNumber;
		this.timeSlotId = timeSlotId;
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

	public String getDptName() {
		return dptName;
	}

	public void setDptName(String dptName) {
		this.dptName = dptName;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public int getCourseCredits() {
		return courseCredits;
	}

	public void setCourseCredits(int courseCredits) {
		this.courseCredits = courseCredits;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(String timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public String getTakeGrade() {
		return takeGrade;
	}

	public void setTakeGrade(String takeGrade) {
		this.takeGrade = takeGrade;
	}
	
}

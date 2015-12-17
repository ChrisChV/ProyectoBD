package com.project.business.dto;

public class TimeSlotDTO {

	private String id;
	
	private String day;
	
	private int startHr;
	
	private int startMin;
	
	private int endHr;
	
	private int endMin;

	public TimeSlotDTO(){}
	
	public TimeSlotDTO(String id, String day, int startHr, int startMin, int endHr, int endMin) {
		this.id = id;
		this.day = day;
		this.startHr = startHr;
		this.startMin = startMin;
		this.endHr = endHr;
		this.endMin = endMin;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getStartHr() {
		return startHr;
	}

	public void setStartHr(int startHr) {
		this.startHr = startHr;
	}

	public int getStartMin() {
		return startMin;
	}

	public void setStartMin(int startMin) {
		this.startMin = startMin;
	}

	public int getEndHr() {
		return endHr;
	}

	public void setEndHr(int endHr) {
		this.endHr = endHr;
	}

	public int getEndMin() {
		return endMin;
	}

	public void setEndMin(int endMin) {
		this.endMin = endMin;
	}
	
}

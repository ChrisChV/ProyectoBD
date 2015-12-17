package com.project.business.dto;

public class ClassroomDTO {

	private String building;
	
	private String roomNumber;
	
	private int capacity;

	public ClassroomDTO(){}
	
	public ClassroomDTO(String building, String roomNumber, int capacity){
		this.building = building;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}

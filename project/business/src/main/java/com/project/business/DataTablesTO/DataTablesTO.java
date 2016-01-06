package com.project.business.DataTablesTO;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class DataTablesTO<T> {
	
	private List<T> aaData;
	
	private int sEcho;
	
	private Integer iTotalRecords;
	
	private Integer iTotalDisplayRecords;
	
	public static String toJson(DataTablesTO<?> dt) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		  try {
		   return mapper.writeValueAsString(dt);
		  } catch (JsonProcessingException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		   return null;
		  }
	}
	
	public List<T> getAaData() {
		return aaData;
	}
	
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	
	public int getsEcho() {
		return sEcho;
	}
	
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	
	public Integer getiTotalRecords() {
		return iTotalRecords;
	}
	
	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	
	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	
	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
}

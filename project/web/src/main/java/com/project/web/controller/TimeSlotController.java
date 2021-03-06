package com.project.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.DataTablesTO.DataTablesTO;
import com.project.business.dto.ClassroomDTO;
import com.project.business.dto.TimeSlotDTO;
import com.project.business.manager.TimeSlotManager;
import com.project.persistence.entity.TimeSlotId;

@Controller
public class TimeSlotController {

	@Autowired
	private TimeSlotManager timeSlotManager;
	
	@RequestMapping(value = "/timetable", produces = "application/json")
	 public @ResponseBody String showUser(@RequestParam int iDisplayStart,
	            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam String sSearch) throws IOException {
		String method="showUser";
		DataTablesTO<TimeSlotDTO> dt = new DataTablesTO<TimeSlotDTO>();
		List<TimeSlotDTO> accts = timeSlotManager.getTable(iDisplayStart, iDisplayLength, sSearch);
		List<TimeSlotDTO> accts2 = timeSlotManager.getAll();
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts2.size());
		dt.setiTotalRecords(accts2.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	 }
	
	@RequestMapping(value = "/time/last", method=RequestMethod.POST)
	public @ResponseBody int getLast(){
		return timeSlotManager.getLastIndex();
	}
	
	@RequestMapping(value = "/time/verificar", method=RequestMethod.POST)
	public @ResponseBody int verificarIndex(@RequestParam("index") int index){
		return timeSlotManager.verificarIndex(index);
	}
	
	@RequestMapping(value = "/time", method=RequestMethod.POST)
	public @ResponseBody TimeSlotDTO getTime(@RequestParam("index") int index){
		return timeSlotManager.getByIndex(index);
	}
	
	@RequestMapping(value = "/time/all", method=RequestMethod.POST)
	public @ResponseBody List<TimeSlotDTO> getAllTime(){
		return timeSlotManager.getAll();
	}
	
	@RequestMapping(value = "/time/insert", method=RequestMethod.POST )
	public @ResponseBody String insert(@RequestParam("id") String timeSlotId, @RequestParam("day") String day
			, @RequestParam("startHr") String startHr, @RequestParam("startMin") String startMin
			, @RequestParam("endHr") String endHr, @RequestParam("endMin") String endMin){
		int a = Integer.parseInt(startHr);
		int b = Integer.parseInt(startMin);
		int c = Integer.parseInt(endHr);
		int d = Integer.parseInt(endMin);
		return timeSlotManager.insert(timeSlotId, day, (byte) a, (byte) b
				, (byte) c, (byte) d);
	}
	
	@RequestMapping(value = "time/delete", method=RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("id") String timeSlotId, @RequestParam("day") String day
			, @RequestParam("startHr") String startHr, @RequestParam("startMin") String startMin){
		int a = Integer.parseInt(startHr);
		int b = Integer.parseInt(startMin);
		return timeSlotManager.delete(new TimeSlotId(timeSlotId, day,(byte) a, (byte) b));
	}
	
	@RequestMapping(value = "time/update", method=RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("id") String timeSlotId, @RequestParam("day") String day
			, @RequestParam("startHr") String startHr, @RequestParam("startMin") String startMin
			, @RequestParam("endHr") String endHr, @RequestParam("endMin") String endMin){
		int a = Integer.parseInt(startHr);
		int b = Integer.parseInt(startMin);
		int c = Integer.parseInt(endHr);
		int d = Integer.parseInt(endMin);
		TimeSlotDTO time = new TimeSlotDTO(timeSlotId, day, a, b, c,d);
		
		return timeSlotManager.update(timeSlotManager.mappingDTO(time));
	}
	
}

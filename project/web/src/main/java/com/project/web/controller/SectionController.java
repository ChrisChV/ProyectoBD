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
import com.project.business.dto.SectionDTO;
import com.project.business.dto.StudentDTO;
import com.project.business.manager.SectionManager;
import com.project.persistence.entity.ClassroomId;
import com.project.persistence.entity.SectionId;

@Controller
public class SectionController {

	@Autowired
	private SectionManager sectionManager;
	
	@RequestMapping(value = "/section", produces = "application/json")
	public @ResponseBody String getByCourse(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho,@RequestParam String courseId) throws IOException{
		String method="showUser";
		DataTablesTO<SectionDTO> dt = new DataTablesTO<SectionDTO>();
		List<SectionDTO> accts = sectionManager.getByCourse(courseId);
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts.size());
		dt.setiTotalRecords(accts.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	}
	
	@RequestMapping(value = "/section/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("courseId") String courseId
			,@RequestParam("secId") String secId, @RequestParam("semestre") String semester
			,@RequestParam("year") int year, @RequestParam("building") String building
			, @RequestParam("roomNumber") String roomNumber, @RequestParam("timeId") String timeSlotId){
		return sectionManager.insert(courseId, secId, semester,(short) year, new ClassroomId(building, roomNumber), timeSlotId);
	}
	
	@RequestMapping(value = "/section/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("courseId") String courseId
			,@RequestParam("secId") String secId, @RequestParam("semestre") String semester
			,@RequestParam("year") int year){
		return sectionManager.delete(new SectionId(courseId, secId, semester, (short) year));
	}
	
	@RequestMapping(value = "/section/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("courseId") String courseId
			,@RequestParam("secId") String secId, @RequestParam("semestre") String semester
			,@RequestParam("year") int year, @RequestParam("building") String building
			, @RequestParam("roomNumber") String roomNumber, @RequestParam("timeId") String timeSlotId){
		SectionDTO section = new SectionDTO(courseId, secId, semester,(short) year, "null", "null",0 , building, roomNumber, timeSlotId);
		return sectionManager.update(sectionManager.mappingDTO(section));
	}
	
}

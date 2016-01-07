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
import com.project.business.dto.AdvisorDTO;
import com.project.business.dto.CourseDTO;
import com.project.business.dto.InstructorDTO;
import com.project.business.dto.StudentDTO;
import com.project.business.manager.AdvisorManager;

@Controller
public class AdvisorController {

	@Autowired
	private AdvisorManager advisorManager;
	
	@RequestMapping(value = "/advisorbyStudent", produces = "application/json")
	public @ResponseBody String getByStudent(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho,@RequestParam String studentId) throws IOException{
		String method="showUser";
		DataTablesTO<InstructorDTO> dt = new DataTablesTO<InstructorDTO>();
		List<InstructorDTO> accts = advisorManager.getByStudent(studentId);
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts.size());
		dt.setiTotalRecords(accts.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	}
	
	@RequestMapping(value = "/advisorbyInstructor", produces = "application/json")
	public @ResponseBody String getByInstructor(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho,@RequestParam String instructorId) throws IOException{
		String method="showUser";
		DataTablesTO<StudentDTO> dt = new DataTablesTO<StudentDTO>();
		List<StudentDTO> accts = advisorManager.getByInstructor(instructorId);
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts.size());
		dt.setiTotalRecords(accts.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	}
	
	@RequestMapping(value = "/advisor/insert" , method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("insId") String instructorId
			, @RequestParam("stId") String studentId){
		return advisorManager.insert(instructorId, studentId);
	}
	
	@RequestMapping(value = "/advisor/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("id") String id){
		return advisorManager.delete(id);
	}
	
	@RequestMapping(value = "/advisor/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("insId") String instructorId
			, @RequestParam("stId") String studentId){
		AdvisorDTO ad = new AdvisorDTO(instructorId, studentId);
		return advisorManager.update(advisorManager.mappingDTO(ad));
	}
	
	
}

package com.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.dto.AdvisorDTO;
import com.project.business.dto.InstructorDTO;
import com.project.business.dto.StudentDTO;
import com.project.business.manager.AdvisorManager;

@Controller
public class AdvisorController {

	@Autowired
	private AdvisorManager advisorManager;
	
	@RequestMapping(value = "/advisor/byStudent", method = RequestMethod.POST)
	public @ResponseBody List<InstructorDTO> getByStudent(@RequestParam("id") String studentId){
		return advisorManager.getByStudent(studentId);
	}
	
	@RequestMapping(value = "/advisor/byInstructor" , method = RequestMethod.POST)
	public @ResponseBody List<StudentDTO> getByInstructor(@RequestParam("id") String instructorId){
		return advisorManager.getByInstructor(instructorId);
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

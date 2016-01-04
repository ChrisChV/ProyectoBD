package com.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.dto.InstructorDTO;
import com.project.business.dto.SectionDTO;
import com.project.business.manager.TeachesManager;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.TeachesId;

@Controller
public class TeachesController {

	@Autowired
	private TeachesManager teachesManager;
	
	@RequestMapping(value = "/teaches/byInstructor", method = RequestMethod.POST)
	public @ResponseBody List<SectionDTO> getByInstructor(@RequestParam("id") String instructorId){
		return teachesManager.getByInstructor(instructorId);
	}
	
	@RequestMapping(value = "/teaches/bySection", method = RequestMethod.POST)
	public @ResponseBody List<InstructorDTO> getBySection(@RequestParam("courseId") String courseId
			, @RequestParam("secId") String secId, @RequestParam("semestre") String semester
			, @RequestParam("year") int year){
		return teachesManager.getBySection(new SectionId(courseId, secId, semester, (short) year));
	}
	
	@RequestMapping(value = "/teaches/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("instructorId") String instructorId
			, @RequestParam("courseId") String courseId
			, @RequestParam("secId") String secId, @RequestParam("semestre") String semester
			, @RequestParam("year") int year){
		return teachesManager.insert(instructorId, new SectionId(courseId, secId, semester, (short) year));
	}
	
	@RequestMapping(value = "/teaches/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("instructorId") String instructorId
			, @RequestParam("courseId") String courseId
			, @RequestParam("secId") String secId, @RequestParam("semestre") String semester
			, @RequestParam("year") int year){
		return teachesManager.delete(new TeachesId(instructorId, courseId, secId, semester, (short) year));
	}
	
}

package com.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.dto.CourseDTO;
import com.project.business.manager.PrereqManager;
import com.project.persistence.entity.PrereqId;

@Controller
public class PereqController {

	@Autowired
	private PrereqManager prereqManager;
	
	@RequestMapping(value = "/prereq", method = RequestMethod.POST)
	public @ResponseBody List<CourseDTO> getPrereq(@RequestParam String courseId){
		return prereqManager.getPrereq(courseId);
	}
	
	@RequestMapping(value = "/prereq/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("courseId") String courseId
			,@RequestParam("prereqId") String prereqId){
		return prereqManager.insert(courseId, prereqId);
	}
	
	@RequestMapping(value = "/prereq/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("courseId") String courseId
			,@RequestParam("prereqId") String prereqId){
		return prereqManager.delete(new PrereqId(courseId, prereqId));
	}
}

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
import com.project.business.dto.CourseDTO;
import com.project.business.dto.DepartmentDTO;
import com.project.business.manager.PrereqManager;
import com.project.persistence.entity.PrereqId;

@Controller
public class PereqController {

	@Autowired
	private PrereqManager prereqManager;
	
	@RequestMapping(value = "/prereq", produces = "application/json")
	public @ResponseBody String getPrereq(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam String courseId) throws IOException{
		String method="showUser";
		DataTablesTO<CourseDTO> dt = new DataTablesTO<CourseDTO>();
		List<CourseDTO> accts = prereqManager.getPrereq(courseId);
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts.size());
		dt.setiTotalRecords(accts.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	}
	
	@RequestMapping(value = "/prereqDept", produces = "application/json")
	public @ResponseBody String getPrereqByDepartament(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam String courseId) throws IOException{
		String method="showUser";
		DataTablesTO<CourseDTO> dt = new DataTablesTO<CourseDTO>();
		List<CourseDTO> accts = prereqManager.getPrereqByDepartment(courseId);
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts.size());
		dt.setiTotalRecords(accts.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
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

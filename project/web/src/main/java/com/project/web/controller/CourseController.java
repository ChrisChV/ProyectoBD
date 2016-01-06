package com.project.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.LastModified;

import com.project.business.DataTablesTO.DataTablesTO;
import com.project.business.dto.ClassroomDTO;
import com.project.business.dto.CourseDTO;
import com.project.business.manager.CourseManager;

@Controller
public class CourseController {
	
	@Autowired
	private CourseManager courseManager;
	
	@RequestMapping(value = "/coursetable", produces = "application/json")
	 public @ResponseBody String showUser(@RequestParam int iDisplayStart,
	            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam String sSearch) throws IOException {
		String method="showUser";
		DataTablesTO<CourseDTO> dt = new DataTablesTO<CourseDTO>();
		List<CourseDTO> accts = courseManager.getTable(iDisplayStart, iDisplayLength, sSearch);
		List<CourseDTO> accts2 = courseManager.getAll();
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts2.size());
		dt.setiTotalRecords(accts2.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	 }
	
	@RequestMapping(value = "/course/last", method = RequestMethod.POST)
	public @ResponseBody int getLast(){
		return courseManager.getLastIndex();
	}
	
	@RequestMapping(value = "/course/verificar", method = RequestMethod.POST)
	public @ResponseBody int verificarIndex(@RequestParam("index") int index){
		return courseManager.verificarIndex(index);
	}
	
	@RequestMapping(value = "/course", method = RequestMethod.POST)
	public @ResponseBody CourseDTO getCourse(@RequestParam("index") int index){
		return courseManager.getByIndex(index);
	}
	
	@RequestMapping(value = "/course/all", method = RequestMethod.POST)
	public @ResponseBody List<CourseDTO> getAll(){
		return courseManager.getAll();
	}
	
	@RequestMapping(value = "/course/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("id") String courseId
			, @RequestParam("depId") String departmentId, @RequestParam("title") String title
			, @RequestParam("credits") int credits){
		return courseManager.insert(courseId, departmentId, title, (byte) credits);
	}
	
	@RequestMapping(value = "/course/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("id") String courseId){
		return courseManager.delete(courseId);
	}
	
	@RequestMapping(value = "/course/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("id") String courseId
			, @RequestParam("depId") String departmentId, @RequestParam("title") String title
			, @RequestParam("credits") int credits){
		CourseDTO course = new CourseDTO(courseId, departmentId, title, credits);
		return courseManager.update(courseManager.mappingDTO(course));
	}
	
}

package com.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.dto.StudentDTO;
import com.project.business.manager.StudentManager;
import com.project.business.manager.StudentManagerImpl;

@Controller
public class StudentController {

	@Autowired
	private StudentManager studentManager;
	
	@RequestMapping(value = "/student/last", method = RequestMethod.POST)
	public @ResponseBody int getLast(){
		return studentManager.getLastIndex();
	}
	
	@RequestMapping(value = "/student/verificar", method = RequestMethod.POST)
	public @ResponseBody int verificarIndex(@RequestParam("index") int index){
		return studentManager.verificarIndex(index);
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public @ResponseBody StudentDTO getStudent(@RequestParam("index") int index){
		return studentManager.getByIndex(index);
	}
	
	@RequestMapping(value = "/student/all", method = RequestMethod.POST)
	public @ResponseBody List<StudentDTO> listStudetn(){
		return studentManager.getAll();
	}
	
	@RequestMapping(value = "/student/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("id") String id
			, @RequestParam("depId") String depId, @RequestParam("name") String name
			, @RequestParam("totCred") int totCred){
		return studentManager.insert(id, depId, name, (short) totCred);
	}
	
	@RequestMapping(value = "/student/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("id") String id){
		return studentManager.delete(id);
	}
	
	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("id") String id
			, @RequestParam("depId") String depId, @RequestParam("name") String name
			, @RequestParam("totCred") int totCred){
		StudentDTO st = new StudentDTO(id, depId, name, totCred);
		return studentManager.update(studentManager.mappingDTO(st));
	}
	
}

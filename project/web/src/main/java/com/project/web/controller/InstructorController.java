package com.project.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.dto.InstructorDTO;
import com.project.business.manager.InstructorManager;

@Controller
public class InstructorController {

	@Autowired
	private InstructorManager instructorManager;
	
	@RequestMapping(value = "/instructor/last" , method = RequestMethod.POST)
	public @ResponseBody int getLast(){
		return instructorManager.getLastIndex();
	}
	
	@RequestMapping(value = "/instructor/verificar" , method = RequestMethod.POST)
	public @ResponseBody int verificarIndex(@RequestParam("index") int index){
		return instructorManager.verificarIndex(index);
	}
	
	@RequestMapping(value = "/instructor" , method = RequestMethod.POST)
	public @ResponseBody InstructorDTO getInstructor(@RequestParam("index") int index){
		return instructorManager.getByIndex(index);
	}
	
	@RequestMapping(value = "/instructor/all", method = RequestMethod.POST)
	public @ResponseBody List<InstructorDTO> getAll(){
		return instructorManager.getAll();
	}
	
	@RequestMapping(value = "/instructor/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("id") String insId
			,@RequestParam("depId") String depId, @RequestParam("name") String name
			,@RequestParam("salary") long salary){
		return instructorManager.insert(insId, depId, name, new BigDecimal(salary));
	}
	
	@RequestMapping(value = "/instructor/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("id") String insId){
		return instructorManager.delete(insId);
	}
	
	@RequestMapping(value = "/instructor/update" , method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("id") String insId
			,@RequestParam("depId") String depId, @RequestParam("name") String name
			,@RequestParam("salary") long salary){
		InstructorDTO ins = new InstructorDTO(insId, depId, name, salary);
		return instructorManager.update(instructorManager.mappingDTO(ins));
	}
	
	@RequestMapping(value = "/instructor/byDep" , method = RequestMethod.POST)
	public @ResponseBody List<InstructorDTO> getByDep(@RequestParam("depId") String deptName){
		return instructorManager.getByDept(deptName);
	}
	
}

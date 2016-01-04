package com.project.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.dto.ClassroomDTO;
import com.project.business.manager.ClassroomManager;
import com.project.persistence.entity.ClassroomId;

@Controller
public class ClassroomController {

	@Autowired
	private ClassroomManager classrootManager;
	
	@RequestMapping(value = "/classroom/last" , method = RequestMethod.POST)
	public @ResponseBody int getLast(){
		return classrootManager.getLastIndex();
	}
	
	@RequestMapping(value = "/classroom/verificar" , method = RequestMethod.POST)
	public @ResponseBody int verificarIndex(@RequestParam("index") int index){
		return classrootManager.verificarIndex(index);
	}
	
	@RequestMapping(value = "/classroom" , method = RequestMethod.POST)
	public @ResponseBody ClassroomDTO getClas(@RequestParam("index") int index){
		return classrootManager.getByIndex(index);
	}
	
	@RequestMapping(value = "/classroom/all", method = RequestMethod.POST)
	public @ResponseBody List<ClassroomDTO> getAll(){
		return classrootManager.getAll();
	}
	
	@RequestMapping(value = "/classroom/insert" , method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("building") String building
			, @RequestParam("roomNumber") String roomN, @RequestParam("capacity") int capacity){
		return classrootManager.insert(building, roomN, (short) capacity);
	}
	
	@RequestMapping(value = "/classroom/delete" , method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("building") String building
			, @RequestParam("roomNumber") String roomN){
		return classrootManager.delete(new ClassroomId(building, roomN));
	}
	
	@RequestMapping(value = "/classroom/update" , method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("building") String building
			, @RequestParam("roomNumber") String roomN, @RequestParam("capacity") int capacity){
		ClassroomDTO cla = new ClassroomDTO(building, roomN, capacity);
		return classrootManager.update(classrootManager.mappingDTO(cla));
	}
	
}

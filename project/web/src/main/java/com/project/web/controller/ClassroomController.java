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
	
	@RequestMapping(value = "/classroomtable", produces = "application/json")
	 public @ResponseBody String showUser(@RequestParam int iDisplayStart,
	            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam String sSearch) throws IOException {
		String method="showUser";
		DataTablesTO<ClassroomDTO> dt = new DataTablesTO<ClassroomDTO>();
		List<ClassroomDTO> accts = classrootManager.getTable(iDisplayStart, iDisplayLength, sSearch);
		List<ClassroomDTO> accts2 = classrootManager.getAll();
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts2.size());
		dt.setiTotalRecords(accts2.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	 }
	
	@RequestMapping(value = "/classroom/all", method = RequestMethod.POST)
	public @ResponseBody List<ClassroomDTO> getAll(){
		System.out.println("GGGGGGGGGGGGGG");
		//return "[{\"building\":\"Packard\",\"roomNumber\":\"101\",\"capacity\":500}]";
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

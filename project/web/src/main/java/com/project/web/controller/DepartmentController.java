package com.project.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.business.DataTablesTO.DataTablesTO;
import com.project.business.dto.ClassroomDTO;
import com.project.business.dto.DepartmentDTO;
import com.project.business.manager.DepartmentManager;
import com.project.persistence.entity.PrereqId;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentManager departmentManager;
	
	@RequestMapping(value = "/departmenttable", produces = "application/json")
	 public @ResponseBody String showUser(@RequestParam int iDisplayStart,
	            @RequestParam int iDisplayLength, @RequestParam int sEcho, @RequestParam String sSearch) throws IOException {
		String method="showUser";
		DataTablesTO<DepartmentDTO> dt = new DataTablesTO<DepartmentDTO>();
		List<DepartmentDTO> accts = departmentManager.getTable(iDisplayStart, iDisplayLength, sSearch);
		List<DepartmentDTO> accts2 = departmentManager.getAll();
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts2.size());
		dt.setiTotalRecords(accts2.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	 }
	
	@RequestMapping(value = "/department/last", method=RequestMethod.POST)
	public @ResponseBody int getLast(){
		return departmentManager.getLastIndex();
	}
	
	@RequestMapping(value = "/department/verificar", method=RequestMethod.POST)
	public @ResponseBody int verificarIndex(@RequestParam("index") int index){
		return departmentManager.verificarIndex(index);
	}
	
	@RequestMapping(value = "/department", method=RequestMethod.POST)
	public @ResponseBody DepartmentDTO getDpt(@RequestParam("index") int index){
		return departmentManager.getByIndex(index);
	}
	
	@RequestMapping(value = "/department/all", method=RequestMethod.POST)
	public @ResponseBody List<DepartmentDTO> listDepartment(){
		return departmentManager.getAll();
	}
	
	@RequestMapping(value = "/department/insert", method=RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("name") String depName
			, @RequestParam("building") String building, @RequestParam("budget") long budget){
		return departmentManager.insert(depName, building, new BigDecimal(budget));
	}
	
	@RequestMapping(value = "/department/delete", method=RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("name") String depName){
		return departmentManager.delete(depName);
	}
	
	@RequestMapping(value = "/department/update", method=RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("name") String depName
			, @RequestParam("building") String building, @RequestParam("budget") long budget){
		DepartmentDTO dep = new DepartmentDTO(depName, building, budget);
		return departmentManager.update(departmentManager.mappingDTO(dep));
	}
}

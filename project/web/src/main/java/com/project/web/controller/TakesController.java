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
import com.project.business.dto.SectionDTO;
import com.project.business.dto.TakesDTO;
import com.project.business.manager.TakesManager;
import com.project.persistence.entity.SectionId;
import com.project.persistence.entity.TakesId;

@Controller
public class TakesController {

	@Autowired
	private TakesManager takesManager;
	
	@RequestMapping(value = "/takesbyStudent", produces = "application/json")
	public @ResponseBody String getByStudent(@RequestParam int iDisplayStart,
            @RequestParam int iDisplayLength, @RequestParam int sEcho,@RequestParam String studentId) throws IOException{
		String method="showUser";
		DataTablesTO<SectionDTO> dt = new DataTablesTO<SectionDTO>();
		List<SectionDTO> accts = takesManager.getByStudent(studentId);
		dt.setAaData(accts);
		dt.setiTotalDisplayRecords(accts.size());
		dt.setiTotalRecords(accts.size()); 
		dt.setsEcho(sEcho);
		return dt.toJson(dt);
	}
	
	@RequestMapping(value = "/takes/insert" , method = RequestMethod.POST)
	public @ResponseBody String insert(@RequestParam("studentId") String studentId
			, @RequestParam("courseId") String courseId, @RequestParam("secId") String secId
			, @RequestParam("semestre") String semester, @RequestParam("year") int year
			, @RequestParam("grade") String grade){
		return takesManager.insert(studentId, new SectionId(courseId, secId, semester, (short) year), grade);
	}
	
	@RequestMapping(value = "takes/delete" , method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam("studentId") String studentId
			, @RequestParam("courseId") String courseId, @RequestParam("secId") String secId
			, @RequestParam("semestre") String semester, @RequestParam("year") int year){
		return takesManager.delete(new TakesId(studentId, courseId, secId, semester, (short) year));
	}
	
	@RequestMapping(value = "takes/update" , method = RequestMethod.POST)
	public @ResponseBody String update(@RequestParam("studentId") String studentId
			, @RequestParam("courseId") String courseId, @RequestParam("secId") String secId
			, @RequestParam("semestre") String semester, @RequestParam("year") int year
			, @RequestParam("grade") String grade){
		TakesDTO take = new TakesDTO(courseId, secId, semester, year, studentId, grade);
		return takesManager.update(takesManager.mappingDTO(take));
		
	}
	
}

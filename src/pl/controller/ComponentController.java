package pl.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.components.Assignement;
import pl.components.Navbar;
import pl.components.NavbarBuilder;
import pl.dao.AssignementsDao;
import pl.dao.ConceptDao;
import pl.dao.ProjectDao;
import pl.dto.Concept;
import pl.dto.Project;

@Controller
public class ComponentController {
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	NavbarBuilder navbarBuilder;
	
	@Autowired
	AssignementsDao assignementsDao;
	
	@Autowired
	ConceptDao conceptDao;
	
	@Autowired
	ProjectDao projectDao;
	
	@RequestMapping(value="/getNavbarData")
	@ResponseBody
	public String getNavbarData(Principal principal){
		
		String username = principal.getName();
		Navbar nav = navbarBuilder.buildNavbarForUser(username);
		return convertToJson(nav);
	}
	
	@RequestMapping(value="/getAssignements")
	@ResponseBody
	public String getAssignements(Principal principal){
		
		String username = principal.getName();
		ArrayList<Assignement> list = assignementsDao.getAssignements(username);
		return convertToJson(list);
	}
	
	@RequestMapping(value="/getProjectData")
	@ResponseBody
	public String getProjectData(Principal principal){
		
		String username = principal.getName();
		Project p = projectDao.getProjectById(username, 1);
		return convertToJson(p);
	}
	
	@RequestMapping(value="/getConcepts")
	@ResponseBody
	public String getConcepts(Principal principal){
		
		String username = principal.getName();
		ArrayList<Concept> list = conceptDao.getConceptsUserHasAccessTo(username);
		return convertToJson(list);
	}

	
	public String convertToJson(Object obj){
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonInString;
	}
}

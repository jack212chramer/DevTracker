package pl.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.dao.AssignementsDao;
import pl.dao.ConceptDao;
import pl.dao.ProjectDao;
import pl.dao.TaskDao;
import pl.dto.Concept;
import pl.dto.Project;
import pl.dto.Task;

@Controller
public class MainController {
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ConceptDao conceptDao;
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	AssignementsDao assignementsDao;
	
		
	@RequestMapping(value="/")
	public String homepage(){

		return "dashboard";
	}
	
	@RequestMapping(value="/test")
	public String test(){

		return "test";
	}
	
	@RequestMapping(value="/element")
	public ModelAndView element(
			@RequestParam(value="type") char element_type,
			@RequestParam(value="id") int id,
			Principal principal){
			String template = "";
			ModelAndView mav = null;
			
			switch(element_type){
				case 'p': template = "project";
					Project p = projectDao.getProjectById(principal.getName(), id);
					mav = new ModelAndView(template,"project",p);
					break;
			/*	case 'c': template = "concept";
					Concept c = conceptDao.getProjectById(principal.getName(), id);
					mav = new ModelAndView(template,"concept",c);
					break;
				case 't': template = "task";
					Task p = taskDao.getProjectById(principal.getName(), id);
					mav = new ModelAndView(template,"task",t);
					break;
				case 's': template = "subtask";
					Task p = taskDao.getProjectById(principal.getName(), id);
					mav = new ModelAndView(template,"task",t);
					break;
					*/
			}
			
		return mav;
	}

}

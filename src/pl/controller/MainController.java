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
import pl.dao.SubtaskDao;
import pl.dao.TaskDao;
import pl.dto.Concept;
import pl.dto.Project;
import pl.dto.Subtask;
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
	SubtaskDao subtaskDao;
	
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
			String template = "project";
			ModelAndView mav = null;
			
			switch(element_type){
				case 'p': 
					Project p = projectDao.getProjectById(principal.getName(), id);
					mav = new ModelAndView(template,"element",p);
					break;
				case 'c': 
					Concept c = conceptDao.getConceptById(id, principal.getName());
					mav = new ModelAndView(template,"element",c);
					break;
				case 't': 
					Task t = taskDao.getTaskById(id, principal.getName());
					mav = new ModelAndView(template,"element",t);
					break;
				case 's': 
					Subtask s = subtaskDao.getSubtaskById(id, principal.getName());
					mav = new ModelAndView(template,"element",s);
					break;
					
			}
			
		return mav;
	}

}

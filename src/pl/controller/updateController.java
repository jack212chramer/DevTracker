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
public class updateController {
	
	@Autowired
	ProjectDao projectDao;
	
	@Autowired
	ConceptDao conceptDao;
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	AssignementsDao assignementsDao;
	
		
	
	@RequestMapping(value="/setVersion")
	public void setVersion(
			Principal principal,
			@RequestParam(value="version")String version,
			@RequestParam(value="type")char type,
			@RequestParam(value="id")int id){
		
		String username = principal.getName();
		
		switch(type) {
		case 'p': 
			Project p = projectDao.getProjectById(username, id);
			p.setVersion(version);
			projectDao.updateProject(p);
			break;
		case 'c': 
			Concept c = conceptDao.getConceptById(id, username);
			c.setVersion(version);
			conceptDao.updateConcept(c);
			break;
		}
		

	}
	
}

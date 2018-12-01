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
public class updateController {
	
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
		case 't': 
			Task t = taskDao.getTaskById(id, username);
			t.setVersion(version);
			taskDao.updateTask(t);
			break;
		case 's': 
			Subtask s = subtaskDao.getSubtaskById(id, username);
			s.setVersion(version);
			subtaskDao.updateSubtask(s);
			break;
		}
	}
	
	@RequestMapping(value="/setPriority")
	public void setPriority(
			Principal principal,
			@RequestParam(value="priority")int priority,
			@RequestParam(value="type")char type,
			@RequestParam(value="id")int id){
		
		String username = principal.getName();
		
		switch(type) {
		case 'c': 
			Concept c = conceptDao.getConceptById(id, username);
			c.setPriority(priority);
			conceptDao.updateConcept(c);
			break;
		case 't': 
			Task t = taskDao.getTaskById(id, username);
			t.setPriority(priority);
			taskDao.updateTask(t);
			break;
		case 's': 
			Subtask s = subtaskDao.getSubtaskById(id, username);
			s.setPriority(priority);
			subtaskDao.updateSubtask(s);
			break;
		}
}
		
		@RequestMapping(value="/setStatus")
		public void setStatus(
				Principal principal,
				@RequestParam(value="status")int statusInt,
				@RequestParam(value="type")char type,
				@RequestParam(value="id")int id){
			
			String username = principal.getName();
			statusInt-=1;
			String status = Integer.toString(statusInt);
			
			switch(type) {
			case 'c': 
				Concept c = conceptDao.getConceptById(id, username);
				c.setStatus(status);
				conceptDao.updateConcept(c);
				break;
			case 't': 
				Task t = taskDao.getTaskById(id, username);
				t.setStatus(status);
				taskDao.updateTask(t);
				break;
			case 's': 
				Subtask s = subtaskDao.getSubtaskById(id, username);
				s.setStatus(status);
				subtaskDao.updateSubtask(s);
				break;
			}
	}
		
		@RequestMapping(value="/setDescription")
		public void setDescription(
				Principal principal,
				@RequestParam(value="description")String status,
				@RequestParam(value="type")char type,
				@RequestParam(value="id")int id){
			
			String username = principal.getName();
			
			switch(type) {
			case 'p': 
				Project p = projectDao.getProjectById(username, id);
				p.setDescription(status);
				projectDao.updateProject(p);
				break;
			case 'c': 
				Concept c = conceptDao.getConceptById(id, username);
				c.setDescription(status);
				conceptDao.updateConcept(c);
				break;
			case 't': 
				Task t = taskDao.getTaskById(id, username);
				t.setDescription(status);
				taskDao.updateTask(t);
				break;
			case 's': 
				Subtask s = subtaskDao.getSubtaskById(id, username);
				s.setDescription(status);
				subtaskDao.updateSubtask(s);
				break;
			}
	}
	
}

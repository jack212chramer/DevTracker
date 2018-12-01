package pl.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.components.Assignement;
import pl.components.Navbar;
import pl.components.NavbarBuilder;
import pl.dao.AssignementsDao;
import pl.dao.CommentDao;
import pl.dao.ConceptDao;
import pl.dao.ProjectDao;
import pl.dao.SubtaskDao;
import pl.dao.TaskDao;
import pl.dto.Concept;
import pl.dto.Project;
import pl.dto.Subtask;
import pl.dto.Task;

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
	
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	TaskDao taskDao;
	
	@Autowired
	SubtaskDao subtaskDao;
	
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
	
	@RequestMapping(value="/getElementData")
	public String getElementData(
			@RequestParam(value="id") int id,
			@RequestParam(value="type") char type){
		
		String redirect = "redirect:/"; 
		
		switch(type) {
			case 'p': redirect+="getProjectData?id="+id;
				break;
			case 'c': redirect+="getConceptData?id="+id;
				break;
			case 't': redirect+="getTaskData?id="+id;
				break;
			case 's': redirect+="getSubtaskData?id="+id;
				break;
		}

		return redirect;
	}
	
	@RequestMapping(value="/getProjectData")
	@ResponseBody
	public String getProjectData(
			@RequestParam(value="id") int id,
			Principal principal){
		
		String username = principal.getName();
		Project p = projectDao.getProjectById(username, id);
		return convertToJson(p);
	}
	
	@RequestMapping(value="/getConceptData")
	@ResponseBody
	public String getConceptData(
			@RequestParam(value="id") int id,
			Principal principal){
		
		String username = principal.getName();
		Concept p = conceptDao.getConceptById(id, username);
		return convertToJson(p);
	}
	
	@RequestMapping(value="/getTaskData")
	@ResponseBody
	public String getTaskData(
			@RequestParam(value="id") int id,
			Principal principal){
		
		String username = principal.getName();
		Task p = taskDao.getTaskById(id, username);
		return convertToJson(p);
	}
	
	@RequestMapping(value="/getSubtaskData")
	@ResponseBody
	public String getSubtaskData(
			@RequestParam(value="id") int id,
			Principal principal){
		
		String username = principal.getName();
		Subtask p = subtaskDao.getSubtaskById(id, username);
		return convertToJson(p);
	}
	
	
	@RequestMapping(value="/getConcepts")
	@ResponseBody
	public String getConcepts(Principal principal){
		
		String username = principal.getName();
		ArrayList<Concept> list = conceptDao.getConceptsUserHasAccessTo(username);
		return convertToJson(list);
	}
	
	@RequestMapping(value="/getTasks")
	@ResponseBody
	public String getTasks(Principal principal){
		
		String username = principal.getName();
		ArrayList<Task> list = taskDao.getTasksUserHasAccessTo(username);
		return convertToJson(list);
	}
	
	@RequestMapping(value="/getSubtasks")
	@ResponseBody
	public String getSubtasks(Principal principal){
		
		String username = principal.getName();
		ArrayList<Subtask> list = subtaskDao.getSubtasksUserHasAccessTo(username);
		return convertToJson(list);
	}
	
	@RequestMapping(value="/getComments")
	@ResponseBody
	public String getComments(
			@RequestParam(value="type") char type,
			@RequestParam(value="id") int id){
		
	//	String json = "";
		
	//	commentDao.getCommentsForElement(id, Character.toString(type));
		return convertToJson(commentDao.getCommentsForElement(id, Character.toString(type)));
	}
	
	@RequestMapping(value="/getPinnedElements")
	@ResponseBody
	public String getPinnedElements(
			@RequestParam(value="type") char type,
			@RequestParam(value="id") int id){
		
		String json = "";
		
		switch(type){
		case 'p':
			ArrayList<Concept> list = conceptDao.getConceptspinnedToProject(id);
			json = convertToJson(list);
			break;
		case 'c': 
			ArrayList<Task> taskList = taskDao.getTasksspinnedToConcept(id);
			json = convertToJson(taskList);
			break;
		case 't': 
			ArrayList<Subtask> subtaskList = subtaskDao.getTasksspinnedToConcept(id);
			json = convertToJson(subtaskList);
			break;
			
	}
		return json;
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

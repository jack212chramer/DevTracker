package pl.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dto.Concept;
import pl.dto.Project;
import pl.dto.Subtask;
import pl.dto.Task;
import pl.jdbc.ConceptJDBC;
import pl.jdbc.ProjectJDBC;
import pl.jdbc.SubtaskJDBC;
import pl.jdbc.TaskJDBC;

@Repository
public class SubtaskDao {
	
	@Autowired
	ConceptJDBC conceptJDBC;
	
	@Autowired
	TaskJDBC taskJDBC;
	
	@Autowired
	SubtaskJDBC subtaskJDBC;
	
	@Autowired
	ProjectJDBC projectJDBC;
	
	private int project_id;
	
	public ArrayList<Subtask> getSubtasksUserHasAccessTo(String username){
		ArrayList<Subtask> list = subtaskJDBC.selectPSubtasksFromDatabaseByUserRole(username);
		return list;
	}
	
	public ArrayList<Subtask> getTasksspinnedToConcept(int id){
		ArrayList<Subtask> list = subtaskJDBC.selectSubtasksFromDatabaseByTask(id);
		
		return list;
	}
	
	public Subtask getSubtaskById(int id, String username){
		ArrayList<Subtask> list = subtaskJDBC.selectSubtasksFromDatabaseByIdAndUsername(id, username);
		//list = completeProjectInformations(list);
		
		return list.get(0);
	}
	
	public void updateSubtask(Subtask task) {
		subtaskJDBC.updateSubtask(task);
	}
	
	private ArrayList<Task> completeConceptInformations(ArrayList<Task> list){
		for(int i =0;i<list.size();i++){
			int concept_id = list.get(i).getConcept_id();
			project_id = list.get(i).getProject_id();
			Concept concept = conceptJDBC.selectConceptsFromDatabaseById(concept_id).get(0);
			list.get(i).setProject_name(concept.getName());
		}
		return list;
	}
	
	private ArrayList<Task> completeProjectInformations(ArrayList<Task> list){
		for(int i =0;i<list.size();i++){
			try {
				Project project = projectJDBC.selectProjectsFromDatabaseById(project_id).get(0);
				list.get(i).setProject_name(project.getName());
				list.get(i).setProject_image(project.getImage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}

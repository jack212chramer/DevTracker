package pl.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dto.Concept;
import pl.dto.Project;
import pl.jdbc.ConceptJDBC;
import pl.jdbc.ProjectJDBC;

@Repository
public class ConceptDao {
	
	@Autowired
	ConceptJDBC conceptJDBC;
	
	@Autowired
	ProjectJDBC projectJDBC;
	
	public ArrayList<Concept> getConceptsUserHasAccessTo(String username){
		ArrayList<Concept> list = conceptJDBC.selectConceptsFromDatabaseByUserRole(username);
		list = completeProjectInformations(list);
		return list;
	}
	
	public ArrayList<Concept> getConceptspinnedToProject(int id){
		ArrayList<Concept> list = conceptJDBC.selectConceptsFromDatabaseByProject(id);
		list = completeProjectInformations(list);
		return list;
	}
	
	public Concept getConceptById(int id, String username){
		ArrayList<Concept> list = conceptJDBC.selectConceptsFromDatabaseByProjectIdAndUsername(id, username);
		list = completeProjectInformations(list);
		return list.get(0);
	}
	
	private ArrayList<Concept> completeProjectInformations(ArrayList<Concept> list){
		for(int i =0;i<list.size();i++){
			int project_id = list.get(i).getProject_id();
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
	
	public void updateConcept(Concept concept) {
		conceptJDBC.updateConcept(concept);
	}
}

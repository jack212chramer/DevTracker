package pl.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.dto.Project;
import pl.jdbc.ProjectJDBC;

@Repository
public class ProjectDao {
	
	@Autowired
	ProjectJDBC projectJDBC;
	
	public ArrayList<Project> getProjectUserHasAccessTo(String username){
		return projectJDBC.selectProjectsFromDatabaseByUserRole(username);
	}
	
	public Project getProjectById(String username, int id){
		 Project p = projectJDBC.selectProjectsFromDatabaseByUserRoleAndId(username, id).get(0);
		 return p;
	}
	
	public void updateProject(Project project) {
		projectJDBC.updateProject(project);
	}
}

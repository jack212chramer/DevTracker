package pl.components;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import pl.dao.ProjectDao;
import pl.dto.Project;

public class NavbarBuilder {
	
	@Autowired
	ProjectDao projectDao;
	
	private Navbar nav;
	private String username;
	
	public Navbar buildNavbarForUser(String username){
		this.username = username;
		nav = new Navbar();
		nav.setUsername(username);
		setProjects();
		return nav;
	}
	
	private void setProjects(){
		ArrayList<Project> list = projectDao.getProjectUserHasAccessTo(username);
		nav.setProjects(list);
	}

}

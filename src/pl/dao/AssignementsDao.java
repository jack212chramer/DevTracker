package pl.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.components.Assignement;
import pl.jdbc.AssignementJDBC;

@Repository(value="AssignementsDao")
public class AssignementsDao {
	
	@Autowired
	AssignementJDBC assignementJDBC;
	
	public ArrayList<Assignement> getAssignements(String username){
		ArrayList<Assignement> list = assignementJDBC.selectAssignementsFromDatabase(username);
		return list;
	}

}

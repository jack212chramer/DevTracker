package pl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import pl.components.Assignement;
import pl.config.DatabaseConfig;

@Component
public class AssignementJDBC extends DatabaseConfig{
	
	private Connection c;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	public ArrayList<Assignement> selectAssignementsFromDatabase(String username){
		ArrayList<Assignement> list = new ArrayList<>();
		try{
			connect();
			rs = selectProjects(username);
			list = addResultsToListAndClose(rs,list);
			rs = selectConcepts(username);
			list = addResultsToListAndClose(rs,list);
			rs = selectTasks(username);
			list = addResultsToListAndClose(rs,list);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
		
	
	private ResultSet selectProjects(String username) throws SQLException{
		stmt = null;
		stmt = c.prepareStatement("SELECT * FROM assignements,projects WHERE username = ? AND assignements.element_type = 'p' AND assignements.element_id = projects.id;");
		stmt.setString(1, username);
		return stmt.executeQuery();
	}
	
	private ResultSet selectConcepts(String username) throws SQLException{
		stmt = null;
		stmt = c.prepareStatement("SELECT * FROM assignements,Concepts WHERE username = ? AND assignements.element_type = 'c' AND assignements.element_id = concepts.id;");
		stmt.setString(1, username);
		return stmt.executeQuery();
	}
	
	private ResultSet selectTasks(String username) throws SQLException{
		stmt = null;
		stmt = c.prepareStatement("SELECT * FROM assignements,tasks WHERE username = ? AND assignements.element_type = 't' AND assignements.element_id = tasks.id;");
		stmt.setString(1, username);
		return stmt.executeQuery();
	}
	
	private ResultSet selectSubtasks(String username) throws SQLException{
		stmt = null;
		stmt = c.prepareStatement("SELECT * FROM assignements,subtasks WHERE username = ? AND assignements.element_type = 's' AND assignements.element_id = subtasks.id;");
		stmt.setString(1, username);
		return stmt.executeQuery();
	}
	
	private ArrayList<Assignement> addResultsToListAndClose(ResultSet rs, ArrayList<Assignement> list){
		try {
			while(rs.next()){
				Assignement assignement = new Assignement();
				assignement.setElement_type(rs.getString("element_type"));
				assignement.setId(rs.getInt("element_id"));
				assignement.setName(rs.getString("name"));
				assignement.buildFullId();
				list.add(assignement);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void connect() throws ClassNotFoundException, SQLException{
		c = null;
		Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(connectionPath);
	      c.setAutoCommit(false);
	}
	
	private void disconnect() throws SQLException{
		rs.close();
	      stmt.close();
	      c.close();
	}
	
}

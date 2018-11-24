package pl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import pl.config.DatabaseConfig;
import pl.dto.Concept;

@Component
public class ConceptJDBC extends DatabaseConfig{
	
	private Connection c;
	private PreparedStatement stmt = null;
	private ResultSet rs;
	
	
	public ArrayList<Concept> selectConceptsFromDatabaseByUserRole(String username){
		ArrayList<Concept> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Concepts,roles,projects WHERE "
					+ "roles.username = ? AND roles.element_id = concepts.id ;");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<Concept> selectConceptsFromDatabaseById(int id){
		ArrayList<Concept> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Concepts WHERE id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<Concept> selectConceptsFromDatabaseByProject(int id){
		ArrayList<Concept> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Concepts WHERE project_id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
		
	private ArrayList<Concept> prepareList(ResultSet rs) throws SQLException{
		ArrayList<Concept> list = new ArrayList<>();
		while(rs.next()){
			Concept concept = new Concept();
			concept.setId(rs.getInt("id"));
			concept.setName(rs.getString("name"));
			concept.setDescription(rs.getString("description"));
			concept.setPriority(rs.getInt("priority"));
			concept.setWorkflow(rs.getString("workflow"));
			concept.setStatus(rs.getString("status"));
			concept.setVersion(rs.getString("version"));
			concept.setProject_id(rs.getInt("project_id"));
			list.add(concept);
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

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
	
	public ArrayList<Concept> selectConceptsFromDatabaseByProjectIdAndUsername(int id, String username){
		ArrayList<Concept> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Concepts,roles WHERE id = ? AND concepts.id=roles.element_id "
					+ "AND roles.element_type='c' AND roles.username=?;");
			stmt.setInt(1, id);
			stmt.setString(2, username);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public void updateConcept(Concept concept){
		try{
			connect();
			stmt = c.prepareStatement("UPDATE concepts SET name=?,description=?,version=?,status=?,priority=?,project_id=? WHERE id=?;");
			stmt.setString(1, concept.getName());
			stmt.setString(2, concept.getDescription());
			stmt.setString(3, concept.getVersion());
			stmt.setString(4, concept.getStatus());
			stmt.setInt(5, concept.getPriority());
			stmt.setInt(6, concept.getProject_id());
			stmt.setInt(7, concept.getId());
			stmt.executeUpdate();
			c.commit();
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
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

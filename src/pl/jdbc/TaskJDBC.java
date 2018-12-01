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
import pl.dto.Task;

@Component
public class TaskJDBC extends DatabaseConfig{
	
	private Connection c;
	private PreparedStatement stmt = null;
	private ResultSet rs;
	
	
	public ArrayList<Task> selectProjectsFromDatabaseByUserRole(String username){
		ArrayList<Task> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Tasks,roles WHERE roles.username = ? AND roles.element_id = Tasks.id AND roles.element_type = Tasks.element_type;");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<Task> selectTasksFromDatabaseByConcept(int id){
		ArrayList<Task> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM tasks WHERE concept_id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	
	public ArrayList<Task> selectTasksFromDatabaseByIdAndUsername(int id, String username){
		ArrayList<Task> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM tasks,roles WHERE id = ? AND tasks.id=roles.element_id "
					+ "AND roles.element_type='t' AND roles.username=?;");
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
		
	public ArrayList<Task> selectProjectsFromDatabaseById(int id) throws SQLException{
		ArrayList<Task> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Tasks WHERE id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public void updateTask(Task task) {
		try{
			connect();
			stmt = c.prepareStatement("UPDATE tasks SET name=?,description=?,version=?,status=?,priority=?,concept_id=? WHERE id = ?;");
			stmt.setString(1, task.getName());
			stmt.setString(2, task.getDescription());
			stmt.setString(3, task.getVersion());
			stmt.setString(4, task.getStatus());
			stmt.setInt(5, task.getPriority());
			stmt.setInt(6, task.getConcept_id());
			stmt.setInt(7, task.getId());
			stmt.executeUpdate();
			c.commit();
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
	}
		
	private ArrayList<Task> prepareList(ResultSet rs) throws SQLException{
		ArrayList<Task> list = new ArrayList<>();
		while(rs.next()){
			Task task = new Task();
			task.setId(rs.getInt("id"));
			task.setName(rs.getString("name"));
			task.setDescription(rs.getString("description"));
			task.setPriority(rs.getInt("priority"));
			task.setWorkflow(rs.getString("workflow"));
			task.setStatus(rs.getString("status"));
			task.setVersion(rs.getString("version"));
			task.setConcept_id(rs.getInt("concept_id"));
			list.add(task);
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

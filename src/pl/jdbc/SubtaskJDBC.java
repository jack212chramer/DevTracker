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
import pl.dto.Subtask;
import pl.dto.Task;

@Component
public class SubtaskJDBC extends DatabaseConfig{
	
	private Connection c;
	private PreparedStatement stmt = null;
	private ResultSet rs;
	
	
	public ArrayList<Subtask> selectPSubtasksFromDatabaseByUserRole(String username){
		ArrayList<Subtask> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM subasks,roles WHERE roles.username = ? AND roles.element_id = subtasks.id AND roles.element_type = subtasks.element_type;");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<Subtask> selectSubtasksFromDatabaseByTask(int id){
		ArrayList<Subtask> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Subtasks WHERE task_id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	
	public ArrayList<Subtask> selectSubtasksFromDatabaseByIdAndUsername(int id, String username){
		ArrayList<Subtask> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Subtasks,roles WHERE id = ? AND Subtasks.id=roles.element_id "
					+ "AND roles.element_type='s' AND roles.username=?;");
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
		
	public ArrayList<Subtask> selectSubtasksFromDatabaseById(int id) throws SQLException{
		ArrayList<Subtask> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Subtasks WHERE id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public void updateSubtask(Subtask task) {
		try{
			connect();
			stmt = c.prepareStatement("UPDATE Subtasks SET name=?,description=?,version=?,status=?,priority=?task_id=? WHERE id = ?;");
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
		
	private ArrayList<Subtask> prepareList(ResultSet rs) throws SQLException{
		ArrayList<Subtask> list = new ArrayList<>();
		while(rs.next()){
			Subtask task = new Subtask();
			task.setId(rs.getInt("id"));
			task.setName(rs.getString("name"));
			task.setDescription(rs.getString("description"));
			task.setPriority(rs.getInt("priority"));
			task.setWorkflow(rs.getString("workflow"));
			task.setStatus(rs.getString("status"));
			task.setVersion(rs.getString("version"));
			task.setTask_id(rs.getInt("task_id"));
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

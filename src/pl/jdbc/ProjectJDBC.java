package pl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import pl.config.DatabaseConfig;
import pl.dto.Project;

@Component
public class ProjectJDBC extends DatabaseConfig{
	
	private Connection c;
	private PreparedStatement stmt = null;
	private ResultSet rs;
	
	
	public ArrayList<Project> selectProjectsFromDatabaseByUserRole(String username){
		ArrayList<Project> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Projects,roles WHERE roles.username = ? AND roles.element_id = Projects.id AND roles.element_type = projects.element_type;");
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			list = prepareProjectList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	
	public ArrayList<Project> selectProjectsFromDatabaseByUserRoleAndId(String username, int id){
		ArrayList<Project> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Projects,roles WHERE roles.username = ? AND projects.id = ? AND roles.element_id = Projects.id AND roles.element_type = projects.element_type;");
			stmt.setString(1, username);
			stmt.setInt(2, id);
			rs = stmt.executeQuery();
			list = prepareProjectList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
		
	public ArrayList<Project> selectProjectsFromDatabaseById(int id) throws SQLException{
		ArrayList<Project> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM Projects WHERE id = ?;");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			list = prepareProjectList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
		
	private ArrayList<Project> prepareProjectList(ResultSet rs) throws SQLException{
		ArrayList<Project> list = new ArrayList<>();
		while(rs.next()){
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setName(rs.getString("name"));
			project.setDescription(rs.getString("description"));
			project.setImage(rs.getString("image"));
			project.setVersion(rs.getString("version"));
			list.add(project);
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

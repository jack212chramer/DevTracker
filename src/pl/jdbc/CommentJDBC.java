package pl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import pl.components.Comment;
import pl.config.DatabaseConfig;

@Component
public class CommentJDBC extends DatabaseConfig{
	
	private Connection c;
	private PreparedStatement stmt = null;
	private ResultSet rs;
	
	
	public ArrayList<Comment> selectProjectsFromDatabaseByElement(String element_type,int element_id){
		ArrayList<Comment> list = null;
		try{
			connect();
			stmt = c.prepareStatement("SELECT * FROM comments WHERE element_id = ? AND element_type = ?;");
			stmt.setInt(1, element_id);
			stmt.setString(2, element_type);
			rs = stmt.executeQuery();
			list = prepareProjectList(rs);
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return list;
	}
	

		
	private ArrayList<Comment> prepareProjectList(ResultSet rs) throws SQLException{
		ArrayList<Comment> list = new ArrayList<>();
		while(rs.next()){
			Comment comment = new Comment();
			comment.setId(rs.getInt("id"));
			comment.setUsername(rs.getString("username"));
			comment.setComment(rs.getString("comment"));
			comment.setElement_type(rs.getString("element_type"));
			comment.setElement_id(rs.getInt("element_id"));
			list.add(comment);
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

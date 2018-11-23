package pl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import pl.authentication.User;
import pl.config.DatabaseConfig;

@Component
public class UserJDBC extends DatabaseConfig{
		
	private Connection c;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	public User selectUserFromDatabase(String username){
		User user = null;
		try{
			connect();
			rs = selectByUsername(username);
			user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(new BCryptPasswordEncoder().encode( rs.getString("password")));
			user.setEnabled(rs.getBoolean("enabled"));
			user.setRoles(rs.getString("authority"));
			disconnect();
		}catch(Exception e){
			System.out.println(e);
		}
		return user;
	}
	
	
	
	private ResultSet selectByUsername(String username) throws SQLException{
		stmt = null;
		stmt = c.prepareStatement("SELECT * FROM Users,Authorities WHERE Users.username = ? AND Users.username = Authorities.username;");
		stmt.setString(1, username);
		return stmt.executeQuery();
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

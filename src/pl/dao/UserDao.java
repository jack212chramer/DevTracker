package pl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.authentication.User;
import pl.jdbc.UserJDBC;

@Repository(value="UserDao")
public class UserDao {
	
	@Autowired
	UserJDBC userJDBC;
	
	public User getUserbyUsername(String username){
		User user = userJDBC.selectUserFromDatabase(username);
		return user;
	}

}

package pl.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pl.authentication.User;
import pl.jdbc.UserJDBC;

public class SelectUserTest {

	@Test
	public void test() {
		UserJDBC jdbc = new UserJDBC();
		User user = jdbc.selectUserFromDatabase("test");
		assertTrue("User not loaded",user.getUsername().equals("test"));
	}

}

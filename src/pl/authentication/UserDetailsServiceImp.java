package pl.authentication;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;


public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = findUserbyUsername(username);
		    
		    UserBuilder builder = null;
		    if (user != null) {
		      builder = org.springframework.security.core.userdetails.User.withUsername(username);
		      builder.password(user.getPassword());
		      builder.disabled(!user.isEnabled());
		      builder.roles(user.getRoles());
		    } else {
		      throw new UsernameNotFoundException("User not found.");
		    }
		    return builder.build();
	}
	
	private User findUserbyUsername(String username){
		User user = userDao.getUserbyUsername(username);
		return user;
		
	}

}

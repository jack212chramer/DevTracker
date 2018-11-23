package pl.authentication;

public class User {

	private String username;
	private String password;
	private boolean enabled;
	private String[] roles;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String... roles) {
		this.roles = roles;
	}

	
	
	
	
}

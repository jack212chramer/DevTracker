package pl.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	protected final String connectionPath = "jdbc:sqlite:C:/Users/User/eclipse-workspace/database/DevTracker";
}

package pl.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	protected final String connectionPath = "jdbc:sqlite:/home/jack/eclipse-workspace/database/DevTracker";
}

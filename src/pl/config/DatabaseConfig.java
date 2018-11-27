package pl.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	protected final String connectionPath = "jdbc:sqlite:/home/jack212chramer/eclipse-workspace/database/DevTracker";
}

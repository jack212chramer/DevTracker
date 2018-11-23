package pl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.components.NavbarBuilder;
import pl.dao.AssignementsDao;
import pl.dao.ConceptDao;
import pl.dao.ProjectDao;
import pl.dao.TaskDao;
import pl.dao.UserDao;
import pl.jdbc.AssignementJDBC;
import pl.jdbc.ConceptJDBC;
import pl.jdbc.ProjectJDBC;
import pl.jdbc.TaskJDBC;
import pl.jdbc.UserJDBC;

@EnableWebMvc
@Configuration
@ComponentScan({"pl.controller"})
public class Config implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver bean = new InternalResourceViewResolver();
	    bean.setViewClass(JstlView.class);
	    bean.setPrefix("/WEB-INF/view/");
	    bean.setSuffix(".jsp");
	    return bean;
	}

	@Bean
	public UserDao userDao(){
		return new UserDao();
	}
	
	@Bean
	public UserJDBC userJDBC(){
		return new UserJDBC();
	}
	
	@Bean
	public ProjectDao projectDao(){
		return new ProjectDao();
	}
	
	@Bean
	public ProjectJDBC projectJDBC(){
		return new ProjectJDBC();
	}
	
	@Bean
	public AssignementsDao assignementsDao(){
		return new AssignementsDao();
	}
	
	@Bean
	public AssignementJDBC assignementJDBC(){
		return new AssignementJDBC();
	}
	
	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}
	
	@Bean
	public NavbarBuilder navbarBuilder(){
		return new NavbarBuilder();
	}
	
	@Bean
	public ConceptDao conceptDao(){
		return new ConceptDao();
	}
	
	@Bean
	public ConceptJDBC conceptJDBC(){
		return new ConceptJDBC();
	}
	
	@Bean
	public TaskDao taskDao(){
		return new TaskDao();
	}
	
	@Bean
	public TaskJDBC taskJDBC(){
		return new TaskJDBC();
	}
}

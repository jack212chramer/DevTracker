package pl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.authentication.UserDetailsServiceImp;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	  @Bean
	  public UserDetailsService userDetailsService() {
	    return new UserDetailsServiceImp();
	  };
	
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	  
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	    .authorizeRequests()
	    	.anyRequest().authenticated()
		    .and()
	    .formLogin()
	    //	.loginPage("/login")
	    	.loginProcessingUrl("/signin") 
	    	.usernameParameter("username")
	    	.passwordParameter("password")
	    //	.defaultSuccessUrl("/loginCheck")
	    //	.failureUrl("/loginCheck")
	    	.permitAll()
	    	.and()
	    .logout().permitAll().logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
	    	.and()
	    .csrf().disable();
	  }

}

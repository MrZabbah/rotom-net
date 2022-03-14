package es.trident.rotomnet.security;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import es.trident.rotomnet.service.RepositoryUserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private RepositoryUserDetailsService userDetailsService;
	
  @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10,new SecureRandom());
	}
  
	public SecurityConfiguration(RepositoryUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().antMatchers("/").permitAll();
		http.authorizeHttpRequests().antMatchers("/login").permitAll();
		http.authorizeHttpRequests().antMatchers("/register").permitAll();
		http.authorizeHttpRequests().antMatchers("/registered").permitAll();
		http.authorizeHttpRequests().antMatchers("/pokedex").permitAll();
		http.authorizeHttpRequests().antMatchers("/teamGenerator").permitAll();
		http.authorizeHttpRequests().antMatchers("/createRandomTeam").permitAll();
		http.authorizeHttpRequests().antMatchers("/{id}/image").permitAll();
		http.authorizeHttpRequests().antMatchers("/modified_user/{username}").permitAll();
		http.authorizeHttpRequests().antMatchers("/sendByMail").permitAll();
		http.authorizeHttpRequests().antMatchers("/users").hasRole("ADMIN");
		http.authorizeHttpRequests().antMatchers("/selectUser").hasRole("ADMIN");
		
    
		http.authorizeHttpRequests().anyRequest().authenticated();
				
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("pwd");
		http.formLogin().failureUrl("/login");
		http.formLogin().defaultSuccessUrl("/", true);
				
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		http.csrf().disable();
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}

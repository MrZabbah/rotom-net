package es.trident.rotomnet.security;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import es.trident.rotomnet.service.RepositoryUserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private RepositoryUserDetailsService userDetailsService;
	
	public SecurityConfiguration(RepositoryUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10,new SecureRandom());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Public and Private pages
		// Login - Logout Configuration
		// CSRF disabling
		http.authorizeHttpRequests().antMatchers("/pokedex").authenticated();
		http.authorizeHttpRequests().antMatchers("/register").permitAll();
		http.authorizeHttpRequests().antMatchers("/registered").permitAll();
		http.authorizeHttpRequests().anyRequest().permitAll();
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("pwd");

		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/teamGenerator");
		
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		http.csrf().disable();
	}
}

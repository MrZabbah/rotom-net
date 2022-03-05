package es.trident.rotomnet.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Administrator adding
		auth.inMemoryAuthentication().withUser("opalo").password("pkmn").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Public and Private pages
		// Login - Logout Configuration
		// CSRF disabling
		http.authorizeHttpRequests().antMatchers("/pokedex").authenticated();
		http.authorizeHttpRequests().anyRequest().permitAll();
		
		http.csrf().disable();
	}
}

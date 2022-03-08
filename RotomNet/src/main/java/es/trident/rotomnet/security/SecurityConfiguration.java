package es.trident.rotomnet.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		String encodedPass = encoder.encode("pkmn");
		
		// Administrator adding
		auth.inMemoryAuthentication().withUser("opalo").password(encodedPass).roles("USER");
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Public and Private pages
		// Login - Logout Configuration
		// CSRF disabling
		http.authorizeHttpRequests().antMatchers("/pokedex").authenticated();
		http.authorizeHttpRequests().anyRequest().permitAll();
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("pwd");

		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/");
		
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		http.csrf().disable();
	}
}

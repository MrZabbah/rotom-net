package es.trident.rotomnet.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		String encodedPass = encoder.encode("pkmn");
		
		// Administrator addings
		auth.inMemoryAuthentication().withUser("opalo").password(encodedPass).roles("USER", "ADMIN");
		auth.inMemoryAuthentication().withUser("Test").password(encodedPass).roles("USER");
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Public and Private pages
		// Login - Logout Configuration
		// CSRF disabling		
		http.authorizeHttpRequests().antMatchers("/").permitAll();
		http.authorizeHttpRequests().antMatchers("/login").permitAll();
		http.authorizeHttpRequests().antMatchers("/register").permitAll();
		http.authorizeHttpRequests().antMatchers("/registered").permitAll();
		http.authorizeHttpRequests().antMatchers("/pokedex").permitAll();
		http.authorizeHttpRequests().antMatchers("/teamGenerator").permitAll();
		http.authorizeHttpRequests().antMatchers("/createRandomTeam").permitAll();
		http.authorizeHttpRequests().antMatchers("/createRandomTeam").permitAll();
		http.authorizeHttpRequests().antMatchers("/{id}/image").permitAll();		
		http.authorizeHttpRequests().antMatchers("/users").hasRole("ADMIN");
		http.authorizeHttpRequests().antMatchers("/selectUser").hasRole("ADMIN");
		
		http.authorizeHttpRequests().anyRequest().authenticated();
		
		
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("pwd");
		http.formLogin().failureUrl("/");
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

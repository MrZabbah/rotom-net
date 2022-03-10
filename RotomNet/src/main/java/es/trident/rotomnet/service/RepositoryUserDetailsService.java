package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.User;
import es.trident.rotomnet.repository.UserRepository;

@Service
public class RepositoryUserDetailsService implements UserDetailsService{
	
	private UserRepository userRepository;
	
	public RepositoryUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User currentUser = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		List<GrantedAuthority> userRoles = new ArrayList<GrantedAuthority>();
		for(String currentRole : currentUser.getRoles()) {
			userRoles.add(new SimpleGrantedAuthority("ROLE_"+currentRole));
		}
		return new org.springframework.security.core.userdetails.User(currentUser.getUsername(), currentUser.getPwd(), userRoles);
	}

}

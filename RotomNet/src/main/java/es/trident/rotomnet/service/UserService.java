/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.repository.UserRepository;

/**
 * UserService: Servicio dedicado al control y manejo de las tablas
 * relacionadas con usuarios, dentro de la base de datos.
 */

@Service
public class UserService {
	
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User saveNewUser(String username, String pwd) {
		return saveNewUser(username, pwd, null);
	}
	
	public void addRoleToUser(String username, String role) {
		User user = findUserByUsername(username);
		user.addRole(role);
		userRepository.save(user);
	}

	public User saveNewUser(String username, String pwd, MultipartFile image) {
		String time = dtf.format(LocalDateTime.now());
		User u = new User(username, passwordEncoder.encode(pwd), 1, time);
		
		try {
			if(image == null || image.isEmpty()) {
				u.setImage(false);
				u.setImageFile(null);
			}else {
				u.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				u.setImage(true);
			}			
		} catch (IOException e) {}
		
		userRepository.save(u);
		return u;
	}
	
	public User findUserByUsername(String username) throws UsernameNotFoundException{
		return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
	}
		
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	public User findUserById(long id) {
		return userRepository.findById(id).orElseThrow();
	}

	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	public void saveUserWithTeamsChanged(User user) {
		userRepository.save(user);
	}

	public void modifyUser(String username, String newUsername, String pwd, MultipartFile image) throws IOException {
		User u = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
		
		if(!newUsername.equals("")) {u.setUsername(newUsername);}
		if(!pwd.equals("")) {u.setPwd(passwordEncoder.encode(pwd));}
		if(!image.isEmpty()) {
			u.setImage(true);
			u.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
		}
		userRepository.save(u);
	}
	
}

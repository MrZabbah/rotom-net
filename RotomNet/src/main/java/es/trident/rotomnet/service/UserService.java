/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.engine.jdbc.BlobProxy;
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

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User saveNewUser(String username, String pwd) {
		return saveNewUser(username, pwd, null);
	}

	public User saveNewUser(String username, String pwd, MultipartFile image) {
		String time = dtf.format(LocalDateTime.now());
		User u = new User(username, pwd, 1, time);
		
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
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
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
		User u = userRepository.findByUsername(username);
		
		if(!newUsername.equals("")) {u.setUsername(newUsername);}
		if(!pwd.equals("")) {u.setPwd(pwd);}
		if(!image.isEmpty()) {
			u.setImage(true);
			u.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
		}
		userRepository.save(u);
	}
	
}

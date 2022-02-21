package es.trident.rotomnet.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.trident.rotomnet.model.User;
import es.trident.rotomnet.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	public User saveNewUser(String username, String pwd) throws IOException {
		String time = dtf.format(LocalDateTime.now());
		User u = new User(username, pwd, 1, time);
		u.setImage(false);
		u.setImageFile(null);
		repository.save(u);
		return u;
	}
	
	public User saveNewUser(String username, String pwd, MultipartFile image) throws IOException {
		String time = dtf.format(LocalDateTime.now());
		User u = new User(username, pwd, 1, time);
		if(image.isEmpty()) {
			u.setImage(false);
			u.setImageFile(null);
		}else {
			u.setImage(true);
			u.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
		}		
		repository.save(u);
		return u;
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}

	public User findUserById(long id) {
		return repository.findById(id).orElseThrow();
	}
	
	public void deleteUser(long id) {
		repository.deleteById(id);
	}

	public User getUserByUsernameAndPwd(String username, String pwd) {
		return repository.findByUsernameAndPwd(username, pwd);
	}

	public void modifyUser(String username, String newUsername, String pwd, MultipartFile image) throws IOException {
		User u = repository.findByUsername(username);
		
		//Modify the user if parameters are not null. 
		if(!newUsername.equals("")) {u.setUsername(newUsername);}
		if(!pwd.equals("")) {u.setPwd(pwd);}
		if(!image.isEmpty()) {
			u.setImage(true);
			u.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
		}
		repository.save(u);
	}

	public User findUserByUsername(String username) {
		return repository.findByUsername(username);
	}
}

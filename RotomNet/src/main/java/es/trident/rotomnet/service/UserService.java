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
import es.trident.rotomnet.model.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void saveNewUser(String username, String pwd, MultipartFile image) throws IOException {
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
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}

	public User findUserById(long id) {
		return repository.findById(id).orElseThrow();
	}
}

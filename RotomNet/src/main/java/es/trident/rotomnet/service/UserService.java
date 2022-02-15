package es.trident.rotomnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.User;
import es.trident.rotomnet.model.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public void saveNewUser(String username, String pwd) {
		User u = new User(username, pwd);
		repository.save(u);
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
}

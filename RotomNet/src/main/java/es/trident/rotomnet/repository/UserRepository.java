package es.trident.rotomnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsernameAndPwd(String username, String pwd);	
	public User findByUsername(String username);
}

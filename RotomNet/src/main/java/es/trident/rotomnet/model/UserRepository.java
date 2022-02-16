package es.trident.rotomnet.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsernameAndPwd(String username, String pwd);
}

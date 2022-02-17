package es.trident.rotomnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.trident.rotomnet.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import es.trident.rotomnet.model.User;

/**
 * UserRepository: Repositorio encargado de las consultas de la tabla
 * de usuarios a la base de datos
 */
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsernameAndPwd(String username, String pwd);	
	
	public Optional<User> findByUsername(String username);
	
}

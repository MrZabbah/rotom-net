/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;

/**
 * TeamRepository: Interfaz dedicada a manejar la entidad Team en la base de datos.
 */

public interface TeamRepository extends JpaRepository<Team,Integer>{
	
	Page<Team> findByUser(User user, Pageable page);
	
}

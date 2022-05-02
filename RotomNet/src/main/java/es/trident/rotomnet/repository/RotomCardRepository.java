/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.User;

/**
 * RotomCardRepository: Repositorio encargado de las consultas de la tabla
 * de RotomCard a la base de datos.
 */
@CacheConfig
public interface RotomCardRepository extends JpaRepository<RotomCard, Pokemon>{
	
	@Cacheable(value="cards")
	List<RotomCard> findAll();
	
	@Query(nativeQuery =true,value = "SELECT * FROM rotom_card as e WHERE e.pokemon_dex_id NOT IN (:pkmns)")
	List<RotomCard> findByPokemonNotIn(@Param("pkmns")List<Pokemon> pkmns);
	
}

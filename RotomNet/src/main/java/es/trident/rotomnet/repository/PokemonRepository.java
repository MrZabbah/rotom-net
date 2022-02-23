/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import es.trident.rotomnet.model.Pokemon;

/**
 * PokemonRepository: Interfaz dedicada a manejar la entidad Pokemon en la base de datos.
 */

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	
	List<Pokemon> findByType1OrType2AllIgnoreCaseAndReadyToBattle(String type1, String type2, boolean readyToBattle);
	
	List<Pokemon> findByLegendaryAndReadyToBattle(boolean legendary, boolean readyToBattle);
	
	@Query("SELECT p FROM Pokemon p WHERE (p.type1 IN ?1 or p.type2 IN ?1)and p.legendary = ?2 and p.readyToBattle = ?3")
	List<Pokemon> findByTypesAndLegendaryAndReadyToBattle(List<String> types, boolean _legendary, boolean _readyToBattle);
	
}

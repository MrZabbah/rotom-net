package es.trident.rotomnet.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.trident.rotomnet.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	List<Pokemon> findByType1OrType2AllIgnoreCaseAndReadyToBattle(String type1, String type2, boolean readyToBattle);
	List<Pokemon> findByLegendaryAndReadyToBattle(boolean legendary, boolean readyToBattle);
	@Query("SELECT p FROM Pokemon p WHERE (p.type1 = ?1 or p.type2 = ?2)and p.legendary = ?3 and p.readyToBattle = ?4")
	List<Pokemon> findByTypesAndLegendaryAndReadyToBattle(String _type1, String _type2, boolean _legendary, boolean _readyToBattle);
}

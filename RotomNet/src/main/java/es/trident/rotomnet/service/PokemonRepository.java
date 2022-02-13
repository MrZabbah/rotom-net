package es.trident.rotomnet.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.trident.rotomnet.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	List<Pokemon> findBy_type1Or_type2AllIgnoreCaseAndReadyToBattle(String type1, String type2, boolean readyToBattle);
	List<Pokemon> findBy_legendaryAnd_readyToBattle(boolean legendary, boolean readyToBattle);
	@Query("select p from Pokemon where (_type1 = ?1 or _type2 =?2)and _legendary = ?3")
	List<Pokemon> findByTypesAndLegendary(String type1, String type2, boolean legendaryCheck);
}

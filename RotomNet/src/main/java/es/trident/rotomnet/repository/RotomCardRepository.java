package es.trident.rotomnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.RotomCard;

public interface RotomCardRepository extends JpaRepository<RotomCard, Pokemon>{
	@Query(nativeQuery =true,value = "SELECT * FROM rotom_card as e WHERE e.pokemon_dex_id NOT IN (:pkmns)")
	List<RotomCard> findByPokemonNotIn(@Param("pkmns")List<Pokemon> pkmns);
}

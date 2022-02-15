package es.trident.rotomnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.trident.rotomnet.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

}

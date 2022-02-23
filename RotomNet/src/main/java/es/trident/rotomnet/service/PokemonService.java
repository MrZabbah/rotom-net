/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.repository.PokemonRepository;
import es.trident.rotomnet.service.util.Utils;

/**
 * PokemonService Servicio dedicado al control y manejo de las tablas
 * relacionadas con Pokemon, dentro de la base de datos. Dentro de estas tablas
 * se encuentran: Pokemon, RotomCard y UserRotomCard
 */
@Service
public class PokemonService {

	private final String[] POKEMON_TYPES = { "Fire", "Water", "Grass", "Electric", "Ground", "Rock", "Poison",
			"Psychic", "Flying", "Bug", "Normal", "Ghost", "Fighting", "Steel", "Ice", "Dragon", "Dark", "Fairy" };
	private PokemonRepository pokemonRepository;

	public PokemonService(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}

	/**
	 * Dado un array de tipo Booleano, ordenada según POKEMON_TYPES, devuelve una
	 * lista de Strings de tipos
	 * 
	 * @param types Array de booleanos
	 * @return Lista de tipos
	 */
	public ArrayList<String> getTypesFromRequest(boolean[] types) {
		ArrayList<String> selectedTypes = new ArrayList<String>();
		int i = 0;
		int count = 0;

		while (i < types.length && count < 6) {

			if (types[i]) {
				selectedTypes.add(POKEMON_TYPES[i]);
				count++;
			}

			i += 1;
		}

		return selectedTypes;
	}

	public ArrayList<Pokemon> getRandomPokemonListBy(int number, boolean legendaries, ArrayList<String> types) {
		ArrayList<Pokemon> pokemonList;

		if (types == null || types.isEmpty()) {
			pokemonList = (ArrayList<Pokemon>) pokemonRepository.findByLegendaryAndReadyToBattle(legendaries, true);
		} else {
			pokemonList = (ArrayList<Pokemon>) pokemonRepository.findByTypesAndLegendaryAndReadyToBattle(types,
					legendaries, true);
		}

		return Utils.getRandomList(number, pokemonList);
	}

}

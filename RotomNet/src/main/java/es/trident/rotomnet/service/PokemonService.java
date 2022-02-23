/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.model.UserRotomCard;
import es.trident.rotomnet.repository.PokemonRepository;
import es.trident.rotomnet.repository.RotomCardRepository;
import es.trident.rotomnet.repository.UserRotomCardRepository;

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
	private RotomCardRepository cardRepository;
	private UserRotomCardRepository userCardsRepository;

	public PokemonService(PokemonRepository pokemonRepository, RotomCardRepository cardRepository,
			UserRotomCardRepository userCardsRepository) {
		this.pokemonRepository = pokemonRepository;
		this.cardRepository = cardRepository;
		this.userCardsRepository = userCardsRepository;
	}

	public List<List<RotomCard>> getAllCards() {
		List<RotomCard> cards = cardRepository.findAll();
		List<List<RotomCard>> subCardSets = Lists.partition(cards, 5);
		return subCardSets;
	}

	public List<List<UserRotomCard>> getUserCards(User user) {
		List<UserRotomCard> cards = userCardsRepository.findByUser(user);
		List<Pokemon> userDeck = new ArrayList<>();
		List<List<UserRotomCard>> subCardSets;
		List<RotomCard> pokemonCardsNotOwned;
		Ordering<UserRotomCard> byPokemon = new OrderingByPokemon();

		userDeck.add(new Pokemon(0));

		for (UserRotomCard userRotomCard : cards) {
			userDeck.add(userRotomCard.getRottomCard().getPokemon());
		}

		pokemonCardsNotOwned = cardRepository.findByPokemonNotIn(userDeck);

		for (RotomCard rotomCard : pokemonCardsNotOwned) {
			UserRotomCard noOwnedCard = new UserRotomCard(user, rotomCard, false);
			noOwnedCard.setAmount(0);
			cards.add(noOwnedCard);
		}

		Collections.sort(cards, byPokemon);
		subCardSets = Lists.partition(cards, 5);
		return subCardSets;
	}

	/**
	 * Método encargado de devolver un String en formato {Discovered:
	 * [cartasPoseidas / cartasTotales]}
	 * 
	 * @param user Usuario a analizar
	 * @return String formateado
	 */
	public String getUserDiscoverRatio(User user) {
		String s = String.format("Discovered: [%d / %d]", userCardsRepository.countByUser(user), cardRepository.count());
		return s += String.format(" Shinies: [%d / %d]", userCardsRepository.countByUserShiny(user), cardRepository.count());
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

		return getRandomList(number, pokemonList);
	}

	public ArrayList<RotomCard> getRandomCardTeam() {
		List<RotomCard> cardList = cardRepository.findAll();
		return getRandomList(6, cardList);
	}
	
	public void addCardToUser(RotomCard rotomCard, User user, boolean shiny) {
		UserRotomCard card = userCardsRepository.findByUserAndRotomCard(user, rotomCard);
		
		if (card != null) {
			card.increaseAmountBy(1);
			card.updateShinyCondition(shiny);
			userCardsRepository.save(card);
		} else {
			userCardsRepository.save(new UserRotomCard(user, rotomCard, shiny));
		}
		
	}
	
	private <T> ArrayList<T> getRandomList(int number, List<T> fullList) {
		ArrayList<Integer> selectedIndex = new ArrayList<Integer>();
		ArrayList<T> selected = new ArrayList<T>();
		int index;
		for (int i = 0; i < number; ++i) {

			do {
				index = ((int) (Math.random() * 100)) % fullList.size();
			} while (selectedIndex.contains(index));

			selectedIndex.add(index);
			selected.add(fullList.get(index));
		}

		return selected;
	}
	
	private class OrderingByPokemon extends Ordering<UserRotomCard> {
		
		@Override
		public int compare(UserRotomCard s1, UserRotomCard s2) {
			return Ints.compare(s1.getRottomCard().getPokemonDexIndex(), s2.getRottomCard().getPokemonDexIndex());
		}

	}

}

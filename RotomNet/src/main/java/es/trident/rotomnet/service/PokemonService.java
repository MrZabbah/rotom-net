package es.trident.rotomnet.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.model.UserRotomCard;
import es.trident.rotomnet.repository.PokemonRepository;
import es.trident.rotomnet.repository.RotomCardRepository;
import es.trident.rotomnet.repository.UserRotomCardRepository;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	@Autowired
	private RotomCardRepository cardRepository;
	@Autowired
	private UserRotomCardRepository userCardsRepository;
	@Autowired
	private UserService userService;

	private final String[] POKEMON_TYPES = { "Fire", "Water", "Grass", "Electric", "Ground", "Rock", "Poison",
			"Psychic", "Flying", "Bug", "Normal", "Ghost", "Fighting", "Steel", "Ice", "Dragon", "Dark", "Fairy" };

	public List<List<RotomCard>> getAllCards() {

		List<RotomCard> cards = cardRepository.findAll();
		List<List<RotomCard>> subCardSets = Lists.partition(cards, 5);

		return subCardSets;
	}

	public List<List<UserRotomCard>> getUserCards(User user) {
		List<UserRotomCard> cards = userCardsRepository.findByUser(user);
		List<Pokemon> userDeck = new ArrayList<>();
		userDeck.add(new Pokemon(0));

		for (UserRotomCard userRotomCard : cards) {
			userDeck.add(userRotomCard.getRottomCard().getPokemon());
		}

		List<RotomCard> pokemonCardsNotOwned = cardRepository.findByPokemonNotIn(userDeck);

		for (RotomCard rotomCard : pokemonCardsNotOwned) {
			UserRotomCard noOwnedCard = new UserRotomCard(user, rotomCard, false);
			noOwnedCard.setAmount(0);
			cards.add(noOwnedCard);
		}

		Ordering<UserRotomCard> byPokemon = new OrderingByPokemon();
		Collections.sort(cards, byPokemon);

		List<List<UserRotomCard>> subCardSets = Lists.partition(cards, 5);

		return subCardSets;
	}
	
	public String getUserDiscoverRatio(User user) {
		
		return String.format("Discovered: [%d / %d]", userCardsRepository.countByUser(user), cardRepository.count());
	}
	
	private class OrderingByPokemon extends Ordering<UserRotomCard> {
		@Override
		public int compare(UserRotomCard s1, UserRotomCard s2) {
			return Ints.compare(s1.getRottomCard().getPokemonDexIndex(), s2.getRottomCard().getPokemonDexIndex());
		}
	}

	public ArrayList<String> checkAnyType(boolean[] types) {
		ArrayList<String> selectedTypes = new ArrayList<String>();
		int i = 0;
		int count = 0;
		while (i < types.length && count < 6) {
			if (types[i]) {
				selectedTypes.add(POKEMON_TYPES[i]);
				count++;
			}
			i++;
		}
		return selectedTypes;
	}


	private ArrayList<Pokemon> getNonLegendaries(int number) {
		ArrayList<Pokemon> auxList = new ArrayList<Pokemon>();
		ArrayList<Pokemon> nonLegendaries = (ArrayList<Pokemon>) pokemonRepository
				.findByLegendaryAndReadyToBattle(false, true);
		ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
		int index = -1;
		for (int i = 0; i < number; ++i) {
			do {
				index = ((int) (Math.random() * 100)) % nonLegendaries.size();
			} while (selectedIndexes.contains(index));
			selectedIndexes.add(index);
			auxList.add(nonLegendaries.get(index));
		}
		return auxList;
	}

	private ArrayList<Pokemon> fillListByTypes(ArrayList<String> types, boolean legendaries) {
		ArrayList<Pokemon> myList = new ArrayList<Pokemon>();
		for (int i = 0; i < types.size(); ++i) {
			myList.addAll((ArrayList<Pokemon>) pokemonRepository.findByTypesAndLegendaryAndReadyToBattle(types.get(i),
					types.get(i), legendaries, true));
		}
		return myList;
	}

	private ArrayList<Pokemon> getNonLegendariesByType(ArrayList<String> types, int number) {
		ArrayList<Pokemon> auxList = new ArrayList<Pokemon>();
		ArrayList<Pokemon> nonLegendaries = fillListByTypes(types, false);
		ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
		int index = -1;
		for (int i = 0; i < number; ++i) {
			do {
				index = ((int) (Math.random() * 100)) % nonLegendaries.size();
			} while (selectedIndexes.contains(index));
			selectedIndexes.add(index);
			auxList.add(nonLegendaries.get(index));
		}
		return auxList;
	}

	public Team getRandomTeam(String teamName, ArrayList<String> types, boolean legendaryCheck) {
		ArrayList<Pokemon> team;
		int index = -1;
		if (types.isEmpty()) {
			if (legendaryCheck) {
				ArrayList<Pokemon> legendaries = (ArrayList<Pokemon>) pokemonRepository
						.findByLegendaryAndReadyToBattle(legendaryCheck, true);
				index = ((int) (Math.random() * 100)) % (legendaries.size());
				team = getNonLegendaries(5);
				team.add(legendaries.get(index));
			} else {
				team = getNonLegendaries(6);
			}
		} else {
			if (legendaryCheck) {
				ArrayList<Pokemon> legendaries = fillListByTypes(types, legendaryCheck);
				index = (int) (Math.random() * 100) % legendaries.size();
				team = getNonLegendariesByType(types, 5);
				team.add(legendaries.get(index));
			} else {
				team = getNonLegendariesByType(types, 6);
			}
		}
		Team myTeam = new Team(team, teamName);
		return myTeam;
	}
}

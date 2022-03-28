/**
 * UserRotomCardRepository: Repositorio encargado de las consultas de la tabla
 * de UserRotomCard a la base de datos
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
import es.trident.rotomnet.repository.RotomCardRepository;
import es.trident.rotomnet.repository.UserRotomCardRepository;
import es.trident.rotomnet.service.util.Utils;

/**
 * CardService: Servicio dedicado a la gestión de cartas
 */
@Service
public class CardService {

	private RotomCardRepository cardRepository;
	private UserRotomCardRepository userCardsRepository;

	public CardService(RotomCardRepository cardRepository, UserRotomCardRepository userCardsRepository) {
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

		userDeck.add(Pokemon.NOT_FOUND);

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
		String s = String.format("Discovered: [%d / %d]", deckCount(user), cardRepository.count());

		return s += String.format(" Shinies: [%d / %d]", userCardsRepository.countByUserShiny(user),
				cardRepository.count());
	}

	public ArrayList<RotomCard> getRandomCardTeam() {
		List<RotomCard> cardList = cardRepository.findAll();

		return Utils.getRandomList(6, cardList);
	}

	public UserRotomCard addCardToUser(RotomCard rotomCard, User user, boolean shiny) {
		UserRotomCard card = userCardsRepository.findByUserAndRotomCard(user, rotomCard);

		if (card != null) {
			card.increaseAmountBy(1);
			card.updateShinyCondition(shiny);
			userCardsRepository.save(card);
		} else {
			card = new UserRotomCard(user, rotomCard, shiny);
			userCardsRepository.save(card);
		}

		return card;
	}

	public int deckCount(User user) {
		return userCardsRepository.countByUser(user);
	}

	private class OrderingByPokemon extends Ordering<UserRotomCard> {

		@Override
		public int compare(UserRotomCard s1, UserRotomCard s2) {
			return Ints.compare(s1.getRottomCard().getPokemonDexIndex(), s2.getRottomCard().getPokemonDexIndex());
		}

	}

}

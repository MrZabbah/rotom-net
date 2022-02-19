package es.trident.rotomnet.service;

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
import es.trident.rotomnet.repository.UserRepository;
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
	private UserRepository userRepository;

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

	public void create() {
		List<Pokemon> pokes = new ArrayList<>();
		pokes.add(new Pokemon(1, "Bulbasaur", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Poison", 5, 8, -1,
				3, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(2, "Ivysaur", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Poison", 3, 9, -1, 9,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(4, "Charmander", "Unknown", "Unknown", "Unknown", "Unknown", "Fire", "", 1, 1, -1, 4, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(5, "Charmeleon", "Unknown", "Unknown", "Unknown", "Unknown", "Fire", "", 2, 6, -1, 6, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(7, "Squirtle", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 3, 2, -1, 3, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(8, "Wartortle", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 6, 9, -1, 2, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(10, "Caterpie", "Unknown", "Unknown", "Unknown", "Unknown", "Bug", "", 6, 4, -1, 9, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(11, "Metapod", "Unknown", "Unknown", "Unknown", "Unknown", "Bug", "", 2, 2, -1, 9, -1, -1,
				"None", "None", "", false, false));
		pokes.add(new Pokemon(13, "Weedle", "Unknown", "Unknown", "Unknown", "Unknown", "Bug", "Poison", 6, 3, -1, 9,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(14, "Kakuna", "Unknown", "Unknown", "Unknown", "Unknown", "Bug", "Poison", 2, 7, -1, 8,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(16, "Pidgey", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "Flying", 3, 1, -1, 8,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(17, "Pidgeotto", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "Flying", 2, 6, -1,
				10, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(19, "Rattata", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "", 2, 7, -1, 7, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(21, "Spearow", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "Flying", 5, 5, -1,
				9, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(23, "Ekans", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 6, 10, -1, 6, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(25, "Pikachu", "Unknown", "Unknown", "Unknown", "Unknown", "Electric", "", 1, 10, -1, 8,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(27, "Sandshrew", "Unknown", "Unknown", "Unknown", "Unknown", "Ground", "", 6, 9, -1, 1,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(29, "Nidoran(F)", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 1, 6, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(30, "Nidorina", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 6, 3, -1, 7, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(32, "Nidoran(M)", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 2, 4, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(33, "Nidorino", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 5, 7, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(35, "Clefairy", "Unknown", "Unknown", "Unknown", "Unknown", "Fairy", "", 6, 10, -1, 3, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(37, "Vulpix", "Unknown", "Unknown", "Unknown", "Unknown", "Fire", "", 2, 5, -1, 10, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(39, "Jigglypuff", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "Fairy", 6, 7, -1,
				5, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(41, "Zubat", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "Flying", 2, 10, -1, 7,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(42, "Golbat", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "Flying", 6, 4, -1,
				10, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(43, "Oddish", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Poison", 6, 10, -1,
				10, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(44, "Gloom", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Poison", 1, 4, -1, 2,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(46, "Paras", "Unknown", "Unknown", "Unknown", "Unknown", "Bug", "Grass", 4, 9, -1, 5, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(48, "Venonat", "Unknown", "Unknown", "Unknown", "Unknown", "Bug", "Poison", 3, 5, -1, 4,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(50, "Diglett", "Unknown", "Unknown", "Unknown", "Unknown", "Ground", "", 4, 1, -1, 7, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(52, "Meowth", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "", 3, 6, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(54, "Psyduck", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 2, 3, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(56, "Mankey", "Unknown", "Unknown", "Unknown", "Unknown", "Fighting", "", 5, 2, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(58, "Growlithe", "Unknown", "Unknown", "Unknown", "Unknown", "Fire", "", 1, 3, -1, 10, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(60, "Poliwag", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 5, 6, -1, 10, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(61, "Poliwhirl", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 5, 6, -1, 5, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(63, "Abra", "Unknown", "Unknown", "Unknown", "Unknown", "Psychic", "", 1, 10, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(64, "Kadabra", "Unknown", "Unknown", "Unknown", "Unknown", "Psychic", "", 4, 10, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(66, "Machop", "Unknown", "Unknown", "Unknown", "Unknown", "Fighting", "", 1, 2, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(67, "Machoke", "Unknown", "Unknown", "Unknown", "Unknown", "Fighting", "", 2, 3, -1, 1,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(69, "Bellsprout", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Poison", 5, 10,
				-1, 1, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(70, "Weepinbell", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Poison", 5, 9, -1,
				8, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(72, "Tentacool", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "Poison", 6, 5, -1,
				4, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(74, "Geodude", "Unknown", "Unknown", "Unknown", "Unknown", "Rock", "Ground", 4, 5, -1, 2,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(75, "Graveler", "Unknown", "Unknown", "Unknown", "Unknown", "Rock", "Ground", 4, 6, -1, 4,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(77, "Ponyta", "Unknown", "Unknown", "Unknown", "Unknown", "Fire", "", 1, 10, -1, 9, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(80, "Slowbro", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "Psychic", 5, 4, -1,
				5, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(81, "Magnemite", "Unknown", "Unknown", "Unknown", "Unknown", "Electric", "Steel", 2, 3,
				-1, 6, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(82, "Magneton", "Unknown", "Unknown", "Unknown", "Unknown", "Electric", "Steel", 4, 3, -1,
				4, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(84, "Doduo", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "Flying", 2, 2, -1, 5,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(86, "Seel", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 6, 10, -1, 6, -1, -1,
				"None", "None", "", false, false));
		pokes.add(new Pokemon(88, "Grimer", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 5, 7, -1, 9, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(90, "Shellder", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 4, 4, -1, 2, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(92, "Gastly", "Unknown", "Unknown", "Unknown", "Unknown", "Ghost", "Poison", 3, 10, -1, 4,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(93, "Haunter", "Unknown", "Unknown", "Unknown", "Unknown", "Ghost", "Poison", 5, 4, -1, 9,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(95, "Onix", "Unknown", "Unknown", "Unknown", "Unknown", "Rock", "Ground", 6, 1, -1, 7, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(96, "Drowzee", "Unknown", "Unknown", "Unknown", "Unknown", "Psychic", "", 4, 8, -1, 9, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(98, "Krabby", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 4, 4, -1, 3, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(100, "Voltorb", "Unknown", "Unknown", "Unknown", "Unknown", "Electric", "", 2, 3, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(102, "Exeggcute", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "Psychic", 5, 3,
				-1, 3, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(104, "Cubone", "Unknown", "Unknown", "Unknown", "Unknown", "Ground", "", 1, 9, -1, 7, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(108, "Lickitung", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "", 6, 1, -1, 10,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(109, "Koffing", "Unknown", "Unknown", "Unknown", "Unknown", "Poison", "", 2, 7, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(111, "Rhyhorn", "Unknown", "Unknown", "Unknown", "Unknown", "Ground", "Rock", 6, 4, -1, 7,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(114, "Tangela", "Unknown", "Unknown", "Unknown", "Unknown", "Grass", "", 1, 4, -1, 5, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(116, "Horsea", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 4, 3, -1, 9, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(117, "Seadra", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 5, 9, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(118, "Goldeen", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 5, 4, -1, 10, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(120, "Staryu", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 5, 3, -1, 3, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(125, "Electabuzz", "Unknown", "Unknown", "Unknown", "Unknown", "Electric", "", 6, 3, -1,
				10, -1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(126, "Magmar", "Unknown", "Unknown", "Unknown", "Unknown", "Fire", "", 3, 2, -1, 8, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(129, "Magikarp", "Unknown", "Unknown", "Unknown", "Unknown", "Water", "", 1, 7, -1, 4, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(132, "Ditto", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "", 5, 6, -1, 6, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(133, "Eevee", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "", 1, 5, -1, 7, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(137, "Porygon", "Unknown", "Unknown", "Unknown", "Unknown", "Normal", "", 3, 6, -1, 9, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(138, "Omanyte", "Unknown", "Unknown", "Unknown", "Unknown", "Rock", "Water", 3, 7, -1, 2,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(140, "Kabuto", "Unknown", "Unknown", "Unknown", "Unknown", "Rock", "Water", 3, 7, -1, 2,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(147, "Dratini", "Unknown", "Unknown", "Unknown", "Unknown", "Dragon", "", 5, 7, -1, 4, -1,
				-1, "None", "None", "", false, false));
		pokes.add(new Pokemon(148, "Dragonair", "Unknown", "Unknown", "Unknown", "Unknown", "Dragon", "", 3, 3, -1, 6,
				-1, -1, "None", "None", "", false, false));
		pokes.add(new Pokemon(3, "Venusaur", "Giga drain", "Sludge bomb", "Leech Seed", "Substitute", "Grass", "Poison",
				252, 0, 252, 0, 6, 0, "Chlorophyll", "Modest", "Black sludge", false, true));
		pokes.add(new Pokemon(45, "Vileplume", "Sludge bomb", "Giga drain", "Sleep powder", "Strength sap", "Grass",
				"Poison", 252, 0, 252, 0, 6, 0, "Effect spore", "Modest", "Black Sludge", false, true));
		pokes.add(new Pokemon(71, "Victreebel", "Double-edge", "Leaf blade", "Sleep powder", "Swords dance", "Grass",
				"Poison", 252, 252, 0, 0, 0, 6, "Adamant", "Chlorophyll", "Black sludge", false, true));
		pokes.add(new Pokemon(103, "Exeggutor", "Leech seed", "Giga drain", "Psychic", "Substitute", "Grass", "Psychic",
				252, 0, 252, 0, 6, 0, "Harvest", "Calm", "Sitrus berry", false, true));
		pokes.add(new Pokemon(6, "Charizard", "Fire blast", "Air slash", "Earthquake", "Roost", "Fire", "Flying", 0, 6,
				252, 0, 0, 252, "Solar Power", "Modest", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(38, "Ninetales", "Fire blast", "Substitute", "Nasty plot", "Solar beam", "Fire", "", 0, 0,
				252, 0, 6, 252, "Drought", "Modest", "Heat rock", false, true));
		pokes.add(new Pokemon(59, "Arcanine", "Flare blitz", "Extreme speed", "Will-o-wisp", "Morning sun", "Fire", "",
				252, 252, 0, 0, 0, 6, "Flash fire", "Adamant", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(78, "Rapidash", "Flare blitz", "Will-o wisp", "Morning sun", "Stomping tantrum", "Fire",
				"", 6, 252, 0, 0, 0, 252, "Flash fire", "Adamant", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(136, "Flareon", "Flare blitz", "Flame charge", "Superpower", "Facade", "Fire", "", 252,
				252, 0, 6, 0, 0, "Flash fire", "Adamant", "Toxic orb", false, true));
		pokes.add(new Pokemon(146, "Moltres", "Fire blast", "Roost", "Scorching sands", "Air slash", "Fire", "Flying",
				6, 0, 252, 0, 0, 252, "Flame body", "Modest", "Heavy-Duty boots", true, true));
		pokes.add(new Pokemon(9, "Blastoise", "Protect", "Rapid Spin", "Ice beam", "Scald", "Water", "", 252, 0, 252, 0,
				6, 0, "Torrent", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(130, "Gyarados", "Bounce", "Waterfall", "Dragon dance", "Power whip", "Water", "Flying",
				6, 252, 0, 0, 0, 252, "Moxie", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(119, "Seaking", "Scald", "Megahorn", "Drill run", "Knock off", "Water", "", 252, 252, 6,
				0, 0, 0, "Lightning rod", "Naive", "Life orb", false, true));
		pokes.add(new Pokemon(55, "Golduck", "Scald", "Calm mind", "Ice beam", "Hydro pump", "Water", "", 0, 0, 252, 0,
				6, 252, "Swift swim", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(62, "Poliwrath", "Close combat", "Liquidation", "Darkest lariat", "Rain dance", "Water",
				"Fighting", 252, 252, 0, 6, 0, 0, "Swift swim", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(73, "Tentacruel", "Scald", "Haze", "Rapid spin", "Sludge bomb", "Water", "Poison", 252, 0,
				6, 0, 252, 0, "Clear body", "Calm", "Black sludge", false, true));
		pokes.add(new Pokemon(99, "Kingler", "Liquidation", "X-scissor", "Agility", "Swords dance", "Water", "", 252,
				252, 0, 6, 0, 0, "Sheer force", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(134, "Vaporeon", "Scald", "Wish", "Toxic", "Protect", "Water", "", 252, 0, 252, 0, 6, 0,
				"Water absorb", "Calm", "Leftovers", false, true));
		pokes.add(new Pokemon(26, "Raichu", "Nasty plot", "Thunderbolt", "Volt switch", "Surf", "Electric", "", 0, 0,
				252, 0, 6, 252, "Modest", "Lightning rod", "Life orb", false, true));
		pokes.add(new Pokemon(135, "Jolteon", "Volt switch", "Tunderbolt", "Hyper voice", "Shadow ball", "Electric", "",
				0, 0, 252, 0, 6, 252, "Volt absorb", "Timid", "Choice specs", false, true));
		pokes.add(new Pokemon(101, "Electrode", "Thunderbolt", "Explosion", "Volt switch", "Signal beam", "Electric",
				"", 0, 0, 252, 0, 6, 252, "Aftermath", "Modest", "Choice specs", false, true));
		pokes.add(new Pokemon(145, "Zapdos", "Discharge", "Roost", "Hurricane", "Heat wave", "Electric", "Flying", 0, 0,
				252, 6, 0, 252, "Static", "Modest", "Leftovers", true, true));
		pokes.add(new Pokemon(28, "Sandslash", "Swords Dance", "Earthquake", "Rapid Spin", "Triple axel", "Ground", "",
				6, 252, 0, 252, 0, 0, "Adamant", "Sand rush", "Leftovers", false, true));
		pokes.add(new Pokemon(51, "Dugtrio", "Earthquake", "Stone edge", "Quick attack", "Slash", "Ground", "", 6, 252,
				0, 0, 0, 252, "Arena trap", "Adamant", "Choice band", false, true));
		pokes.add(new Pokemon(105, "Marowak", "Earthquake", "Knock off", "Stone edge", "Double edge", "Ground", "", 6,
				252, 0, 252, 0, 0, "Rock head", "Adamant", "Thick club", false, true));
		pokes.add(new Pokemon(76, "Golem", "Earthquake", "Stone edge", "Stealth rock", "Double edge", "Rock", "Ground",
				6, 252, 0, 252, 0, 0, "Sturdy", "Impish", "Leftovers", false, true));
		pokes.add(new Pokemon(112, "Rhydon", "Stealth rock", "Earthquake", "Stone edge", "Megahorn", "Rock", "Ground",
				252, 6, 252, 0, 0, 0, "Rock head", "Impish", "Eviolite", false, true));
		pokes.add(new Pokemon(139, "Omastar", "Shell smash", "Ice beam", "Earth power", "Hydro pump", "Water", "Rock",
				0, 0, 252, 0, 6, 252, "Swift swim", "Modest", "White herb", false, true));
		pokes.add(new Pokemon(141, "Kabutops", "Liquidation", "Aqua jet", "Swords dance", "Stone edge", "Water", "Rock",
				0, 252, 0, 6, 0, 252, "Swift swim", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(142, "Aerodactyl", "Dual wingbeat", "Stone edge", "Hone claws", "Earthquake", "Rock",
				"Flying", 0, 252, 6, 0, 0, 252, "Rock head", "Jolly", "Life orb", false, true));
		pokes.add(new Pokemon(24, "Arbok", "Gunk shot", "Coil", "Glare", "Crunch", "Poison", "", 252, 252, 0, 6, 0, 0,
				"Intimidate", "Adamant", "Black sludge", false, true));
		pokes.add(new Pokemon(31, "Nidoqueen", "Earth power", "Sludge wave", "Toxic spikes", "Ice beam", "Poison",
				"Ground", 6, 0, 252, 0, 0, 252, "Modest", "Sheer force", "Life orb", false, true));
		pokes.add(new Pokemon(34, "Nidoking", "Earth power", "Sludge wave", "Superpower", "Ice beam", "Poison",
				"Ground", 0, 6, 252, 0, 0, 252, "Modest", "Sheer force", "Life orb", false, true));
		pokes.add(new Pokemon(89, "Muk", "Gunk shot", "Curse", "Knock off", "Substitute", "Poison", "", 252, 252, 0, 6,
				0, 0, "Poison Touch", "Adamant", "Black sludge", false, true));
		pokes.add(new Pokemon(110, "Weezing", "Sludge bomb", "Pain split", "Will o wisp", "Toxic spikes", "Poison", "",
				252, 0, 6, 252, 0, 0, "Levitate", "Bold", "Black sludge", false, true));
		pokes.add(new Pokemon(65, "Alakazam", "Psyshock", "Shadow ball", "Energy ball", "Nasty plot", "Psychic", "", 0,
				0, 252, 0, 6, 252, "Magic guard", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(79, "Slowbro", "Future sight", "Teleport", "Scald", "Toxic", "Water", "Psychic", 252, 0,
				6, 252, 0, 0, "Regenerator", "Bold", "Leftovers", false, true));
		pokes.add(new Pokemon(97, "Hypno", "Head smash", "Zen headbutt", "Toxic", "Protect", "Psychic", "", 252, 252, 0,
				0, 0, 6, "Insomnia", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(121, "Starmie", "Scald", "Psyshock", "Thunderbolt", "Rapid spin", "Water", "Psychic", 0,
				0, 252, 0, 6, 252, "Natural cure", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(150, "Mewtwo", "Psystrike", "Nasty plot", "Fire blast", "Recover", "Psychic", "", 0, 0,
				255, 0, 6, 255, "Pressure", "Modest", "Leftovers", true, true));
		pokes.add(new Pokemon(151, "Mew", "Psychic fangs", "Flare blitz", "Dragon dance", "Close combat", "Psychic", "",
				6, 255, 0, 0, 0, 255, "Synchronize", "Adamant", "Leftovers", true, true));
		pokes.add(new Pokemon(18, "Pidgeot", "Hurricane", "Heat wave", "Roost", "Whirlwind", "Flying", "", 0, 0, 252, 0,
				6, 252, "Keen Eye", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(22, "Fearow", "Drill peck", "Double edge", "Roost", "Focus energy", "Normal", "Flying", 6,
				252, 0, 0, 0, 252, "Keen eye", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(83, "Farfetch'd", "Brave bird", "Leaf blade", "Swords dance", "Slash", "Normal", "Flying",
				0, 252, 0, 6, 0, 252, "Keen eye", "Adamant", "Stick", false, true));
		pokes.add(new Pokemon(85, "Dodrio", "Drill peck", "Roost", "Double edge", "Swords dance", "Normal", "Flying", 0,
				252, 0, 6, 0, 252, "Early bird", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(12, "Butterfree", "Quiver dance", "Sleep powder", "Hurricane", "Bug buzz", "Bug",
				"Flying", 0, 0, 252, 0, 6, 252, "Tinted lens", "Modest", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(15, "Beedrill", "Drill run", "Poison jab", "Aerial ace", "Bug bite", "Bug", "Poison", 0,
				252, 0, 6, 0, 252, "Swarm", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(47, "Parasect", "X-Scissor", "Leech life", "Seed bomb", "Spore", "Bug", "Grass", 252, 252,
				6, 0, 0, 0, "Dry skin", "Adamant", "Focus sash", false, true));
		pokes.add(new Pokemon(49, "Venomoth", "Sludge bomb", "Sleep powder", "Quiver dance", "Air slash", "Bug",
				"Poison", 0, 0, 252, 0, 6, 252, "Tinted lens", "Modest", "Black sludge", false, true));
		pokes.add(new Pokemon(123, "Scyther", "Dual wingbeat", "Knock off", "Roost", "Swords dance", "Bug", "Fying", 6,
				252, 0, 0, 0, 252, "Technician", "Adamant", "Eviolite", false, true));
		pokes.add(new Pokemon(127, "Pinsir", "Earthquake", "Knock off", "X-Scissor", "Close combat", "Bug", "", 6, 252,
				0, 0, 0, 252, "Moxie", "Adamant", "Choice band", false, true));
		pokes.add(new Pokemon(20, "Raticate", "Super fang", "Facade", "Crunch", "Quick Attack", "Normal", "", 6, 252, 0,
				0, 0, 252, "Guts", "Adamant", "Flame orb", false, true));
		pokes.add(new Pokemon(53, "Persian", "Double-edge", "Knock off", "U-turn", "Fake out", "Normal", "", 0, 252, 0,
				6, 0, 252, "Limber", "Adamant", "Choice band", false, true));
		pokes.add(new Pokemon(113, "Chansey", "Soft boiled", "Seismic toss", "Toxic", "Protect", "Normal", "", 252, 0,
				6, 0, 252, 0, "Natural cure", "Calm", "Eviolite", false, true));
		pokes.add(new Pokemon(115, "Kangaskhan", "Double edge", "Sucker punch", "Fake out", "Earthquake", "Normal", "",
				252, 252, 0, 6, 0, 0, "Inner focus", "Adamant", "Assault vest", false, true));
		pokes.add(new Pokemon(128, "Tauros", "Body slam", "Earthquake", "Zen headbutt", "Rock slide", "Normal", "", 6,
				252, 0, 0, 0, 252, "Sheer force", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(143, "Snorlax", "Curse", "Rest", "Darkest lariat", "Double edge", "Normal", "", 252, 252,
				0, 0, 6, 0, "Thick fat", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(94, "Gengar", "Shadow ball", "Nasty plot", "Sludge wave", "Substitute", "Ghost", "Poison",
				0, 0, 252, 0, 6, 252, "Cursed body", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(57, "Primeape", "Close combat", "Double edge", "Ice punch", "Rock slide", "Fighting", "",
				0, 252, 0, 6, 0, 252, "Anger point", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(68, "Machamp", "Close combat", "Stone edge", "Bullet punch", "Facade", "Fighting", "",
				252, 252, 0, 6, 0, 0, "Guts", "Adamant", "Flame orb", false, true));
		pokes.add(new Pokemon(106, "Hitmonlee", "High jump kick", "Curse", "Knock off", "Poison jab", "Fighting", "", 6,
				252, 0, 0, 0, 252, "Unburden", "Adamant", "White herb", false, true));
		pokes.add(new Pokemon(107, "Hitmonchan", "Drain punch", "Mach punch", "Fire punch", "Bulk up", "Fighting", "",
				6, 252, 0, 0, 0, 252, "Iron fist", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(87, "Dewgong", "Surf", "Ice beam", "Toxic", "Protect", "Water", "Ice", 252, 0, 6, 0, 252,
				0, "Thick fat", "Calm", "Leftovers", false, true));
		pokes.add(new Pokemon(91, "Cloyster", "Hydro pump", "Icicle spear", "Shell smash", "Rock blast", "Water", "Ice",
				6, 252, 0, 252, 0, 0, "Skill link", "Adamant", "White herb", false, true));
		pokes.add(new Pokemon(124, "Jynx", "Nasty plot", "Lovely kiss", "Psyshock", "Ice beam", "Ice", "Psychic", 0, 0,
				252, 0, 6, 252, "Dry skin", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(131, "Lapras", "Freeze dry", "Ice beam", "Sparkling aria", "Toxic", "Water", "Ice", 252,
				0, 252, 0, 6, 0, "Water absorb", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(144, "Articuno", "Freeze dry", "Roost", "Ice beam", "Toxic", "Ice", "Flying", 252, 0, 252,
				0, 0, 6, "Pressure", "Modest", "Heavy-Duty boots", true, true));
		pokes.add(new Pokemon(149, "Dragonite", "Earthquake", "Dual wingbeat", "Dragon dance", "Extreme speed",
				"Dragon", "Flying", 6, 252, 0, 0, 0, 252, "Multiscale", "Adamant", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(36, "Clefable", "Moonblast", "Soft-boiled", "Calm mind", "Fire blast", "Fairy", "", 252,
				0, 6, 0, 252, 0, "Magic guard", "Calm", "Life orb", false, true));
		pokes.add(new Pokemon(40, "Wigglituff", "Dazzling gleam", "Reflect", "Light screen", "Fire blast", "Fairy",
				"Normal", 252, 0, 252, 0, 6, 0, "Cute charm", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(122, "Mr. Mime", "Psychic", "Dazzling gleam", "Nasty plot", "Light screen", "Psychic",
				"Fairy", 0, 0, 252, 0, 6, 252, "Filter", "Modest", "Life orb", false, true));
		
		List<List<Integer>> stats = new ArrayList<List<Integer>>();
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 4, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 7, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 1, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 5, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 6, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 4, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 10, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 5, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 4, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 10, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 6, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 10, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 4, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 3, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 4, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 8, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 2, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 2, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 1, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 9, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 1, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 6, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 8, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 5, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 9, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 2, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 4, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 4, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 3, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 9, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 6, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 4, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 3, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 5, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 9, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 9, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 10, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 5, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 2, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 8, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 1, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 3, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 5, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 6, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 9, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 10, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 6, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 7, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 3, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 3, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 2, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 10, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 2, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 5, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 9, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 2, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 1, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 5, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 10, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 8, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 5, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 2, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 7, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 4, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 2, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 7, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 10, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 8, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 1, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 4, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 5, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 10, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 1, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 9, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 1, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 6, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 10, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 3, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 4, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 6, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 5, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 6, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 10, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 8, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 4, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 1, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 10, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 3, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 6, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 6, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 4, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 3, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 10, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 8, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 5, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 5, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 8, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 4, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 10, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 5, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 1, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 10, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 10, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 3, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 3, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 9, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 10, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 10, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 1, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 9, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 9, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 7, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 10, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 2, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 10, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 5, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 6, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 3, 6)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 5, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 7, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 5, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 10, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(8, 5, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 1, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 8, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 7, 2)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 7, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 1, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 4, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 9, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(3, 7, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 5, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 2, 10)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 6, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 2, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 8, 5)));
		stats.add(new ArrayList<Integer>(Arrays.asList(4, 2, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 9, 3)));
		stats.add(new ArrayList<Integer>(Arrays.asList(7, 6, 8)));
		stats.add(new ArrayList<Integer>(Arrays.asList(6, 10, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 1, 9)));
		stats.add(new ArrayList<Integer>(Arrays.asList(1, 3, 1)));
		stats.add(new ArrayList<Integer>(Arrays.asList(5, 6, 4)));
		stats.add(new ArrayList<Integer>(Arrays.asList(2, 3, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(10, 4, 7)));
		stats.add(new ArrayList<Integer>(Arrays.asList(9, 2, 4)));

		List<RotomCard> cards = new ArrayList<>();
		for (int i = 0; i < pokes.size(); i++) {
			List<Integer> pkmnStats = stats.get(i);
			cards.add(new RotomCard(pokes.get(i), pkmnStats.get(0), pkmnStats.get(1), pkmnStats.get(2)));
		}

		cardRepository.saveAll(cards);

		User ua = new User(1, "Juan");
		User ub = new User(2, "Nacho");
		userRepository.save(ua);
		userRepository.save(ub);
		
		Random r = new Random();
		int ini = r.nextInt(0, 151);
		int fin = r.nextInt(ini, 151);
		List<UserRotomCard> userCards = new ArrayList<>();
		for (int i = ini; i <= fin ; i++) {
			userCards.add(new UserRotomCard(ua, cards.get(i), r.nextBoolean()));
		}
		userCardsRepository.saveAll(userCards);

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

	public void createPokemon() {
		ArrayList<Pokemon> pokes = new ArrayList<Pokemon>();

		// Grass

		pokes.add(new Pokemon(465, "Tangrowth", "Giga drain", "Sludge bomb", "Leech seed", "Knock off", "Grass", "",
				252, 6, 0, 252, 0, 0, "Regenerator", "Bold", "Rocky helmet", false, true));
		pokes.add(new Pokemon(154, "Meganium", "Toxic", "Protect", "Synthesis", "Giga drain", "Grass", "", 252, 0, 6, 0,
				252, 0, "Overgrow", "Calm", "Leftovers", false, true));
		pokes.add(new Pokemon(182, "Bellosom", "Quiver dance", "Moonblast", "Giga drain", "Strength sap", "Grass", "",
				0, 0, 252, 0, 6, 252, "Chlorophyll", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(640, "Virizion", "Swords dance", "Close combat", "Substitute", "Leaf blade", "Grass",
				"Fighting", 6, 252, 0, 0, 0, 252, "Justified", "Adamant", "Lum berry", true, true));

		// Fire

		pokes.add(new Pokemon(467, "Magmortar", "Earthquake", "Fire blast", "Psychic", "Thunderbolt", "Fire", "", 252,
				0, 252, 0, 0, 6, "Flame body", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(157, "Typhlosion", "Eruption", "Fire blast", "Hidden power", "Protect", "Fire", "", 6, 0,
				252, 0, 0, 252, "Flash fire", "Modest", "Heavy-Duty boots", false, true));

		// Water
		pokes.add(new Pokemon(382, "Kyogre", "Calm mind", "Origin pulse", "Thunder", "Ice beam", "Water", "", 252, 0,
				252, 0, 6, 0, "Drizzle", "Modest", "Leftovers", true, true));

		// Electric
		pokes.add(new Pokemon(466, "Electivire", "Earthquake", "Volt switch", "Flamethrower", "Ice punch", "Electric",
				"", 6, 0, 252, 0, 0, 252, "Motor Drive", "Quirky", "Life orb", false, true));
		pokes.add(new Pokemon(405, "Luxray", "Facade", "Wild charge", "Superpower", "Volt switch", "Electric", "", 252,
				252, 0, 0, 0, 6, "Guts", "Adamant", "Flame orb", false, true));
		pokes.add(new Pokemon(596, "Galvantula", "Sticky web", "Thunder", "Volt switch", "Giga drain", "Electric",
				"Bug", 0, 0, 252, 0, 6, 252, "Compound eye", "Modest", "Focus sash", false, true));

		// Ground
		pokes.add(new Pokemon(232, "Donphan", "Stone edge", "Earthquake", "Stealth rock", "Rapid spin", "Ground", "", 6,
				252, 252, 0, 0, 0, "Sturdy", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(330, "Flygon", "Dragon dance", "Earthquake", "Fire punch", "Outrage", "Dragon", "Ground",
				0, 252, 6, 0, 0, 252, "Levitate", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(450, "Hippowdon", "Earthquake", "Stone edge", "Stealth rock", "Slack off", "Ground", "",
				252, 6, 0, 252, 0, 0, "Sandstorm", "Impish", "Leftovers", false, true));
		pokes.add(new Pokemon(383, "Groudon", "Precipice Blades", "Heat crash", "Heavy slam", "Swords dance", "Ground",
				"", 252, 252, 0, 0, 0, 6, "Drought", "Adamant", "Leftovers", true, true));

		// Rock
		pokes.add(new Pokemon(464, "Rhyperior", "Earthquake", "Stone edge", "Megahorn", "Fire punch", "Rock", "Ground",
				6, 252, 252, 0, 0, 0, "Solid rock", "Adamant", "Weakness policy", false, true));
		pokes.add(new Pokemon(377, "Regirock", "Rock slide", "Curse", "Body press", "Rest", "Rock", "", 0, 252, 0, 252,
				0, 0, "Sturdy", "Impish", "Leftovers", true, true));

		// Poison
		pokes.add(new Pokemon(569, "Garbodor", "Pain split", "Stomping tantrum", "Gunk shot", "Toxic spikes", "Poison",
				"", 252, 252, 0, 6, 0, 0, "Aftermath", "Adamant", "Black sludge", false, true));
		pokes.add(new Pokemon(804, "Naganadel", "Sludge wave", "Draco meteor", "Nasty plot", "U turn", "Dragon",
				"Poison", 0, 0, 252, 0, 6, 252, "Beast boost", "Modest", "Life orb", true, true));

		// Psychic
		pokes.add(new Pokemon(196, "Espeon", "Psychic", "Shadow ball", "Signal beam", "Calm mind", "Psychic", "", 0, 0,
				252, 0, 6, 252, "Magic Bounce", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(202, "Wobbuffet", "Charm", "Mirror coat", "Counter", "Encore", "Psychic", "", 252, 0, 0,
				252, 6, 0, "Shadow Tag", "Calm", "Leftovers", false, true));

		// Flying
		pokes.add(new Pokemon(663, "Talonflame", "Flare blitz", "Swords dance", "Brave bird", "Roost", "Fire", "Flying",
				0, 252, 0, 0, 6, 252, "Gale wings", "Jolly", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(630, "Mandibuzz", "Foul play", "Toxic", "Roost", "U turn", "Dark", "Flying", 252, 0, 0,
				252, 6, 0, "Overcoat", "Impish", "Leftovers", false, true));

		// Bug
		pokes.add(new Pokemon(794, "Buzzwole", "Close combat", "Leech life", "Dual wingbeat", "Darkest lariat", "Bug",
				"Fighting", 6, 252, 0, 252, 0, 0, "Beast boost", "Adamant", "Choice band", true, true));

		// Normal
		pokes.add(new Pokemon(463, "Lickilicky", "Body slam", "Knock off", "Swords dance", "Earthquake", "Normal", "",
				252, 252, 0, 6, 0, 0, "Own Tempo", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(242, "Blissey", "Soft boiled", "Seismic toss", "Toxic", "Protect", "Normal", "", 252, 0,
				6, 0, 252, 0, "Natural cure", "Calm", "Leftovers", false, true));
		pokes.add(new Pokemon(233, "Porygon2", "Recover", "Tri attack", "Ice beam", "Discharge", "Normal", "", 252, 0,
				0, 252, 6, 0, "Trace", "Calm", "Eviolite", false, true));
		pokes.add(new Pokemon(474, "Porygon-z", "Nasty plot", "Dark pulse", "Tri attack", "Thunderbolt", "Normal", "",
				0, 0, 252, 0, 6, 252, "Download", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(486, "Regigigas", "Body slam", "Substitute", "Toxic", "Protect", "Normal", "", 252, 252,
				0, 0, 0, 6, "Slow Start", "Adamant", "Leftovers", true, true));

		// Ghost
		pokes.add(new Pokemon(356, "Dusclops", "Toxic", "Shadow ball", "Pain split", "Protect", "Ghost", "", 252, 0, 6,
				252, 0, 0, "Frisk", "Bold", "Eviolite", false, true));
		pokes.add(new Pokemon(477, "Dusknoir", "Shadow sneak", "Poltergeist", "Pain Split", "Earthquake", "Ghost", "",
				252, 252, 0, 6, 0, 0, "Pressure", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(563, "Cofagrigus", "Shadow ball", "Body press", "Will-o wisp", "Pain split", "Ghost", "",
				252, 0, 6, 252, 0, 0, "Mummy", "Bold", "Leftovers", false, true));
		pokes.add(new Pokemon(442, "Spiritomb", "Foul play", "Poltergeist", "Trick", "Shadow sneak", "Ghost", "Dark",
				252, 252, 0, 6, 0, 0, "Infiltrator", "Adamant", "Choice band", false, true));
		pokes.add(new Pokemon(426, "Drifblim", "Strength sap", "Shadow ball", "Thunderbolt", "Calm mind", "Ghost",
				"Flying", 252, 0, 252, 0, 6, 0, "Aftermath", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(802, "Marshadow", "Close combat", "Spectral thief", "Shadow sneak", "Bulk up", "Ghost",
				"Fighting", 0, 252, 0, 6, 0, 252, "Technician", "Adamant", "Life orb", true, true));

		// Fighting
		pokes.add(new Pokemon(237, "Hitmontop", "Sucker punch", "Rapid spin", "Close combat", "Knock off", "Fighting",
				"", 252, 252, 0, 6, 0, 0, "Intimidate", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(448, "Lucario", "Meteor mash", "Close combat", "Swords dance", "Extreme speed",
				"Fighting", "Steel", 0, 252, 0, 6, 0, 252, "Inner focus", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(638, "Cobalion", "Close combat", "Swords dance", "Iron head", "Volt switch", "Fighting",
				"Steel", 0, 252, 0, 6, 0, 252, "Justified", "Adamant", "Leftovers", true, true));

		// Steel
		pokes.add(new Pokemon(462, "Magnezone", "Thunderbolt", "Volt switch", "Flash cannon", "Body press", "Electric",
				"Steel", 252, 0, 252, 0, 6, 0, "Sturdy", "Modest", "Choice specs", false, true));
		pokes.add(new Pokemon(208, "Steelix", "Earthquake", "Dragon dance", "Head smash", "Heavy slam", "Steel",
				"Ground", 6, 252, 0, 252, 0, 0, "Rock head", "Impish", "Leftovers", false, true));
		pokes.add(new Pokemon(212, "Scizor", "Knock off", "Swords dance", "Bullet punch", "Superpower", "Bug", "Steel",
				6, 252, 0, 252, 0, 0, "Technician", "Adamant", "Leftovers", false, true));
		pokes.add(new Pokemon(437, "Bronzong", "Gyro ball", "Toxic", "Protect", "Stealth rock", "Steel", "Psychic", 252,
				0, 6, 252, 0, 0, "Levitate", "Calm", "Leftovers", false, true));
		pokes.add(new Pokemon(227, "Skarmory", "Brave bird", "Roost", "Spikes", "Body press", "Steel", "Flying", 6, 252,
				0, 252, 0, 0, "Keen eye", "Impish", "Leftovers", false, true));
		pokes.add(new Pokemon(303, "Mawile", "Iron head", "Play rough", "Superpower", "Sucker punch", "Steel", "Fairy",
				0, 252, 0, 6, 0, 252, "Intimidate", "Adamant", "Choice band", false, true));

		// Ice
		pokes.add(new Pokemon(362, "Glalie", "Freeze dry", "Substitute", "Protect", "Earthquake", "Ice", "", 0, 0, 252,
				0, 6, 252, "Moody", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(473, "Mamoswine", "Earthquake", "Icicle crash", "Ice shard", "Stealth rock", "Ground",
				"Ice", 0, 252, 0, 6, 0, 252, "Thick fat", "Adamant", "Heavy-Duty boots", false, true));

		// Dragon
		pokes.add(new Pokemon(230, "Kingdra", "Hydro pump", "Rain dance", "Ice beam", "Draco meteor", "Water", "Dragon",
				0, 0, 252, 0, 6, 252, "Swift swim", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(373, "Salamence", "Dual wingbeat", "Outrage", "Earthquake", "Dragon dance", "Dragon",
				"Flying", 0, 252, 0, 6, 0, 252, "Moxie", "Adamant", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(445, "Garchomp", "Earthquake", "Outrage", "Swords dance", "Fire fang", "Dragon", "Ground",
				0, 252, 0, 6, 0, 252, "Rough skin", "Adamant", "Lum berry", false, true));
		pokes.add(new Pokemon(706, "Goodra", "Draco meteor", "Power whip", "Earthquake", "Thunderbolt", "Dragon", "",
				252, 0, 6, 0, 252, 0, "Sap sipper", "Calm", "Assault vest", false, true));
		pokes.add(new Pokemon(635, "Hydreigon", "Draco meteor", "Dark pulse", "U turn", "Flash cannon", "Dragon",
				"Dark", 0, 0, 252, 0, 6, 252, "Levitate", "Modest", "Choice specs", false, true));
		pokes.add(new Pokemon(384, "Rayquaza", "Dragon Ascent", "V-Create", "Dragon dance", "Earthquake", "Dragon",
				"Flying", 0, 252, 0, 6, 0, 252, "Air lock", "Jolly", "Heavy-Duty boots", true, true));

		// Dark
		pokes.add(new Pokemon(197, "Umbreon", "Foul play", "Protect", "Toxic", "Wish", "Dark", "", 252, 0, 0, 252, 6, 0,
				"Synchronize", "Impish", "Leftovers", false, true));
		pokes.add(new Pokemon(359, "Absol", "Knock off", "Swords dance", "Sucker punch", "Play rough", "Dark", "", 0,
				252, 0, 6, 0, 252, "Pressure", "Adamant", "Life orb", false, true));
		pokes.add(new Pokemon(571, "Zoroark", "Dark pulse", "Nasty plot", "Sludge bomb", "Flamethrower", "Dark", "", 0,
				0, 252, 0, 6, 252, "Illusion", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(461, "Weavile", "Knock off", "Icicle crash", "Swords dance", "Low kick", "Dark", "Ice", 0,
				252, 0, 0, 6, 252, "Pressure", "Jolly", "Focus sash", false, true));
		pokes.add(new Pokemon(302, "Sableye", "Will-o wisp", "Recover", "Knock off", "Shadow ball", "Dark", "Ghost", 0,
				0, 6, 252, 252, 0, "Prankster", "Modest", "Leftovers", false, true));
		pokes.add(new Pokemon(625, "Bisharp", "Knock off", "Sucker punch", "Swords dance", "Iron head", "Dark", "Steel",
				0, 252, 0, 252, 0, 6, "Defiant", "Adamant", "Focus sash", false, true));
		pokes.add(new Pokemon(717, "Yveltal", "Dark pulse", "Oblivion wing", "Roost", "Knock off", "Dark", "Flying", 0,
				0, 252, 0, 6, 252, "Dark aura", "Modest", "Leftovers", true, true));

		// Fairy
		pokes.add(new Pokemon(685, "Slurpuff", "Drain punch", "Facade", "Play rough", "Belly drum", "Fairy", "", 252,
				252, 0, 0, 0, 6, "Unburden", "Adamant", "Sitrus berry", false, true));
		pokes.add(new Pokemon(282, "Gardevoir", "Psyshock", "Calm mind", "Moonblast", "Mystical fire", "Psychic",
				"Fairy", 0, 0, 252, 0, 6, 252, "Trace", "Modest", "Life orb", false, true));
		pokes.add(new Pokemon(468, "Togekiss", "Air slash", "Dazzling gleam", "Nasty plot", "Roost", "Fairy", "Flying",
				6, 0, 252, 0, 0, 252, "Serene grace", "Modest", "Heavy-Duty boots", false, true));
		pokes.add(new Pokemon(716, "Xerneas", "Geomancy", "Moonblast", "Psyshock", "Thunderbolt", "Fairy", "", 0, 0,
				252, 0, 6, 252, "Fairy aura", "Modest", "Power herb", true, true));

		for (int i = 0; i < pokes.size(); ++i) {
			pokemonRepository.save(pokes.get(i));
		}

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

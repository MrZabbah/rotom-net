package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import es.trident.rotomnet.repository.UserRepository;
import es.trident.rotomnet.repository.UserRotomCardRepository;

@Service
public class PokemonService {
	@Autowired
	private PokemonRepository pr;
	@Autowired
	private RotomCardRepository cr;
	@Autowired
	private UserRotomCardRepository ucr;
	@Autowired
	private UserRepository ur;
	
	public List<List<RotomCard>> getAllCards() {
		
		List<RotomCard> cards = cr.findAll();
		List<List<RotomCard>> subCardSets = Lists.partition(cards, 5);
			    
		return subCardSets;
	}
	
	public List<List<UserRotomCard>> getUserCards(User user) {
		List<UserRotomCard> cards = ucr.findByUser(user);
		List<Pokemon> userDeck = new ArrayList<>();
		
		for (UserRotomCard userRotomCard : cards) {
			userDeck.add(userRotomCard.getRottomCard().getPokemon());
		}
		
		List<RotomCard> pokemonCardsNotOwned = cr.findByPokemonNotIn(userDeck);
		
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
		Pokemon a = new Pokemon(1, "Bulbasaur");
		Pokemon b = new Pokemon(2, "Combusken");
		Pokemon c = new Pokemon(3, "Magikarp");
		Pokemon d = new Pokemon(4, "Togepi");
		Pokemon e = new Pokemon(5, "Metapod");
		Pokemon f = new Pokemon(6, "Kirlia");
		Pokemon g = new Pokemon(7, "Budew");
		Pokemon h = new Pokemon(8, "Fennekin");
		Pokemon i = new Pokemon(9, "Poplio");
		RotomCard ap = new RotomCard(a, 4, 5, 7);
		RotomCard bp = new RotomCard(b, 8, 2, 1);
		RotomCard cp = new RotomCard(c, 4, 4, 2);
		RotomCard dp = new RotomCard(d, 4, 9, 7);
		RotomCard ep = new RotomCard(e, 4, 5, 5);
		RotomCard fp = new RotomCard(f, 4, 5, 5);
		RotomCard gp = new RotomCard(g, 4, 5, 5);
		RotomCard hp = new RotomCard(h, 4, 5, 5);
		RotomCard ip = new RotomCard(i, 4, 5, 5);

		cr.save(ap);
		cr.save(bp);
		cr.save(cp);
		cr.save(dp);
		cr.save(ep);
		cr.save(fp);
		cr.save(gp);
		cr.save(hp);
		cr.save(ip);
		
		User ua = new User(1, "Juan");
		User ub = new User(2, "Juan");
		ur.save(ua);
		ur.save(ub);
		
		ucr.save(new UserRotomCard(ua, cp, false));
		ucr.save(new UserRotomCard(ua, ep, true));
		ucr.save(new UserRotomCard(ua, ip, false));
		ucr.save(new UserRotomCard(ua, ap, true));
		ucr.save(new UserRotomCard(ua, fp, false));
	}
	
	
	private class OrderingByPokemon extends Ordering<UserRotomCard> {
	    @Override
	    public int compare(UserRotomCard s1, UserRotomCard s2) {
	        return Ints.compare(s1.getRottomCard().getPokemonDexIndex(), s2.getRottomCard().getPokemonDexIndex());
	    }
	}
}

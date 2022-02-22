package es.trident.rotomnet.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.trident.rotomnet.model.User;
import es.trident.rotomnet.service.PokemonService;
import es.trident.rotomnet.service.UserService;

@Controller
public class CardBoardController {

	@Autowired
	private PokemonService _pokemonService;
	@Autowired
	private UserService userService;

	
	@GetMapping("/card")
	public String card() {
		return("card");
	}
	
	@GetMapping("/pokedex")
	public String pokedex(Model model) {
		
		model.addAttribute("cards", _pokemonService.getAllCards());
		model.addAttribute("userDeck", false);
		model.addAttribute("boardTitle", "ROTOM CARD SET: FIRST GEN");
		return("pokedex");
	}
	
	@GetMapping("/deck_{username}")
	public String userDeck(Model model, @PathVariable String username) {
		
		User user = userService.findUserByUsername(username);
		model.addAttribute("cards", _pokemonService.getUserCards(user));
		model.addAttribute("ratioString", _pokemonService.getUserDiscoverRatio(user));
		model.addAttribute("userDeck", true);
		model.addAttribute("boardTitle", "Deck of " + username);
		return("pokedex");
	}
}

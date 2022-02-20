package es.trident.rotomnet.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.trident.rotomnet.repository.UserRepository;
import es.trident.rotomnet.service.PokemonService;
import es.trident.rotomnet.service.UserService;

@Controller
public class CardBoardController {

	@Autowired
	private PokemonService _pokemonService;
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void BoardController() {
		_pokemonService.create();
	}
	
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
	
	@GetMapping("/deck")
	public String userDeck(Model model) {
		
		model.addAttribute("cards", _pokemonService.getUserCards(userService.getUserByUsernameAndPwd("Test", "test")));
		model.addAttribute("ratioString", _pokemonService.getUserDiscoverRatio(userService.getUserByUsernameAndPwd("Test", "test")));
		model.addAttribute("userDeck", true);
		model.addAttribute("boardTitle", "YOUR DECK");
		return("pokedex");
	}
}

package es.trident.rotomnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.trident.rotomnet.service.PokemonService_t;

@Controller
public class CardBoardController {

	@Autowired
	private PokemonService_t _pokemonService;
	
	@GetMapping("/card")
	public String card() {
		return("card");
	}
	
	@GetMapping("/pokedex")
	public String pokedex(Model model) {
		
		model.addAttribute("cards", _pokemonService.getAllCards());
		return("pokedex");
	}
}

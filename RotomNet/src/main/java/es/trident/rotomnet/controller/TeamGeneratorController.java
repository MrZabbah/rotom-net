package es.trident.rotomnet.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.trident.rotomnet.service.PokemonService;

@Controller
public class TeamGeneratorController {
	
	private PokemonService _pokemonService;
	
	public TeamGeneratorController(PokemonService pokemonService) {
		_pokemonService = pokemonService;
		_pokemonService.createPokemon();
	}
	
	@GetMapping("/teamGenerator")
	public String teamGenerator() {
		return "teamGeneratorForm";
	}
	
	@PostMapping("/createRandomTeam")
	public String createRandomTeam(Model model, @RequestParam String teamName, @RequestParam(required=false) boolean legendaryCheck,
			 @RequestParam(required=false) boolean fireCheck, @RequestParam(required=false) boolean waterCheck, @RequestParam(required=false) boolean grassCheck, @RequestParam(required=false) boolean electricCheck,
			 @RequestParam(required=false) boolean groundCheck, @RequestParam(required=false) boolean rockCheck, @RequestParam(required=false) boolean poisonCheck, @RequestParam(required=false) boolean psychicCheck,
			 @RequestParam(required=false) boolean flyingCheck, @RequestParam(required=false) boolean bugCheck, @RequestParam(required=false) boolean normalCheck, @RequestParam(required=false) boolean ghostCheck,
			 @RequestParam(required=false) boolean fightingCheck, @RequestParam(required=false) boolean steelCheck, @RequestParam(required=false) boolean iceCheck, @RequestParam(required=false) boolean dragonCheck,
			 @RequestParam(required=false) boolean darkCheck, @RequestParam(required=false) boolean fairyCheck) {
		boolean[] types = {fireCheck,waterCheck,grassCheck,electricCheck,groundCheck,rockCheck,poisonCheck,psychicCheck,flyingCheck,bugCheck,normalCheck,ghostCheck,fightingCheck,steelCheck,iceCheck,dragonCheck,darkCheck,fairyCheck};
		boolean anyType = false;
		ArrayList<String> selectedTypes = _pokemonService.checkAnyType(types);
		if(selectedTypes.isEmpty()) {
			anyType = true;
		}
		model.addAttribute("teamName",teamName);
		model.addAttribute("legendaryCheck",legendaryCheck);
		model.addAttribute("selectedTypes",selectedTypes);
		model.addAttribute("anyType",anyType);
		model.addAttribute("team",_pokemonService.getRandomTeam(teamName,selectedTypes,legendaryCheck));
		return "teamCreated";
	}

}

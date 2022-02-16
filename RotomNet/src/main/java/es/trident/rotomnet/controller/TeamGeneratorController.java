package es.trident.rotomnet.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.service.PokemonService;
import es.trident.rotomnet.service.TeamRepository;
import es.trident.rotomnet.service.TeamService;

@Controller
public class TeamGeneratorController {
		
	private PokemonService _pokemonService;
	private TeamService _teamService;
	
	public TeamGeneratorController(PokemonService pokemonService, TeamService teamService) {
		_pokemonService = pokemonService;
		_teamService = teamService;
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
		_teamService.setCurrentTeam(_pokemonService.getRandomTeam(teamName,selectedTypes,legendaryCheck));
		model.addAttribute("legendaryCheck",legendaryCheck);
		model.addAttribute("selectedTypes",selectedTypes);
		model.addAttribute("anyType",anyType);
		model.addAttribute("team",_teamService.getCurrentTeam());
		return "teamCreated";
	}
	
	@PostMapping("/saveTeam")
	public String saveTeam(Model model) {
		_teamService.saveCurrentTeam();
		return "redirect:/";
	}
	

}

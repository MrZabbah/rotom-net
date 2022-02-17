package es.trident.rotomnet.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.trident.rotomnet.model.Team_t;
import es.trident.rotomnet.service.PokemonService_t;
import es.trident.rotomnet.service.TeamRepository_t;
import es.trident.rotomnet.service.TeamService_t;

@Controller
public class TeamGeneratorController_t {
		
	private PokemonService_t _pokemonService;
	private TeamService_t _teamService;
	
	public TeamGeneratorController_t(PokemonService_t pokemonService_t, TeamService_t teamService_t) {
		_pokemonService = pokemonService_t;
		_teamService = teamService_t;
		_pokemonService.createPokemon();
		//_teamService.createTeams();
	}
	
	@GetMapping("/exit")
	public String exit() {
		return "redirect:/";
	}
	
	@RequestMapping("/teamGenerator")
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
	
	@GetMapping("/displayTeams")
	public String teamList(Model model, Pageable page) {
		Page<Team_t> teamsReceived = _teamService.getAllTeams(page);
		model.addAttribute("teamList",teamsReceived);
		model.addAttribute("previous",teamsReceived.hasPrevious());
		model.addAttribute("next",teamsReceived.hasNext());
		model.addAttribute("nextPage",teamsReceived.getNumber()+1);
		model.addAttribute("previousPage",teamsReceived.getNumber()-1);
		return "teamList";
	}
	
	@GetMapping("/showTeam/{id}")
	public String showTeam(Model model, @PathVariable int id) {
		model.addAttribute("team",_teamService.getTeamById(id));
		return "teamDisplay";
	}
	
	@PostMapping("/deleteTeam/{id}")
	public String deleteTeam(@PathVariable int id) {
		_teamService.deleteTeam(id);
		return "redirect:/";
	}

}

/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.service.PokemonService;
import es.trident.rotomnet.service.TeamService;
import es.trident.rotomnet.service.UserService;

/**
 * TeamGeneratorController Controlador dedicado a gestionar las peticiones
 * relacionadas con la generación automática de equipos
 */

@Controller
public class TeamGeneratorController {

	private PokemonService _pokemonService;
	private TeamService _teamService;
	private UserService _userService;
	
	public TeamGeneratorController(PokemonService pokemonService, TeamService teamService, UserService userService) {
		_pokemonService = pokemonService;
		_teamService = teamService;
		_userService = userService;

	}

	@GetMapping("/exit")
	public String exit() {
		return "redirect:/";
	}

	@RequestMapping("/teamGenerator")
	public String teamGenerator() {
		return "teamGeneratorForm";
	}

	/*
	 * Recibe la información introducida en el formulario de creación de equipos aleatorios, la interpreta,
	 * llama a TeamService para obtener el equipo aleatorio y muestra la página donde se visualiza el equipo creado.
	 */
	
	@PostMapping("/createRandomTeam")
	public String createRandomTeam(Model model, @RequestParam String teamName, @RequestParam(required=false) boolean legendaryCheck,
			 @RequestParam(required=false) boolean fireCheck, @RequestParam(required=false) boolean waterCheck, @RequestParam(required=false) boolean grassCheck, @RequestParam(required=false) boolean electricCheck,
			 @RequestParam(required=false) boolean groundCheck, @RequestParam(required=false) boolean rockCheck, @RequestParam(required=false) boolean poisonCheck, @RequestParam(required=false) boolean psychicCheck,
			 @RequestParam(required=false) boolean flyingCheck, @RequestParam(required=false) boolean bugCheck, @RequestParam(required=false) boolean normalCheck, @RequestParam(required=false) boolean ghostCheck,
			 @RequestParam(required=false) boolean fightingCheck, @RequestParam(required=false) boolean steelCheck, @RequestParam(required=false) boolean iceCheck, @RequestParam(required=false) boolean dragonCheck,
			 @RequestParam(required=false) boolean darkCheck, @RequestParam(required=false) boolean fairyCheck, HttpSession session) {
		boolean[] types = {fireCheck,waterCheck,grassCheck,electricCheck,groundCheck,rockCheck,poisonCheck,psychicCheck,flyingCheck,bugCheck,normalCheck,ghostCheck,fightingCheck,steelCheck,iceCheck,dragonCheck,darkCheck,fairyCheck};
		boolean containTypes = false;
		ArrayList<String> selectedTypes = _pokemonService.getTypesFromRequest(types);
		Team currentTeam;
		
		if (selectedTypes.isEmpty()) {
			containTypes = true;
		}

		currentTeam = _teamService.getRandomTeam(teamName,selectedTypes,legendaryCheck);
		session.setAttribute("legendaryCheck",legendaryCheck);
		session.setAttribute("selectedTypes",selectedTypes);
		session.setAttribute("anyType",containTypes);
		session.setAttribute("currentTeam",currentTeam);
		model.addAttribute("legendaryCheck",legendaryCheck);
		model.addAttribute("selectedTypes",selectedTypes);
		model.addAttribute("anyType",containTypes);
		model.addAttribute("team",currentTeam);
		model.addAttribute("wrongUsername",false);

		return "teamCreated";
	}

	/*
	 * Recibe un usuario del campo de un formulario en la página que muestra el equipo creado. Si este es válido, 
	 * vincula el equipoal usuario y lo guarda.Si no, vuelve a mostrar la página anterior con el título de "Wrong Username"
	 */
	
	@SuppressWarnings("unchecked")
	@PostMapping("/saveTeam")
	public String saveTeam(Model model, @RequestParam String username, HttpSession session) {
		User selectedUser = _userService.findUserByUsername(username);
		Team currentTeam = (Team)session.getAttribute("currentTeam");
		
		if(selectedUser != null) {
			_teamService.saveCurrentTeam(selectedUser,currentTeam);
		} else {
			model.addAttribute("legendaryCheck",(boolean)session.getAttribute("legendaryCheck"));
			model.addAttribute("selectedTypes",(ArrayList<String>)session.getAttribute("selectedTypes"));
			model.addAttribute("anyType",(boolean)session.getAttribute("anyType"));
			model.addAttribute("team",currentTeam);
			model.addAttribute("wrongUsername",true);
			return "teamCreated";
		}
		
		return "redirect:/";
	}

	@GetMapping("/displayTeams_{username}")
	public String teamList(Model model, Pageable page,@PathVariable String username) {
		User selectedUser = _userService.findUserByUsername(username);
		Page<Team> teamsReceived = _teamService.getTeamsByUser(selectedUser,page);
		model.addAttribute("teamList",teamsReceived);
		model.addAttribute("previous",teamsReceived.hasPrevious());
		model.addAttribute("next",teamsReceived.hasNext());
		model.addAttribute("nextPage",teamsReceived.getNumber()+1);
		model.addAttribute("previousPage",teamsReceived.getNumber()-1);
		model.addAttribute("username",username);
		return "teamList";
	}

	@GetMapping("/showTeam_{id}_{username}")
	public String showTeam(Model model, @PathVariable int id, @PathVariable String username) {
		model.addAttribute("team", _teamService.getTeamById(id));
		model.addAttribute("username",username);
		return "teamDisplay";
	}

	@PostMapping("/deleteTeam/{id}/{user}")
	public String deleteTeam(@PathVariable int id, @PathVariable String user) {
		_teamService.deleteTeam(id);
		return "redirect:/displayTeams_"+user;
	}

}

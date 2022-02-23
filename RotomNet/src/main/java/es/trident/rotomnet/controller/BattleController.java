package es.trident.rotomnet.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.service.BattleService;
import es.trident.rotomnet.service.PokemonService;
import es.trident.rotomnet.service.UserService;

@Controller
public class BattleController {

	private PokemonService pokemonService;
	private BattleService battleService;
	private UserService userService;

	public BattleController(PokemonService pokemonService, BattleService battleService, UserService userService) {
		super();
		this.pokemonService = pokemonService;
		this.battleService = battleService;
		this.userService = userService;
	}

	@GetMapping("/battle_{username}")
	public String battleScreen(Model model, @PathVariable String username, HttpSession session) {
		ArrayList<RotomCard> userTeam = pokemonService.getRandomCardTeam();
		ArrayList<RotomCard> enemyTeam = pokemonService.getRandomCardTeam();
		User user = userService.findUserByUsername(username);

		model.addAttribute("userTeam", userTeam);
		model.addAttribute("battleFinished", false);
		model.addAttribute("enemyTeam", enemyTeam);
		session.setAttribute("userTeam", userTeam);
		session.setAttribute("enemyTeam", enemyTeam);
		session.setAttribute("user", user);
		return "battle";
	}

	@GetMapping("/battleResult")
	@SuppressWarnings("unchecked")
	public String battleResult(Model model, HttpSession session) {
		ArrayList<RotomCard> userTeam = (ArrayList<RotomCard>) session.getAttribute("userTeam");
		ArrayList<RotomCard> enemyTeam = (ArrayList<RotomCard>) session.getAttribute("enemyTeam");
		User user = (User) session.getAttribute("user");
		Pair<RotomCard, Boolean> result = battleService.generateBattleResult(userTeam, enemyTeam);
		
		model.addAttribute("card", result.getFirst().getPokemon().getName());
		model.addAttribute("enemyTeam", enemyTeam);
		model.addAttribute("userTeam", userTeam);
		model.addAttribute("shiny", result.getSecond());
		model.addAttribute("battleFinished", true);
		model.addAttribute("winCondition", result.getFirst().isValid());
		
		if (user != null && result.getFirst().isValid()) {
			pokemonService.addCardToUser(result.getFirst(), user, result.getSecond());
		}

		return "battle";
	}
}

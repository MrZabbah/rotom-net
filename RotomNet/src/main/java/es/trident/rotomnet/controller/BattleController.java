/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

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
import es.trident.rotomnet.service.CardService;
import es.trident.rotomnet.service.UserService;

/**
 * BattleController: Controlador dedicado a atender las peticiones relacionadas
 * con los combates entre dos equipos de cartas Pokemon
 */
@Controller
public class BattleController {

	private CardService cardService;
	private BattleService battleService;
	private UserService userService;

	public BattleController(CardService cardService, BattleService battleService, UserService userService) {
		this.cardService = cardService;
		this.battleService = battleService;
		this.userService = userService;
	}

	/**
	 * Método encargado de recoger petición para acudir a la pantalla de realización de combate
	 * @param model
	 * @param username
	 * @param session
	 * @return Plantilla a visualizar
	 */
	@GetMapping("/battle_{username}")
	public String battleScreen(Model model, @PathVariable String username, HttpSession session) {
		ArrayList<RotomCard> userTeam = cardService.getRandomCardTeam();
		ArrayList<RotomCard> enemyTeam = cardService.getRandomCardTeam();
		User user = userService.findUserByUsername(username);

		model.addAttribute("userTeam", userTeam);
		model.addAttribute("battleFinished", false);
		model.addAttribute("enemyTeam", enemyTeam);
		session.setAttribute("userTeam", userTeam);
		session.setAttribute("enemyTeam", enemyTeam);
		session.setAttribute("user", user);
		return "battle";
	}

	/**
	 * Método encargado de recoger la petición para acudir a la pantalla de resultados de un combate
	 * @param model
	 * @param session
	 * @return Plantilla a visualizar
	 */
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
			cardService.addCardToUser(result.getFirst(), user, result.getSecond());
		}

		return "battle";
	}
	
}

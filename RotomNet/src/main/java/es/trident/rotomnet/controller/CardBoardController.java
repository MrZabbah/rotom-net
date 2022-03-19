/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.service.CardService;
import es.trident.rotomnet.service.UserService;

/**
 * CardBoardController: Controlador dedicado a atender aquellas peticiones
 * destinadas a la visualización de cartas pokemon
 */
@Controller
public class CardBoardController {

	private CardService cardService;
	private UserService userService;

	public CardBoardController(CardService cardService, UserService userService) {
		this.cardService = cardService;
		this.userService = userService;
	}

	@GetMapping("/pokedex")
	public String pokedex(Model model, HttpSession session) {
		model.addAttribute("cards", cardService.getAllCards());
		model.addAttribute("userDeck", false);
		model.addAttribute("boardTitle", "ROTOM CARD SET: FIRST GEN");
		session.setAttribute("pageId", -1);
		return ("pokedex");
	}

	@GetMapping("/deck/{username}")
	public String userDeck(Model model, @PathVariable String username, HttpSession session) {
		User user = userService.findUserByUsername(username);

		model.addAttribute("cards", cardService.getUserCards(user));
		model.addAttribute("ratioString", cardService.getUserDiscoverRatio(user));
		model.addAttribute("userDeck", true);
		model.addAttribute("boardTitle", "Deck of " + username);
		session.setAttribute("pageId", -1);
		return ("pokedex");
	}

}

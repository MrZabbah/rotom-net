/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import es.trident.rotomnet.model.RotomCard;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.model.UserRotomCard;
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
	 * Método encargado de recoger petición para acudir a la pantalla de realización
	 * de combate
	 * 
	 * @param model
	 * @param username
	 * @param session
	 * @return Plantilla a visualizar
	 */
	@GetMapping("/battle/{username}")
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
		session.setAttribute("validBattle", true);
		//session.setAttribute("pageId", "battle");
		return "battle";
	}

	/**
	 * Método encargado de recoger la petición para acudir a la pantalla de
	 * resultados de un combate
	 * 
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
		Pair<RotomCard, Boolean> result;
		UserRotomCard userCard;

		result = battleService.generateBattleResult(userTeam, enemyTeam);
		model.addAttribute("card", result.getFirst().getPokemon().getName());
		model.addAttribute("enemyTeam", enemyTeam);
		model.addAttribute("userTeam", userTeam);
		model.addAttribute("shiny", result.getSecond());
		model.addAttribute("battleFinished", true);
		model.addAttribute("winCondition", result.getFirst().isValid());
		model.addAttribute("tweeted", false);
		session.setAttribute("userTeam", userTeam);
		session.setAttribute("enemyTeam", enemyTeam);
		//session.setAttribute("pageId", "battleResult");
		
		if (user != null && result.getFirst().isValid()) {
			userCard = cardService.addCardToUser(result.getFirst(), user, result.getSecond());
			session.setAttribute("userCard", userCard);
			model.addAttribute("tweeted", userCard.getAmount() != 1);
		}

		return "battle";
	}

	/**
	 * Método encargado de recoger la petición para publicar en @RotomNetForum un tweet
	 * sobre la nueva carta desbloqueada por el usuario. Proporciona un enlace para ir
	 * directamente al tweet
	 * @param model
	 * @param session
	 * @param request
	 * @return Plantilla a visualizar
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/battleResult/tweet")
	public String battleTweet(Model model, HttpSession session, HttpServletRequest request) {
		UserRotomCard userCard = (UserRotomCard) session.getAttribute("userCard");
		ArrayList<RotomCard> userTeam;
		ArrayList<RotomCard> enemyTeam;
		String tweetUrl;
		
		if (userCard == null) {
			return "/";
		}
		
		tweetUrl = tweetCardObtained(userCard);
		userTeam = (ArrayList<RotomCard>) session.getAttribute("userTeam");
		enemyTeam = (ArrayList<RotomCard>) session.getAttribute("enemyTeam");

		model.addAttribute("card", userCard.getRottomCard().getPokemon().getName());
		model.addAttribute("enemyTeam", enemyTeam);
		model.addAttribute("userTeam", userTeam);
		model.addAttribute("shiny", userCard.isShinyUnlocked());
		model.addAttribute("battleFinished", true);
		model.addAttribute("winCondition", true);
		model.addAttribute("tweeted", true);
		model.addAttribute("tweetUrl", tweetUrl);
		//session.setAttribute("pageId", "battleTweet");
		return "battle";
	}

	/**
	 * Método encargado de realizar la llamada a la API REST encargada de publicar
	 * los tweets.
	 * @param card Carta que contiene la información necesaria para publicar el tweet
	 * @return URL del tweet publicado
	 */
	private String tweetCardObtained(UserRotomCard card) {
		Map<String, String> parameterMap = new HashMap<String, String>();
		String url = "http://localhost:8080/" + card.getUser().getUsername() + "/card/"
				+ card.getRottomCard().getPokemon().getName() + "/tweet";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Map<String, String>> myRequest;
		ResponseEntity<String> response;
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		parameterMap.put("shiny", Boolean.toString(card.isShinyUnlocked()));
		parameterMap.put("cardAmount", Integer.toString(cardService.deckCount(card.getUser())));
		myRequest = new HttpEntity<>(parameterMap, headers);
		response = restTemplate.postForEntity(url, myRequest, String.class);
		return "https://twitter.com/RotomNetForum/status/" + response.getBody();
	}

}

/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.trident.rotomnet.service.DatabaseManagementService;

/**
 * MainController: Controller dedicado a gestionar la petición de la página
 * principal, así como de la llamada al servicio dbManagementService para poblar
 * la base de datos con información por defecto.
 */
@Controller
public class MainController {

	private DatabaseManagementService dbManagementService;

	public MainController(DatabaseManagementService dbManagementService) {
		this.dbManagementService = dbManagementService;
	}

	@PostConstruct
	public void DatabaseController() {

		if (dbManagementService.isNewDevice()) {
			dbManagementService.poblateDatabase();
		}

	}

	@GetMapping("/")
	public String mainPage(Model model, HttpSession session) {
		session.setAttribute("pageId", -1);
		return "index";

	}

}

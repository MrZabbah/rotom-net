package es.trident.rotomnet.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.trident.rotomnet.service.DatabaseManagementService;

@Controller
public class MainController {

	@Autowired
	DatabaseManagementService dbManagementService;

	@PostConstruct
	public void DatabaseController() {
		if (dbManagementService.isNewDevice()) {
			dbManagementService.poblateDatabase();
		}
	}

	@GetMapping("/")
	public String mainPage(Model model) {
		return "index";
	}

}

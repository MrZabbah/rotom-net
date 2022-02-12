package es.trident.rotomnet.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamGeneratorController {
	
	private final String [] TYPEARRAY = {"Fire","Water","Grass","Electric","Ground","Rock","Poison","Psychic","Flying","Bug","Normal","Ghost","Fighting","Steel","Ice","Dragon","Dark","Fairy"};
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
		ArrayList<String> selectedTypes = new ArrayList<String>();
		boolean anyType = false;
		int i = 0;
		int count = 0;
		while(i < types.length && count < 6) {
			if(types[i]) {
				selectedTypes.add(TYPEARRAY[i]);
				count++;
			}
			i++;
		}
		if(selectedTypes.isEmpty()) {
			anyType = true;
		}
		model.addAttribute("teamName",teamName);
		model.addAttribute("legendaryCheck",legendaryCheck);
		model.addAttribute("selectedTypes",selectedTypes);
		model.addAttribute("anyType",anyType);
		return "teamCreated";
	}

}

package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private UserService userService;
		
	public void saveCurrentTeam(User selectedUser, Team currentTeam) {
		selectedUser.getTeams().add(currentTeam);
		currentTeam.setUser(selectedUser);
		userService.saveUserWithNewTeam(selectedUser);
		teamRepository.save(currentTeam);
	}
	public Page<Team> getTeamsByUsername(Pageable page, String username) {
		return teamRepository.findByUser(page,username);
	}
	
	public Team getTeamById(int id) {
		Optional<Team> team = teamRepository.findById(id);
		return team.orElseThrow();
	}
	
	public void deleteTeam(int id) {
		Team teamToDelete = teamRepository.findById(id).orElseThrow();
		teamRepository.deleteById(id);
	}
	
	public void createTeams() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("Grass");
		for(int i = 0; i < 22; ++i) {
			Team auxTeam = pokemonService.getRandomTeam("Name",types,false);
			teamRepository.save(auxTeam);
		}
		
	}
}

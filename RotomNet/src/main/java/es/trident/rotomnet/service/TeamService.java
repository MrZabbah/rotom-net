package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.Team;

import es.trident.rotomnet.model.User;

import es.trident.rotomnet.repository.TeamRepository;


@Service
public class TeamService {
	
	@Autowired

	private TeamRepository teamRepository;
	
	@Autowired
	private PokemonService pokemonService;
	@Autowired
	private UserService userService;
		
	public void saveCurrentTeam(User selectedUser, Team currentTeam) {
		currentTeam.setUser(selectedUser);
		teamRepository.save(currentTeam);

	}
	public Page<Team> getTeamsByUser(User user,Pageable page) {
		return teamRepository.findByUser(user, page);
	}
	
	public Team getTeamById(int id) {
		Optional<Team> team = teamRepository.findById(id);
		return team.orElseThrow();
	}
	
	public void deleteTeam(int id) {
		Team teamToDelete = teamRepository.findById(id).orElseThrow();
		teamRepository.deleteById(id);
	}
	
	public Team getRandomTeam(String teamName, ArrayList<String> types, boolean legendaryCheck) {
		ArrayList<Pokemon> team, legendaries = new ArrayList<>();
		int numberOfNonLegendary = 6;

		if (legendaryCheck) {
			legendaries = pokemonService.getRandomPokemonListBy(1, true, types);
			numberOfNonLegendary--;

		}
		
		team = pokemonService.getRandomPokemonListBy(numberOfNonLegendary, false, types);
		
		if (legendaryCheck)
			team.add(legendaries.get(0));

		Team myTeam = new Team(team, teamName,null);
		return myTeam;
	}

}

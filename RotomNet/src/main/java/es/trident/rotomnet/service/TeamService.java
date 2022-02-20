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
import es.trident.rotomnet.repository.TeamRepository;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository _teamRepository;
	@Autowired
	private PokemonService pokemonService;
	
	
	private Team currentTeam;
	
	public Team getCurrentTeam() {
		return currentTeam;
	}
	public void setCurrentTeam(Team currentTeam) {
		this.currentTeam = currentTeam;
	}
	public void saveCurrentTeam() {
		_teamRepository.save(currentTeam);
	}
	public Page<Team> getAllTeams(Pageable page) {
		return _teamRepository.findAll(page);
	}
	public Team getTeamById(int id) {
		Optional<Team> team = _teamRepository.findById(id);
		return team.orElseThrow();
	}
	public void deleteTeam(int id) {
		Team teamToDelete = _teamRepository.findById(id).orElseThrow();
		_teamRepository.deleteById(id);
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

		Team myTeam = new Team(team, teamName);
		return myTeam;
	}

}

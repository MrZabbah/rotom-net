package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Team_t;

@Service
public class TeamService_t {
	
	@Autowired
	private TeamRepository_t _teamRepository;
	
	@Autowired
	private PokemonService_t pokemonService_t;
	
	private Team_t currentTeam;
	
	public Team_t getCurrentTeam() {
		return currentTeam;
	}
	public void setCurrentTeam(Team_t currentTeam) {
		this.currentTeam = currentTeam;
	}
	public void saveCurrentTeam() {
		_teamRepository.save(currentTeam);
	}
	public Page<Team_t> getAllTeams(Pageable page) {
		return _teamRepository.findAll(page);
	}
	public Team_t getTeamById(int id) {
		Optional<Team_t> team_t = _teamRepository.findById(id);
		return team_t.orElseThrow();
	}
	public void deleteTeam(int id) {
		Team_t teamToDelete = _teamRepository.findById(id).orElseThrow();
		_teamRepository.deleteById(id);
	}
	
	public void createTeams() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("Grass");
		for(int i = 0; i < 50; ++i) {
			Team_t auxTeam = pokemonService_t.getRandomTeam("Name",types,false);
			_teamRepository.save(auxTeam);
		}
		
	}
}

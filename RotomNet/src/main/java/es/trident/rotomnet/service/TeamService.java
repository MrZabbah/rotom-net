/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.Team;
import es.trident.rotomnet.model.User;
import es.trident.rotomnet.repository.TeamRepository;


/**
 * TeamService: Servicio dedicado a gestionar la tabla Team de la base de datos a través del TeamRepository y ofrecer
 * funcionalidades relacionadas con la creación de equipos a elementos externos.
 */

@Service
public class TeamService {
	
	private TeamRepository teamRepository;
	private PokemonService pokemonService;
	
	public TeamService(TeamRepository teamRepository, PokemonService pokemonService) {
		this.teamRepository = teamRepository;
		this.pokemonService = pokemonService;
	}
	
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
		teamRepository.delete(teamToDelete);
	}
	
	/**
	 * @param teamName Nombre del equipo
	 * @param types Tipos seleccionados para el equipo
	 * @param legendaryCheck Confirmación de un legendario en el equipo
	 * @return myTeam Equipo creado aleatoriamente
	 */
	
	public Team getRandomTeam(String teamName, ArrayList<String> types, boolean legendaryCheck) {
		ArrayList<Pokemon> team;
		ArrayList<Pokemon> legendaries = new ArrayList<>();
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

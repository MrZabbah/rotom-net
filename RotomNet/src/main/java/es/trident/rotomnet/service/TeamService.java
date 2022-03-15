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
	
	public String parseTeam(Team team) {
		StringBuilder teamInfo = new StringBuilder("");
		for (Pokemon p : team.getPokemon()) {
			teamInfo.append(p.getName()+" @ "+p.getItem() +"\n");
			teamInfo.append("Ability: "+p.getAbility()+"\n");
			teamInfo.append("EVs "+checkEvs(p.getHealthEVs(),p.getAttackEVs(),p.getDefenseEVs(),p.getSpAttackEVs(),p.getSpDefenseEVs(),p.getSpeedEVs())+"\n");
			teamInfo.append(p.getNature() +" Nature\n");
			teamInfo.append("-"+p.getAttack1()+"\n");
			teamInfo.append("-"+p.getAttack2()+"\n");
			teamInfo.append("-"+p.getAttack3()+"\n");
			teamInfo.append("-"+p.getAttack4()+"\n");
			teamInfo.append("\n");
		}
		return teamInfo.toString();
	}
	
	public String checkEvs(int he, int at, int def, int spA, int spD, int spe) {
		StringBuilder evs = new StringBuilder("");
		int currentCount = 0;
		
		if(he != 0) {
			evs.append(String.valueOf(he)+" HP / ");
		}
		currentCount += he;
		if(at != 0) {
			evs.append(String.valueOf(at) + " Atk / ");
		}
		currentCount += at;
		if(def != 0) {
			evs.append(String.valueOf(def) +" Def");
			currentCount += def;
			if(currentCount != 510) {
				evs.append(" / ");
			}
		}
		
		if(spA != 0) {
			evs.append(String.valueOf(spA)+" SpA");
			currentCount += spA;
			if(currentCount != 510) {
				evs.append(" / ");
			}
		}
		
		if(spD != 0) {
			evs.append(String.valueOf(spD)+" SpD");
			currentCount += spD;
			if(currentCount != 510) {
				evs.append(" / ");
			}
		}

		if(spe != 0) {
			evs.append(String.valueOf(spe)+" Spe");
		}
		return evs.toString();
	}

}

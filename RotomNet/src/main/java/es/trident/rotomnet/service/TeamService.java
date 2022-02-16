package es.trident.rotomnet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Team;

@Service
public class TeamService {
	
	@Autowired
	private TeamRepository _teamRepository;
	
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

}

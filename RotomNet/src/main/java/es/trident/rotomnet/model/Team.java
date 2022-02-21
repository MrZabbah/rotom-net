package es.trident.rotomnet.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToMany
	private List<Pokemon> teamPokemon;
	private String name;
	
	protected Team() {}
	
	public Team(List<Pokemon> teamPokemon, String name) {
		this.teamPokemon = teamPokemon;
		this.name = name;
	}
	
	public List<Pokemon> getPokemon() {
		return teamPokemon;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setPokemon(List<Pokemon> teamPokemon) {
		this.teamPokemon = teamPokemon;
	}
	public void setId(int id) {
		id = id;
	}
	public void setName(String name) {
		name = name;
	}
}


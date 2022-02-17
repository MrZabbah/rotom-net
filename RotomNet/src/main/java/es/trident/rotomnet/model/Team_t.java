package es.trident.rotomnet.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Team_t {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToMany
	private List<Pokemon_t> teamPokemon;
	private String name;
	
	protected Team_t() {}
	
	public Team_t(List<Pokemon_t> teamPokemon, String name) {
		this.teamPokemon = teamPokemon;
		this.name = name;
	}
	
	public List<Pokemon_t> getPokemon() {
		return teamPokemon;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setPokemon(List<Pokemon_t> teamPokemon) {
		this.teamPokemon = teamPokemon;
	}
	public void setId(int id) {
		id = id;
	}
	public void setName(String name) {
		name = name;
	}
}


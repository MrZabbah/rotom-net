package es.trident.rotomnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int _id;
	
	@OneToMany
	private Pokemon[] _pokemon;
	private String _name;
	
	protected Team() {}
	
	public Team(Pokemon[] pokemon, String name) {
		_pokemon = pokemon;
		_name = name;
	}
	
	public Pokemon[] getPokemon() {
		return _pokemon;
	}
	public int getId() {
		return _id;
	}
	public String getName() {
		return _name;
	}
	public void setPokemon(Pokemon[] pokemon) {
		_pokemon = pokemon;
	}
	public void setId(int id) {
		_id = id;
	}
	public void setName(String name) {
		_name = name;
	}
}


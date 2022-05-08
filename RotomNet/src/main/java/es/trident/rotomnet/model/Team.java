/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/** 
 * Team: Clase que representa un equipo de seis Pokemon. 
 */
@Entity
public class Team implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	private List<Pokemon> teamPokemon;	
	private String name;
	@ManyToOne
	private User user;
	
	protected Team() {}
	
	public Team(List<Pokemon> teamPokemon, String name, User user) {
		this.teamPokemon = teamPokemon;
		this.name = name;
		this.user = user;
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
	
	public User getUser() {
		return user;
	}
	
	public void setPokemon(List<Pokemon> teamPokemon) {
		this.teamPokemon = teamPokemon;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}


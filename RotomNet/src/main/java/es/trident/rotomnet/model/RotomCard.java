/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.model;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * RotomCard: Entidad encargada de establecer la tabla relacionada con
 * cartas pokemon
 */
@Entity
public class RotomCard implements Comparator<RotomCard>, Comparable<RotomCard>{
	
	public static RotomCard NOT_FOUND = new RotomCard(Pokemon.NOT_FOUND, 0, 0, 0);
	
	@Id
    @Column(name = "CARD_ID")
    private int pokemonId;
    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn(name="CARD_ID", referencedColumnName="DEX_ID")
	private Pokemon pokemon;
	private int health;
	private int attack;
	private int defense;
	@OneToMany(mappedBy="rotomCard" ,cascade = CascadeType.ALL)
	private List<UserRotomCard> deck;
	
	protected RotomCard() {
		
	}
	
	public RotomCard(Pokemon pokemon, int health, int attack, int defense) {
		this.pokemon = pokemon;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
	}
	
	public Pokemon getPokemon() { return pokemon; }
	public void setPokemon(Pokemon pokemon) { this.pokemon = pokemon; }
	
	public int getHealth() { return health; }
	public void setHealth(int health) { this.health = health; }
	
	public int getAttack() { return attack; }
	public void setAttack(int attack) { this.attack = attack; }
	
	public int getDefense() { return defense; }
	public void setDefense(int defense) { this.defense = defense; }

	public int getPokemonDexIndex() {
		return pokemon.getPokedexNumber();
	}
	
	public boolean isValid() {
		return pokemon.getPokedexNumber() > 0;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(attack, defense, health, pokemon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RotomCard other = (RotomCard) obj;
		return Objects.equals(pokemon, other.pokemon);
	}

	@Override
	public int compareTo(RotomCard o) {
		
		return pokemon.compareTo(o.pokemon);
	}

	@Override
	public int compare(RotomCard o1, RotomCard o2) {
		return pokemon.compare(o1.pokemon, o2.pokemon);
	}
	
}
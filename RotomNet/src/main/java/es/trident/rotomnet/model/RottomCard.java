package es.trident.rotomnet.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class RottomCard {
	@Id
    @Column(name = "POKEMON_ID")
    private int pokemonId;

    @OneToOne
    @MapsId
    @PrimaryKeyJoinColumn(name="POKEMON_ID", referencedColumnName="DEX_ID")
	private Pokemon pokemon;
	private int health;
	private int attack;
	private int defense;
	
	protected RottomCard() {}
	
	public RottomCard(Pokemon pokemon, int health, int attack, int defense) {
		
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
		RottomCard other = (RottomCard) obj;
		return Objects.equals(pokemon, other.pokemon);
	}
	
	
}
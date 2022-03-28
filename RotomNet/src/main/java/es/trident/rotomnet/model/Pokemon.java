/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Pokemon: Clase dedicada a representar un Pokemon, usadacomo
 * componente básico para las Cartas y los Equipos.
 */

@Entity
public class Pokemon{
	public static Pokemon NOT_FOUND = new Pokemon(-1);
	
	@Id
	@Column(name = "DEX_ID")
	private int pokedexNumber;
	private String name;
	private String attack1;
	private String attack2;
	private String attack3;
	private String attack4;
	private String type1;
	private String type2;
	private int healthEVs;
	private int attackEVs;
	private int spAttackEVs;
	private int defenseEVs;
	private int spDefenseEVs;
	private int speedEVs;
	private String ability;
	private String nature;
	private String item;
	private boolean legendary;
	private boolean readyToBattle;
	
	protected Pokemon() {
		
	}
	
	public Pokemon(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}
	
	public Pokemon(int pokedexNumber, String name, String attack1, String attack2, String attack3, String attack4, String type1, String type2, int healthEVs,
			int attackEVs, int spAttackEVs, int defenseEVs, int spDefenseEVs, int speedEVs, String ability, String nature, String item, boolean legendary,boolean readyToBattle) {
		
		this.pokedexNumber = pokedexNumber;
		this.name = name;
		this.attack1 = attack1;
		this.attack2 = attack2;
		this.attack3 = attack3;
		this.attack4 = attack4;
		this.type1 = type1;
		this.type2 = type2;
		this.healthEVs = healthEVs;
		this.attackEVs = attackEVs;
		this.spAttackEVs = spAttackEVs;
		this.defenseEVs = defenseEVs;
		this.spDefenseEVs = spDefenseEVs;
		this.speedEVs = speedEVs;
		this.ability = ability;
		this.nature = nature;
		this.item = item;
		this.legendary = legendary;
		this.readyToBattle = readyToBattle;
	}	
	
	public int getPokedexNumber() {
		return pokedexNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAttack1() {
		return attack1;
	}
	
	public String getAttack2() {
		return attack2;
	}
	
	public String getAttack3() {
		return attack3;
	}
	
	public String getAttack4() {
		return attack4;
	}
	
	public String getType1() {
		return type1;
	}
	
	public String getType2() {
		return type2;
	}
	
	public int getHealthEVs() {
		return healthEVs;
	}
	
	public int getAttackEVs() {
		return attackEVs;
	}
	
	public int getSpAttackEVs() {
		return spAttackEVs;
	}
	
	public int getDefenseEVs() {
		return defenseEVs;
	}
	
	public int getSpDefenseEVs() {
		return spDefenseEVs;
	}
	
	public int getSpeedEVs() {
		return speedEVs;
	}
	
	public String getAbility() {
		return ability;
	}
	
	public String getNature() {
		return nature;
	}
	
	public String getItem() {
		return item;
	}
	
	public boolean getLegendary() {
		return legendary;
	}
	
	public boolean getReadyToBattle() {
		return readyToBattle;
	}
	
	public void setPokedexNumber(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAttack1(String attack1) {
		this.attack1 = attack1;
	}
	
	public void setAttack2(String attack2) {
		this.attack2 = attack2;
	}
	
	public void setAttack3(String attack3) {
		this.attack3 = attack3;
	}
	
	public void setAttack4(String attack4) {
		this.attack4 = attack4;
	}
	
	public void setType1(String type1) {
		this.type1 = type1;
	}
	
	public void setType2(String type2) {
		this.type2 = type2;
	}
	
	public void setHealthEVs(int healthEVs) {
		this.healthEVs = healthEVs;
	}
	
	public void setAttackEVs(int attackEVs) {
		this.attackEVs = attackEVs;
	}
	
	public void setSpAttackEVs(int spAttackEVs) {
		this.spAttackEVs = spAttackEVs;
	}
	
	public void setDefenseEVs(int defenseEVs) {
		this.defenseEVs = defenseEVs;
	}
	
	public void setSpDefenseEVs(int spDefenseEVs) {
		this.spDefenseEVs = spDefenseEVs;
	}
	
	public void setSpeedEVs(int speedEVs) {
		this.speedEVs = speedEVs;
	}
	
	public void setAbility(String ability) {
		this.ability = ability;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public void setLegendary(boolean legendary) {
		this.legendary = legendary;
	}
	
	public void setReadyToBattle(boolean readyToBattle) {
		this.readyToBattle = readyToBattle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pokedexNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Pokemon other = (Pokemon) obj;
		return pokedexNumber == other.pokedexNumber;
	}
	
}

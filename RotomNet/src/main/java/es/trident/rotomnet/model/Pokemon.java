package es.trident.rotomnet.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pokemon {
	
	@Id
	private int _pokedexNumber;
	
	private String _name;
	private String _attack1;
	private String _attack2;
	private String _attack3;
	private String _attack4;
	private String _type1;
	private String _type2;
	private int _healthEVs;
	private int _attackEVs;
	private int _spAttackEVs;
	private int _defenseEVs;
	private int _spDefenseEVs;
	private int _speedEVs;
	private String _ability;
	private String _nature;
	private String _item;
	private boolean _legendary;
	private boolean _readyToBattle;
	
	protected Pokemon() {
		
	}
	
	public Pokemon(int pokedexNumber, String name, String attack1, String attack2, String attack3, String attack4, String type1, String type2, int healthEVs,
			int attackEVs, int spAttackEVs, int defenseEVs, int spDefenseEVs, int speedEVs, String ability, String nature, String item, boolean legendary,boolean readyToBattle) {
		
		_pokedexNumber = pokedexNumber;
		_name = name;
		_attack1 = attack1;
		_attack2 = attack2;
		_attack3 = attack3;
		_attack4 = attack4;
		_type1 = type1;
		_type2 = type2;
		_healthEVs = healthEVs;
		_attackEVs = attackEVs;
		_spAttackEVs = spAttackEVs;
		_defenseEVs = defenseEVs;
		_spDefenseEVs = spDefenseEVs;
		_speedEVs = speedEVs;
		_ability = ability;
		_nature = nature;
		_item = item;
		_legendary = legendary;
		_readyToBattle = readyToBattle;
	}
	
	public int getPokedexNumber() {
		return _pokedexNumber;
	}
	public String getName() {
		return _name;
	}
	public String getAttack1() {
		return _attack1;
	}
	public String getAttack2() {
		return _attack2;
	}
	public String getAttack3() {
		return _attack3;
	}
	public String getAttack4() {
		return _attack4;
	}
	public String getType1() {
		return _type1;
	}
	public String getType2() {
		return _type2;
	}
	public int getHealthEVs() {
		return _healthEVs;
	}
	public int getAttackEVs() {
		return _attackEVs;
	}
	public int getSpAttackEVs() {
		return _spAttackEVs;
	}
	public int getDefenseEVs() {
		return _defenseEVs;
	}
	public int getSpDefenseEVs() {
		return _spDefenseEVs;
	}
	public int getSpeedEVs() {
		return _speedEVs;
	}
	public String getAbility() {
		return _ability;
	}
	public String getNature() {
		return _nature;
	}
	public String getItem() {
		return _item;
	}
	public boolean getLegendary() {
		return _legendary;
	}
	public boolean getReadyToBattle() {
		return _readyToBattle;
	}
	
	public void setPokedexNumber(int pokedexNumber) {
		_pokedexNumber = pokedexNumber;
	}
	public void setName(String name) {
		_name = name;
	}
	public void setAttack1(String attack1) {
		_attack1 = attack1;
	}
	public void setAttack2(String attack2) {
		_attack2 = attack2;
	}
	public void setAttack3(String attack3) {
		_attack3 = attack3;
	}
	public void setAttack4(String attack4) {
		_attack4 = attack4;
	}
	public void setType1(String type1) {
		_type1 = type1;
	}
	public void setType2(String type2) {
		_type2 = type2;
	}
	public void setHealthEVs(int healthEVs) {
		_healthEVs = healthEVs;
	}
	public void setAttackEVs(int attackEVs) {
		_attackEVs = attackEVs;
	}
	public void setSpAttackEVs(int spAttackEVs) {
		_spAttackEVs = spAttackEVs;
	}
	public void setDefenseEVs(int defenseEVs) {
		_defenseEVs = defenseEVs;
	}
	public void setSpDefenseEVs(int spDefenseEVs) {
		_spDefenseEVs = spDefenseEVs;
	}
	public void setSpeedEVs(int speedEVs) {
		_speedEVs = speedEVs;
	}
	public void setAbility(String ability) {
		_ability = ability;
	}
	public void setNature(String nature) {
		_nature = nature;
	}
	public void setItem(String item) {
		_item = item;
	}
	public void setLegendary(boolean legendary) {
		_legendary = legendary;
	}
	public void setReadyToBattle(boolean readyToBattle) {
		_readyToBattle = readyToBattle;
	}
}

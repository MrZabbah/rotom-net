package es.trident.rotomnet.model;

public class Pokemon {

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
	
	public Pokemon(int pokedexNumber, String name, String attack1, String attack2, String attack3, String attack4, String type1, String type2, int healthEVs,
			int attackEVs, int spAttackEVs, int defenseEVs, int spDefenseEVs, int speedEVs, String ability, String nature, String item, boolean legendary) {
		
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
	
	public void getPokedexNumber(int pokedexNumber) {
		_pokedexNumber = pokedexNumber;
	}
	public void getName(String name) {
		_name = name;
	}
	public void getAttack1(String attack1) {
		_attack1 = attack1;
	}
	public void getAttack2(String attack2) {
		_attack2 = attack2;
	}
	public void getAttack3(String attack3) {
		_attack3 = attack3;
	}
	public void getAttack4(String attack4) {
		_attack4 = attack4;
	}
	public void getType1(String type1) {
		_type1 = type1;
	}
	public void getType2(String type2) {
		_type2 = type2;
	}
	public void getHealthEVs(int healthEVs) {
		_healthEVs = healthEVs;
	}
	public void getAttackEVs(int attackEVs) {
		_attackEVs = attackEVs;
	}
	public void getSpAttackEVs(int spAttackEVs) {
		_spAttackEVs = spAttackEVs;
	}
	public void getDefenseEVs(int defenseEVs) {
		_defenseEVs = defenseEVs;
	}
	public void getSpDefenseEVs(int spDefenseEVs) {
		_spDefenseEVs = spDefenseEVs;
	}
	public void getSpeedEVs(int speedEVs) {
		_speedEVs = speedEVs;
	}
	public void getAbility(String ability) {
		_ability = ability;
	}
	public void getNature(String nature) {
		_nature = nature;
	}
	public void getItem(String item) {
		_item = item;
	}
	public void getLegendary(boolean legendary) {
		_legendary = legendary;
	}
}

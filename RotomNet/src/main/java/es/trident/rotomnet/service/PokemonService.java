package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.PokemonCard;
import es.trident.rotomnet.model.Team;



@Service
public class PokemonService {
	
	@Autowired
	private PokemonRepository _pokemonRepository;
	
	private final String [] POKEMON_TYPES = {"Fire","Water","Grass","Electric","Ground","Rock","Poison","Psychic","Flying","Bug","Normal","Ghost","Fighting","Steel","Ice","Dragon","Dark","Fairy"};
	
	public List<List<PokemonCard>> getAllCards() {
		return List.of(
				List.of(				
						new PokemonCard(0, false, false),
						new PokemonCard(1, false, false),
						new PokemonCard(2, false, false),
						new PokemonCard(3, true, true),
						new PokemonCard(4, false, false)
						),
				List.of(				
						new PokemonCard(5, false, false),
						new PokemonCard(6, true, true),
						new PokemonCard(7, false, false),
						new PokemonCard(8, true, false),
						new PokemonCard(9, false, false)
						),
				List.of(				
						new PokemonCard(10, false, false),
						new PokemonCard(11, false, false),
						new PokemonCard(12, false, false),
						new PokemonCard(13, true, false),
						new PokemonCard(14, false, false)
						),
				List.of(				
						new PokemonCard(15, false, false),
						new PokemonCard(16, true, false),
						new PokemonCard(17, false, false),
						new PokemonCard(18, true, false),
						new PokemonCard(19, true, true)
						),
				List.of(				
						new PokemonCard(20, true, true),
						new PokemonCard(21, true, true),
						new PokemonCard(22, false, false),
						new PokemonCard(23, true, false),
						new PokemonCard(24, false, false)
						)
				);
	}
	
	public ArrayList<String> checkAnyType(boolean[] types) {
		ArrayList<String> selectedTypes = new ArrayList<String>();
		int i = 0;
		int count = 0;
		while(i< types.length && count <6) {
			if(types[i]) {
				selectedTypes.add(POKEMON_TYPES[i]);
				count++;
			}
			i++;
		}
		return selectedTypes;
	}
	
	public void createPokemon() {
		Pokemon salamence = new Pokemon(1,"Salamence","Fly","Outrage","Earthquake","Dragon dance","Dragon","Flying",0,252,0,3,0,252,"Moxie","Adamant","Heavy Duty boots",false, true);
		Pokemon blastoise = new Pokemon(2,"Blastoise","Hydro-pump","Rapid-spin","Scald","Ice beam","water","",0,252,0,3,0,252,"Torrent","Modest","Leftovers",false, true);
		_pokemonRepository.save(salamence);
		_pokemonRepository.save(blastoise);
	}
	
	private void getNonLegendaries(int number, Pokemon[] team){
		ArrayList<Pokemon> nonLegendaries = (ArrayList<Pokemon>)_pokemonRepository.findBy_legendaryAnd_readyToBattle(false,true);
		for(int i = 0; i < number;++i) {
			int index = (int)Math.random()%nonLegendaries.size();
			team[i] = nonLegendaries.get(index);
		}
	} 
	
	private void fillListByTypes(ArrayList<String>types, ArrayList<Pokemon> myList, boolean legendaries) {
		for(int i = 0; i < types.size();++i) {
			myList.addAll((ArrayList<Pokemon>)_pokemonRepository.findByTypesAndLegendary(types.get(i), types.get(i),legendaries));
		}
	}
	
	private void getNonLegendariesByType(ArrayList<String>types,int number, Pokemon[] team) {
		ArrayList<Pokemon> nonLegendaries = new ArrayList<Pokemon>();
		fillListByTypes(types,nonLegendaries,false);
		for(int i = 0; i < number;++i) {
			int index = (int)Math.random()%nonLegendaries.size();
			team[i] = nonLegendaries.get(index);
		}
	}
	
	public Team getRandomTeam(String teamName, ArrayList<String> types, boolean legendaryCheck) {
		Pokemon[] team = new Pokemon[6];
		if(types.isEmpty()) {
			if(legendaryCheck) {
				ArrayList<Pokemon> legendaries = (ArrayList<Pokemon>)_pokemonRepository.findBy_legendaryAnd_readyToBattle(legendaryCheck, true);
				int index = (int)Math.random()%legendaries.size();
				team[5] = legendaries.get(index);
				getNonLegendaries(5,team);
			} else {
				getNonLegendaries(6,team);
			}
		} else {
			if(legendaryCheck) {
				ArrayList<Pokemon> legendaries = new ArrayList<Pokemon>();
				fillListByTypes(types,legendaries,legendaryCheck);
				int index =  (int)Math.random()%legendaries.size();
				team[5] = legendaries.get(index);
				getNonLegendariesByType(types,5,team);
			} else {
				getNonLegendariesByType(types,6,team);				
			}
		}
		Team myTeam = new Team(team,teamName);
		return myTeam;
	}
}

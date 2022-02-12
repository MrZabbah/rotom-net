package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.PokemonCard;

@Service
public class PokemonService {
	
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
	
	public Pokemon[] getRandomTeam(ArrayList<String> types, boolean legendaryCheck) {
		Pokemon[] team = new Pokemon[6];
		Pokemon salamence = new Pokemon(1,"Salamence","Fly","Outrage","Earthquake","Dragon dance","Dragon","Flying",0,252,0,3,0,252,"Moxie","Adamant","Heavy Duty boots",false);
		Pokemon blastoise = new Pokemon(2,"Blastoise","Hydro-pump","Rapid-spin","Scald","Ice beam","water","",0,252,0,3,0,252,"Torrent","Modest","Leftovers",false);
		for(int i = 0; i < 6; ++i) {
			if(i<3) {
				team[i] = salamence;
			} else {
				team[i] = blastoise;
			}
		}
		return team;
	}
}

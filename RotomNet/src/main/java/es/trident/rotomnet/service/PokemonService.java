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
		ArrayList<Pokemon> pokes = new ArrayList<Pokemon>();
		//Grass
		pokes.add(new Pokemon(3, "Venusaur", "Giga drain", "Sludge bomb", "Leech Seed", "Substitute", "Grass","Poison",252,0,252,0,6,0,"Chlorophyll", "Modest", "Black sludge", false,true));
		
		//Fire
		pokes.add(new Pokemon(6,"Charizard","Fire blast","Air slash","Earthquake","Roost","Fire","Flying",0,6,252,0,0,252,"Solar Power","Modest","Heavy-Duty boots",false,true ));
		
		//Water
		pokes.add(new Pokemon(9,"Blastoise","Protect","Rapid Spin","Ice beam","Scald","Water","",252,0,252,0,6,0,"Torrent","Modest","Leftovers",false,true ));
		pokes.add(new Pokemon(131,"Lapras","Freeze dry","Ice beam","Sparkling aria","Toxic","Water","Ice",252,0,252,0,6,0,"Water absorb","Modest","Leftovers",false,true));
		pokes.add(new Pokemon(91,"Cloyster","Hydro pump","Icicle spear","Shell smash","Rock blast","Water","Ice",6,252,0,252,0,0,"Skill link","Adamant","White herb",false,true));
		pokes.add(new Pokemon(130,"Gyarados","Bounce","Waterfall","Dragon dance","Power whip","Water","Flying",6,252,0,0,0,252,"Moxie","Adamant","Leftovers",false,true));
		pokes.add(new Pokemon(119,"Seaking","Scald","Megahorn","Drill run","Knock off","Water","",252,252,6,0,0,0,"Lightning rod","Naive","Life orb",false,true));
		pokes.add(new Pokemon(141,"Kabutops","Liquidation","Aqua jet","Swords dance","Stone edge","Water","Rock",0,252,0,6,0,252,"Swift swim","Adamant","Life orb",false,true));
		
		//Electric
		pokes.add(new Pokemon(26,"Raichu","Nasty plot","Thunderbolt","Volt switch","Surf","Electric","",0,0,252,0,6,252,"Modest","Lightning rod","Life orb",false,true));
		pokes.add(new Pokemon(135,"Jolteon","Volt switch","Tunderbolt","Hyper voice","Shadow ball","Electric","",0,0,252,0,6,252,"Volt absorb","Timid","Choice specs",false,true));
		pokes.add(new Pokemon(466,"Electivire","Earthquake","Volt switch","Flamethrower","Ice punch","Electric","",6,0,252,0,0,252,"Motor Drive","Quirky","Life orb",false,true));
		pokes.add(new Pokemon(145,"Zapdos","Discharge","Roost","Hurricane","Heat wave","Electric","Flying",0,0,252,6,0,252,"Static","Modest","Leftovers",true,true));
		
		//Ground
		
		//Rock
		
		//Poison
		
		//Psychic
		pokes.add(new Pokemon(150,"Mewtwo","Psystrike","Nasty plot","Fire blast","Recover","Psychic","",0,0,255,0,6,255,"Pressure","Modest","Leftovers",true,true));
		pokes.add(new Pokemon(151,"Mew","Psychic fangs", "Flare blitz", "Dragon dance", "Close combat","Psychic","",6,255,0,0,0,255,"Synchronize","Adamant","Leftovers",true,true));
		
		//Flying
		
		//Bug
		
		//Normal
		
		//Ghost
		
		//Fighting
		
		//Steel
		
		//Ice
		
		//Dragon
		
		//Dark
		
		//Fairy
		
		
		for(int i= 0;i< pokes.size();++i) {
			_pokemonRepository.save(pokes.get(i));
		}
		
	}
	
	private ArrayList<Pokemon> getNonLegendaries(int number){
		ArrayList<Pokemon> auxList = new ArrayList<Pokemon>();
		ArrayList<Pokemon> nonLegendaries = (ArrayList<Pokemon>)_pokemonRepository.findByLegendaryAndReadyToBattle(false,true);
		ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
		int index = -1;
		for(int i = 0; i < number;++i) {
			do {
				index = ((int)(Math.random()*10))%nonLegendaries.size();
			}while(selectedIndexes.contains(index));
			selectedIndexes.add(index);
			auxList.add(nonLegendaries.get(index));
		}
		return auxList;
	} 
	
	private ArrayList<Pokemon> fillListByTypes(ArrayList<String>types, boolean legendaries) {
		 ArrayList<Pokemon> myList = new ArrayList<Pokemon>();
		for(int i = 0; i < types.size();++i) {
			myList.addAll((ArrayList<Pokemon>)_pokemonRepository.findByTypesAndLegendary(types.get(i), types.get(i),legendaries));
		}
		return myList;
	}
	
	private ArrayList<Pokemon> getNonLegendariesByType(ArrayList<String>types,int number) {
		ArrayList<Pokemon> auxList = new ArrayList<Pokemon>();
		ArrayList<Pokemon> nonLegendaries = fillListByTypes(types,false);
		ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
		int index = -1;
		for(int i = 0; i < number;++i) {
			do {
				index = ((int)(Math.random()*10))%nonLegendaries.size();
			}while(selectedIndexes.contains(index));
			selectedIndexes.add(index);
			auxList.add(nonLegendaries.get(index));
		}
		return auxList;
	}
	
	public Team getRandomTeam(String teamName, ArrayList<String> types, boolean legendaryCheck) {
		ArrayList<Pokemon> team;
		int index = -1;
		if(types.isEmpty()) {
			if(legendaryCheck) {
				ArrayList<Pokemon> legendaries = (ArrayList<Pokemon>)_pokemonRepository.findByLegendaryAndReadyToBattle(legendaryCheck, true);
				ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
				do {
					index = ((int)(Math.random()*10))%(legendaries.size());
				}while(selectedIndexes.contains(index));
				selectedIndexes.add(index);
				team= getNonLegendaries(5);
				team.add(legendaries.get(index));
			} else {
				team = getNonLegendaries(6);
			}
		} else {
			if(legendaryCheck) {
				ArrayList<Pokemon> legendaries = fillListByTypes(types,legendaryCheck);
				ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
				do {
					index =  (int)(Math.random()*10)%legendaries.size();
				}while(selectedIndexes.contains(index));
				selectedIndexes.add(index);
				team = getNonLegendariesByType(types,5);
				team.add(legendaries.get(index));
			} else {
				team = getNonLegendariesByType(types,6);	
			}
		}
		Team myTeam = new Team(team,teamName);
		return myTeam;
	}
}

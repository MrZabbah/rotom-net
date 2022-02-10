package es.trident.rotomnet.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.PokemonCard;

@Service
public class PokemonService {
	
	public List<List<PokemonCard>> getAllCards() {
		return List.of(
				List.of(				
						new PokemonCard(0, false, false),
						new PokemonCard(1, false, false),
						new PokemonCard(2, false, false)
						),
				List.of(				
						new PokemonCard(3, true, false),
						new PokemonCard(4, false, false),
						new PokemonCard(5, false, false)
						),
				List.of(				
						new PokemonCard(6, false, false),
						new PokemonCard(7, false, false),
						new PokemonCard(8, false, false)
						),
				List.of(				
						new PokemonCard(9, true, true),
						new PokemonCard(10, false, false),
						new PokemonCard(11, false, false)
						),
				List.of(				
						new PokemonCard(12, true, true),
						new PokemonCard(13, false, false),
						new PokemonCard(14, false, false)
						),
				List.of(				
						new PokemonCard(15, false, false),
						new PokemonCard(16, true, false),
						new PokemonCard(17, false, false)
						)
				);
	}
}

package es.trident.rotomnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.Pokemon;
import es.trident.rotomnet.model.PokemonCard;
import es.trident.rotomnet.model.RottomCard;
import es.trident.rotomnet.repository.PokemonRepository;
import es.trident.rotomnet.repository.RottomCardRepository;

@Service
public class PokemonService {
	
	@Autowired
	private PokemonRepository r;
	@Autowired
	private RottomCardRepository c;

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
	
	public void create() {
		Pokemon b = new Pokemon(1, "Bulbasaur");
		r.save(new Pokemon(4, "Chicorita"));
		c.save(new RottomCard(b, 4, 5, 7));
	}
}

package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.RotomCard;

@Service
public class BattleService {

	public Pair<RotomCard, Boolean> generateBattleResult(List<RotomCard> userTeam, List<RotomCard> enemyTeam) {
		Random r = new Random();
		RotomCard result = RotomCard.NOT_FOUND;
		int winningMatches = 0;
		
		for (int i = 0; i < 6; i++) {
			int userDice = r.nextInt(9) + 1;
			int enemyDice = r.nextInt(9) + 1;
			
			if (userDice >= enemyDice) {
				winningMatches += 1;
			}
		}
		
		if (winningMatches >= 3) {
			int index;
			ArrayList<RotomCard> pokemon = new ArrayList<>(userTeam);
			
			pokemon.addAll(enemyTeam);
			index = r.nextInt(pokemon.size());
			result = pokemon.get(index);
		}

		return Pair.of(result, r.nextFloat() <= 0.08f);
	}
	
	
}

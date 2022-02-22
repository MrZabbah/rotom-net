package es.trident.rotomnet.service;

import java.util.List;
import java.util.Random;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import es.trident.rotomnet.model.RotomCard;

@Service
public class BattleService {

	public Pair<RotomCard, Boolean> generateBattleResult(List<RotomCard> userTeam, List<RotomCard> enemyTeam) {
		Random r = new Random();
		RotomCard result = null;
		int[] winningMatches = new int[2];
		
		for (int i = 0; i < 6; i++) {
			winningMatches[0] += r.nextInt(9) + 1;
			winningMatches[1] += r.nextInt(9) + 1;
		}
		
		if (winningMatches[0] > winningMatches[1]) {
			int index;
			userTeam.addAll(enemyTeam);
			index = r.nextInt(userTeam.size());
			result = userTeam.get(index);
		}

		return Pair.of(result, r.nextFloat() <= 0.1f);
	}
	
	
}

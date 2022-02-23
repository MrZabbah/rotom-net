/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import es.trident.rotomnet.model.RotomCard;

/**
 * PokemonService: Servicio dedicado a la gestión del combate.
 */
@Service
public class BattleService {

	/**
	 * Dados dos equipos formados por RotomCard, calcula seis tiradas de dado para el enemigo y el usuario
	 * en un rango del 1 al 10. En caso de que el usuario gane 3 o más batallas (si su tirada es mayor), devuelve 
	 * una carta aleatoria de aquellas sobre las que se calculó el combate, junto con una probabilidad de 
	 * obtenerla shiny (especial) de un 8%.
	 * @param userTeam Lista de RotomCards del usuario.
	 * @param enemyTeam Lista de RotomCards del enemigo
	 * @return Pair de RotomCard obtenida (carta nula si pierde) y un booleano que determina si es o no shiny.
	 */
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

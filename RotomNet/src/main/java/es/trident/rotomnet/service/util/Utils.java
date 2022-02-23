/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.service.util;

import java.util.ArrayList;
import java.util.List;

/*
 * Util Clase dedicada a implementar métodos generales que puedan ser usados desde diferentes puntos. 
 */

public class Utils {
	
	public static <T> ArrayList<T> getRandomList(int number, List<T> fullList) {
		ArrayList<Integer> selectedIndex = new ArrayList<Integer>();
		ArrayList<T> selected = new ArrayList<T>();
		int index;
		
		for (int i = 0; i < number; ++i) {

			do {
				index = ((int) (Math.random() * 100)) % fullList.size();
			} while (selectedIndex.contains(index));

			selectedIndex.add(index);
			selected.add(fullList.get(index));
		}

		return selected;
	}
	
}

/**
 * UserRotomCardRepository: Repositorio encargado de las consultas de la tabla
 * de UserRotomCard a la base de datos
 */

package es.trident.rotomnet.service.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils: Clase dedicada al almacenaje de métodos estáticos
 * recurrentes en diferentes servicios
 */
public class Utils {
	
	/**
	 * Dada una cantidad de elementos y una lista cualquiera, devuelve un ArrayList
	 * de tamaño n con elementos randomizados
	 * @param <T> Tipo genérico de elementos
	 * @param number Longitud del Array deseado
	 * @param fullList Lista de la que se desea extraer la sublista
	 * @return arrayList randomizado de N elementos
	 */
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

/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.model.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * UserRotomCardId: Clase destinada a representar la clave primaria compuesta de la 
 * entidad UserRotomCard
 */
@SuppressWarnings("serial")
public class UserRotomCardId implements Serializable{

	private long user;
	private int rotomCard;
	
	protected UserRotomCardId() {
		
	}
	
	public UserRotomCardId(int user, int rotomCard) {
		this.user = user;
		this.rotomCard = rotomCard;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rotomCard, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRotomCardId other = (UserRotomCardId) obj;
		return Objects.equals(rotomCard, other.rotomCard) && Objects.equals(user, other.user);
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public int getRottomCard() {
		return rotomCard;
	}

	public void setRottomCard(int rotomCard) {
		this.rotomCard = rotomCard;
	}
	
}

/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.model;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import es.trident.rotomnet.model.util.UserRotomCardId;

/**
 * UserRotomCard: Entidad encargada de establecer la tabla
 * en la base de datos sobre las cartas que posee cada usuario
 */
@Entity
@IdClass(UserRotomCardId.class)
public class UserRotomCard implements Comparator<UserRotomCard>, Comparable<UserRotomCard>{
	
	@Id
	@MapsId("user")
	@ManyToOne
	private User user;
	@Id
	@MapsId("rotomCard")
	@ManyToOne
	private RotomCard rotomCard;
	private int amount;
	private boolean isShinyUnlocked;
	
	public UserRotomCard() { 
		
	}
	
	public UserRotomCard(User user, RotomCard rotomCard, boolean isShinyUnlocked) {
		this.user = user;
		this.rotomCard = rotomCard;
		this.amount = 1;
		this.isShinyUnlocked = isShinyUnlocked;
	}
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	
	public RotomCard getRottomCard() { return rotomCard; }
	public void setRottomCard(RotomCard rotomCard) { this.rotomCard = rotomCard; }
	
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }
	
	public boolean isShinyUnlocked() { return isShinyUnlocked; }
	public void setIsShinyUnlocked(boolean isShinyUnlocked) { this.isShinyUnlocked = isShinyUnlocked; }
	
	public void increaseAmountBy(int increment) {
		amount += increment;
	}
	
	public boolean noOwner() {
		return amount < 1;
	}
	
	public void updateShinyCondition(boolean shiny) {
		isShinyUnlocked = shiny || isShinyUnlocked;
	}

	@Override
	public int compareTo(UserRotomCard o) {
		return rotomCard.compareTo(o.rotomCard);
	}

	@Override
	public int compare(UserRotomCard o1, UserRotomCard o2) {
		// TODO Auto-generated method stub
		return rotomCard.compare(o1.rotomCard, o2.rotomCard);
	}
	
}

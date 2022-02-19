package es.trident.rotomnet.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

@Entity
@IdClass(UserRotomCardId.class)
public class UserRotomCard {
	
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
	
	public UserRotomCard() { }
	
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
}

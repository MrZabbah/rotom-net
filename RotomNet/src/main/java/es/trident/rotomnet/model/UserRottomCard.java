package es.trident.rotomnet.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(UserRottomCardId.class)
public class UserRottomCard {
	
	@Id
	@ManyToOne
	private User user;
	@Id
	@ManyToOne
	private RottomCard rottomCard;
	private int amount;
	private boolean isShinyUnlocked;
	
	protected UserRottomCard() {}
	
	public UserRottomCard(User user, RottomCard rottomCard, boolean isShinyUnlocked) {
		this.user = user;
		this.rottomCard = rottomCard;
		this.amount = 1;
		this.isShinyUnlocked = isShinyUnlocked;
	}
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	
	public RottomCard getRottomCard() { return rottomCard; }
	public void setRottomCard(RottomCard rottomCard) { this.rottomCard = rottomCard; }
	
	public int getAmount() { return amount; }
	public void setAmount(int amount) { this.amount = amount; }
	
	public boolean isShinyUnlocked() { return isShinyUnlocked; }
	public void setIsShinyUnlocked(boolean isShinyUnlocked) { this.isShinyUnlocked = isShinyUnlocked; }
	
	public void increaseAmountBy(int increment) {
		amount += increment;
	}
}

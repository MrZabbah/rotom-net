package es.trident.rotomnet.model;

import java.io.Serializable;
import java.util.Objects;


@SuppressWarnings("serial")
public class UserRottomCardId implements Serializable{

	private User user;
	
	private RottomCard rottomCard;
	
	public UserRottomCardId(User user, RottomCard rottomCard) {
		this.user = user;
		this.rottomCard = rottomCard;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rottomCard, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRottomCardId other = (UserRottomCardId) obj;
		return Objects.equals(rottomCard, other.rottomCard) && Objects.equals(user, other.user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RottomCard getRottomCard() {
		return rottomCard;
	}

	public void setRottomCard(RottomCard rottomCard) {
		this.rottomCard = rottomCard;
	}
	
	
}

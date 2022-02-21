package es.trident.rotomnet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String username;
	private String pwd;
	private int daysLogged;
	private String lastLog;

	// User Image
	private boolean image; // If there is an image.

	@Lob
	@JsonIgnore
	private Blob imageFile;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Team> teams;

	protected User() {
	}

	public User(String username, String pwd, int daysLogged, String lastLog) {
		this.username = username;
		this.pwd = pwd;
		this.daysLogged = daysLogged;
		this.lastLog = lastLog;
		this.teams = new ArrayList<Team>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userId == other.userId;
	}

	// Getter & Setters
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}
	
	public List<Team> getTeams(){
		return teams;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getDaysLogged() {
		return daysLogged;
	}

	public void setDaysLogged(int daysLogged) {
		this.daysLogged = daysLogged;
	}

	public String getLastLog() {
		return lastLog;
	}

	public void setLastLog(String lastLog) {
		this.lastLog = lastLog;
	}

	public boolean getImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}

	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob imageFile) {
		this.imageFile = imageFile;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}

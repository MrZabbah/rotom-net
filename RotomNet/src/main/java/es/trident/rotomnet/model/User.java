/**
 * PRACTICA DESTINADA A LA ASIGNATURA DESARROLLO DE APLICACIONES DISTRIBUIDAS
 * CAMPUS DE MÓSTOLES - CURSO 2021/2022
 */

package es.trident.rotomnet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User: entidad de la base de datos encargada de guardar la información
 * del usuario.
 */
@Entity
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;	
	private String username;
	private String pwd;
	private int daysLogged;
	private String lastLog;	
	private boolean image; 
	private String mail;
	@Lob
	@JsonIgnore
	private Blob imageFile;	
	@OneToMany(mappedBy="user" ,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserRotomCard> deck;
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Team> teams;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	protected User() {}

	public User(String username, String pwd, int daysLogged, String lastLog, String mail) {
		this.username = username;
		this.pwd = pwd;
		this.daysLogged = daysLogged;
		this.lastLog = lastLog;
		this.mail = mail;
		deck = new ArrayList<UserRotomCard>();
		this.teams = new ArrayList<Team>();
		this.roles = new ArrayList<String>();
		roles.add("USER");
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
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
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
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
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

	public List<UserRotomCard> getDeck() {
		return deck;
	}

	public void setDeck(List<UserRotomCard> deck) {
		this.deck = deck;
	}
	
	public List<Team> getTeams(){
		return teams;
	}	
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}	
	
	public List<String> getRoles(){
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public void addRole(String role) {
		roles.add(role);
	}

}

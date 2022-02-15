package es.trident.rotomnet.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String username;
	private String pwd;
	private int daysLogged;
	private String lastLog;
	
	//User Image
	private boolean image; //If there is an image.
	
	@Lob
	@JsonIgnore
	private Blob imageFile;
	
	//List<Team> teams;
	//Dictionary<int, tipo_raro> deck;
	
	protected User(){}
	
	public User(String username, String pwd, int daysLogged, String lastLog) {
		this.username = username;
		this.pwd = pwd;	
		this.daysLogged = daysLogged;		
        this.lastLog = lastLog;
	}
	
	//Getter & Setters
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

}

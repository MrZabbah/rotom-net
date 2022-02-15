package es.trident.rotomnet.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

@Entity
public class User {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String username;
	private String pwd;
	private int daysLogged;
	private String lastLog;
	
	//List<Team> teams;
	//Dictionary<int, tipo_raro> deck;
	
	protected User(){}
	
	public User(String username, String pwd) {
		this.username = username;
		this.pwd = pwd;	
		daysLogged = 1;		
        lastLog = dtf.format(LocalDateTime.now());
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
	

}

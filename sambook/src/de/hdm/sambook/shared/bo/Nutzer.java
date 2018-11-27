package de.hdm.sambook.shared.bo;

import java.util.Date;

public class Nutzer extends BusinessObject{
	private static final long serialVersionUID = 1L; 
	
	private Date erstellungszeitpunkt;
	private String nickname;
	private String vorname;
	private String nachname;
	private String email;
	
	public Date getErstellungszeitpunkt() {
		return erstellungszeitpunkt;
	}
	public void setErstellungszeitpunkt(Date erstellungszeitpunkt) {
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

package de.hdm.sambook.shared.bo;

import java.util.Date;

public class Pinnwand extends BusinessObject{
	private static final long serialVersionUID = 1L; 
	
	private Date erstellungszeitpunkt;
	private int ownerID=0;
	

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}

	public Date getErstellungszeitpunkt() {
		return erstellungszeitpunkt;
	}

	public void setErstellungszeitpunkt(Date erstellungszeitpunkt) {
		this.erstellungszeitpunkt = erstellungszeitpunkt;
	}

	@Override
	public String toString() {
		return super.toString()+ "inhaber, Nutzer-ID: #" + this.ownerID;
	}
	
	

}

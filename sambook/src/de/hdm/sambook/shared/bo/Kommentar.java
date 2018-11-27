package de.hdm.sambook.shared.bo;

import java.util.Date;

public class Kommentar extends BusinessObject{
	private static final long serialVersionUID = 1L; 
		
		private Date erstellungszeitpunkt;
		private String text;
		public Date getErstellungszeitpunkt() {
			return erstellungszeitpunkt;
		}
		public void setErstellungszeitpunkt(Date erstellungszeitpunkt) {
			this.erstellungszeitpunkt = erstellungszeitpunkt;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	

}

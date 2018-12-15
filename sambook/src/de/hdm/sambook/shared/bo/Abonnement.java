package de.hdm.sambook.shared.bo;

import java.util.Date;

public class Abonnement extends BusinessObject {

  private static final long serialVersionUID = 1L;

  private Date erstellungszeitpunkt;

public Date getErstellungszeitpunkt() {
	return erstellungszeitpunkt;
}

public void setErstellungszeitpunkt(Date erstellungszeitpunkt) {
	this.erstellungszeitpunkt = erstellungszeitpunkt;

}

@Override
public int getId() {
	// TODO Auto-generated method stub
	return getId();
}
  
  
}

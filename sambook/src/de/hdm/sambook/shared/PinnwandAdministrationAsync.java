package de.hdm.sambook.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;


import de.hdm.sambook.shared.bo.Abonnement;
import de.hdm.sambook.shared.bo.Beitrag;
import de.hdm.sambook.shared.bo.Kommentar;
import de.hdm.sambook.shared.bo.Nutzer;
import de.hdm.sambook.shared.bo.Pinnwand;

public interface PinnwandAdministrationAsync {

	void init(AsyncCallback<Void> callback);
	
	void createAbonnement(Date erstellungszeitpunkt, AsyncCallback<Abonnement>callback);
	
	void update(Abonnement a, AsyncCallback<Void> callback);
	
	void delete(Abonnement a, AsyncCallback<Void> callback);
	
	void getAbonnementById(int id, AsyncCallback<Abonnement>callback);
	
	void getAllAbonnement(AsyncCallback<Vector<Abonnement>>callback);
	
	///////////////////////////////////////////////////////////////////
	
	void createBeitrag(Date erstellungszeitpunkt, String text, AsyncCallback<Beitrag>callback);
	
	void update(Beitrag b, AsyncCallback<Void> callback);
	
	void delete(Beitrag b, AsyncCallback<Void> callback);
	
	void getBeitragById(int id, AsyncCallback<Beitrag>callback);
	
	void getAllBeitrag(AsyncCallback<Vector<Beitrag>>callback);
	
	void getBeitragOf(Pinnwand p, AsyncCallback<Vector<Beitrag>>callback);
	///////////////////////////////////////////////////////////////////
	
	void createKommentar(Date erstellungszeitpunkt, String text, AsyncCallback<Kommentar>callback);
	
	void update(Kommentar k, AsyncCallback<Void> callback);
	
	void delete(Kommentar k, AsyncCallback<Void> callback);
	
	void getKommentarById(int id, AsyncCallback<Kommentar>callback);
	
	void getAllKommentar(AsyncCallback<Vector<Kommentar>>callback);
	
	void getKommentarOf(Beitrag b, AsyncCallback<Vector<Kommentar>>callback);
	
	void getKommentarOf(Nutzer n, AsyncCallback<Vector<Kommentar>>callback);
	
	///////////////////////////////////////////////////////////////////
	
	void createNutzer(Date erstellungszeitpunkt, String nickname, String vorname, String nachname, String email, AsyncCallback<Nutzer>callback);
	
	void update(Nutzer n, AsyncCallback<Void>callback);
	
	void delete(Nutzer n, AsyncCallback<Void>callback);
	
	void getAllNutzer(AsyncCallback<Vector<Nutzer>>callback);
	
	void getNutzerById(int id, AsyncCallback<Nutzer>callback);
	
	void getNutzerByEmail(String email, AsyncCallback<Vector<Nutzer>>callback);
	
	void getNutzerOf(Pinnwand p, AsyncCallback<Vector<Nutzer>>callback);
	
	void getNutzerByMailOrCreate(String email, AsyncCallback<Vector<Nutzer>> callback);
	
	void checkNutzer(String mail, AsyncCallback<Nutzer> callback);
	
	///////////////////////////////////////////////////////////////////
	
	void createPinnwand(Date erstellungszeitpunkt, AsyncCallback<Pinnwand>callback);
	
	void update(Pinnwand p, AsyncCallback<Void>callback);
	
	void delete(Pinnwand p, AsyncCallback<Void>callback);
	
	void getPinnwandOf(Nutzer n, AsyncCallback<Vector<Pinnwand>>callback);
	
	void getPinnwandById(int id, AsyncCallback<Pinnwand>callback);
	
	void getAllPinnwand(AsyncCallback<Vector<Pinnwand>>callback);
	
	void createPinnwandFor(Nutzer n, AsyncCallback<Pinnwand>callback);

	void setPinnwand(Pinnwand pinnwand, AsyncCallback<Void>callback);

}

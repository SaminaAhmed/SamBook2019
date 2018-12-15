package de.hdm.sambook.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.sambook.shared.bo.*;

/**
 * Synchrone Schnittstelle für eine RPC-Fähige Klasse zur Verwaltung von Pinnwänden
 * --> das Löschen eines Nutzers erfordert Kenntnisse über die Verpflechtung eines 
 * Nutzers mit Pinnwand-Objekten. Um die Klasse Pinnwand/Nutzer nicht zu stark an
 * andere Klassen zu koppeln, wird das Wissen darüber, wie einzelne Daten-Objekte 
 * koexistieren, in der vorliegenden Klasse gekapselt. 
 * 
 * @RemoteServiceRelativePath("pinnwandadministration")</code> ist bei der
 * Adressierung des aus der zugehörigen Impl-Klasse entstehenden
 * Servlet-Kompilats behilflich. Es gibt im Wesentlichen einen Teil der URL des
 * Servlets an.
 * 
 * @author Samina
 *
 */

@RemoteServiceRelativePath("pinnwandadministration")
public interface PinnwandAdministration extends RemoteService{
	/**
	 * Initialisierung des Objekts. Diese Methode ist zu dem 
	 * NoArgument Constructor der implementierenden Klasse 
	 * notwendig. 
	 * 
	 * @throws IllegalArgumentException
	 */
	public void init() throws IllegalArgumentException;
	
	/**
	 * 
	 * @param erstellungszeitpunkt
	 * @param nickname
	 * @param vorname
	 * @param nachname
	 * @return Ein fertiges Nutzer-Objekt
	 * @throws IllegalArgumentException
	 */
	public Abonnement createAbonnement(Date erstellungszeitpunkt)
	throws IllegalArgumentException;

	
	public void update(Abonnement a) throws IllegalArgumentException;
	
	public void delete(Abonnement a) throws IllegalArgumentException;
	
	public Abonnement getAbonnementById(int id) throws IllegalArgumentException;

	public Vector<Abonnement> getAllAbonnement() throws IllegalArgumentException;
	
	///////////////////////////////////////////////////////////////////
	
	public Beitrag createBeitrag(Date erstellungszeitpunkt, String text)
			throws IllegalArgumentException;
	
	public void update(Beitrag b) throws IllegalArgumentException;
	
	public void delete(Beitrag b) throws IllegalArgumentException;
	
	public Beitrag getBeitragById(int id) throws IllegalArgumentException;

	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException;
	
	public Vector<Beitrag> getBeitragOf(Pinnwand p) throws IllegalArgumentException;
	
	///////////////////////////////////////////////////////////////////
	
	public Kommentar createKommentar(Date erstellungszeitpunkt, String text)
			throws IllegalArgumentException;
	
	public void update(Kommentar k) throws IllegalArgumentException;
	
	public void delete(Kommentar k) throws IllegalArgumentException;
	
	public Kommentar getKommentarById(int id) throws IllegalArgumentException;
	
	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException;
	
	public Vector<Kommentar> getKommentarOf(Beitrag b) throws IllegalArgumentException;

	public Vector<Kommentar> getKommentarOf(Nutzer n) throws IllegalArgumentException;
	
	///////////////////////////////////////////////////////////////////
	
	public Nutzer createNutzer(Date erstellungszeitpunkt, String nickname, String vorname, String nachname, String email)
	throws IllegalArgumentException;
	
	public void update(Nutzer n) throws IllegalArgumentException;
	
	public void delete(Nutzer n) throws IllegalArgumentException;
	
	public Vector<Nutzer> getAllNutzer() throws IllegalArgumentException;

	public Nutzer getNutzerById(int id) throws IllegalArgumentException;
	
	public Vector<Nutzer> getNutzerByEmail(String email) throws IllegalArgumentException;
	
	public Vector<Nutzer> getNutzerOf(Pinnwand p) throws IllegalArgumentException;
	
	public Vector<Nutzer> getNutzerByMailOrCreate(String email) throws Exception;
	
	public Nutzer checkNutzer(String email) ;
	
	///////////////////////////////////////////////////////////////////
	
	public Pinnwand createPinnwand(Date erstellungszeitpunkt) throws IllegalArgumentException;
	
	public void update(Pinnwand p) throws IllegalArgumentException;
	
	public void delete(Pinnwand p) throws IllegalArgumentException;

	public Vector<Pinnwand> getPinnwandOf(Nutzer n) throws IllegalArgumentException;

	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException;

	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException;

	public Pinnwand createPinnwandFor(Nutzer n) throws IllegalArgumentException;
	
	public  void setPinnwand(Pinnwand pinnwand) throws IllegalArgumentException;

	



	

	



	
	

	
	
}

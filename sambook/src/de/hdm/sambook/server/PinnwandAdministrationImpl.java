package de.hdm.sambook.server;


import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.sambook.server.db.AbonnementMapper;
import de.hdm.sambook.server.db.BeitragMapper;
import de.hdm.sambook.server.db.KommentarMapper;
import de.hdm.sambook.server.db.NutzerMapper;
import de.hdm.sambook.server.db.PinnwandMapper;
import de.hdm.sambook.shared.PinnwandAdministration;
import de.hdm.sambook.shared.bo.Abonnement;
import de.hdm.sambook.shared.bo.Beitrag;
import de.hdm.sambook.shared.bo.Kommentar;
import de.hdm.sambook.shared.bo.Nutzer;
import de.hdm.sambook.shared.bo.Pinnwand;

@SuppressWarnings("serial")
public class PinnwandAdministrationImpl extends RemoteServiceServlet
implements PinnwandAdministration{
	
	private Pinnwand pinnwand = null;
	
	private  AbonnementMapper aMapper = null;
	private BeitragMapper bMapper = null;
	private KommentarMapper kMapper = null;
	private NutzerMapper nMapper = null; // 0 ist der Startpunkt
	private PinnwandMapper pMapper = null;
	
	public void init() throws IllegalArgumentException{
		this.aMapper = AbonnementMapper.aMapper();
		this.bMapper = BeitragMapper.bMapper();
		this.kMapper = KommentarMapper.kMapper();
		this.nMapper = NutzerMapper.nMapper();
		this.pMapper = PinnwandMapper.pMapper();
		
	}
	///////////////////////////////////////////////////////////////
	@Override
	public Abonnement createAbonnement(Date erstellungszeitpunkt) throws IllegalArgumentException{
		
		
		Abonnement a = new Abonnement();
		a.setErstellungszeitpunkt(erstellungszeitpunkt);
		
		return this.aMapper.insert(a);
	}

	@Override
	public void update(Abonnement a) throws IllegalArgumentException {
		aMapper.update(a);
		
	}
	@Override
	public void delete(Abonnement a) throws IllegalArgumentException {
		this.aMapper.delete(a);
		
	}
	
	@Override
	public Abonnement getAbonnementById(int id) throws IllegalArgumentException{
		return this.aMapper.findByKey(id);
	}
	
	@Override
	public Vector<Abonnement> getAllAbonnement() throws IllegalArgumentException{
		return this.aMapper.findAll();
	}
	///////////////////////////////////////////////////////////////////

	@Override
	public Beitrag createBeitrag(Date erstellungszeitpunkt, String text) throws IllegalArgumentException {
		
		Beitrag b = new Beitrag();
		b.setErstellungszeitpunkt(erstellungszeitpunkt);
		b.setText(text);
		
		return this.bMapper.insert(b);
	}

	@Override
	public void update(Beitrag b) throws IllegalArgumentException{
		bMapper.update(b);
	}

	@Override
	public void delete(Beitrag b) throws IllegalArgumentException {
		this.bMapper.delete(b);
		
	}
	
	@Override
	public Beitrag getBeitragById(int id) throws IllegalArgumentException{
		return this.bMapper.findByKey(id);
	}
	
	
	@Override
	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException{
		return this.bMapper.findAll();
	}
	
	@Override
	public Vector<Beitrag> getBeitragOf(Pinnwand p) throws IllegalArgumentException{
		return this.bMapper.findByPinnwand(p);
	}
	///////////////////////////////////////////////////////////////////
	@Override
	public Kommentar createKommentar(Date erstellungszeitpunkt, String text) throws IllegalArgumentException {
		
		Kommentar k = new Kommentar();
		k.setErstellungszeitpunkt(erstellungszeitpunkt);
		k.setText(text);
		
		return this.kMapper.insert(k);
	}

	@Override
	public void update(Kommentar k) throws IllegalArgumentException{
		kMapper.update(k);
	}

	@Override
	public void delete(Kommentar k) throws IllegalArgumentException {
		this.kMapper.delete(k);
		
	}
	
	@Override
	public Kommentar getKommentarById(int id) throws IllegalArgumentException{
		return this.kMapper.findByKey(id);
	}
	
	@Override
	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException{
		return this.kMapper.findAll();
	}
	
	@Override
	public Vector<Kommentar> getKommentarOf(Beitrag b) throws IllegalArgumentException{
		return this.kMapper.findByBeitrag(b.getId());
		
	}
	
	@Override
	public Vector<Kommentar> getKommentarOf(Nutzer n) throws IllegalArgumentException{
		return this.kMapper.findByBeitrag(n.getId());
		
	}
	
	/////////////////////////////////////////////////////////////////////
	
	@Override
	public Nutzer createNutzer(Date erstellungszeitpunkt, String nickname, String vorname, String nachname, String email) throws IllegalArgumentException{

		Nutzer n = new Nutzer();
		n.setErstellungszeitpunkt(erstellungszeitpunkt);
		n.setNickname(nickname);
		n.setVorname(vorname);
		n.setNachname(nachname);
		n.setEmail(email);
		return this.nMapper.insert(n);

	}
	/**
	 * 
	 * @param id
	 * @return Nutzer anhand seiner ID
	 * @throws IllegalArgumentException
	 */
	
	@Override
	public Nutzer getNutzerById(int id) throws IllegalArgumentException{
		return this.nMapper.findByKey(id);
	}
	
	@Override
	public Vector<Nutzer> getAllNutzer() throws IllegalArgumentException{
		return this.nMapper.findAll();
	}
	
	@Override
	public void update(Nutzer n) throws IllegalArgumentException{
		nMapper.update(n);
	}
	
	@Override
	public void delete(Nutzer n) throws IllegalArgumentException{
		Vector<Pinnwand> pinnwand = this.getPinnwandOf(n);
		if(pinnwand != null) {
			for(Pinnwand p : pinnwand) {
				this.delete(p);
			}
		}
	}
	
	@Override
	public Vector<Nutzer> getNutzerByEmail(String email) throws IllegalArgumentException{
		return this.nMapper.findByEmail(email);
	}
	
	@Override
	public Vector<Nutzer> getNutzerOf(Pinnwand p) throws IllegalArgumentException{
		return this.nMapper.findByPinnwand(p);
	}
	
	@Override
	public Vector<Nutzer> getNutzerByMailOrCreate(String email) throws Exception {
		Vector<Nutzer> nutzer = this.nMapper.findByEmail(email);
		if(nutzer != null) {
			return nutzer;
		} else {
			Nutzer newNutzer = new Nutzer();
			newNutzer.setEmail(email);
			this.nMapper.insert(newNutzer).getId();
			
			return this.nMapper.findByEmail(email);
		}
	}
	
	public Nutzer checkNutzer(String email) {
		// TODO Auto-generated method stub
		return null;
		}
	/////////////////////////////////////////////////////////////////////
	
	@Override
	public Pinnwand createPinnwand(Date erstellungszeitpunkt) throws IllegalArgumentException{
		Pinnwand p = new Pinnwand();
		p.setErstellungszeitpunkt(erstellungszeitpunkt);
		return this.pMapper.insert(p);
		
	}
	
	@Override
	public Pinnwand createPinnwandFor(Nutzer n) throws IllegalArgumentException{
		Pinnwand p = new Pinnwand();
		p.setOwnerID(n.getId());
		p.setId(1);//kann das sein?
		return this.pMapper.insert(p);
	}
	
	@Override
	public void update(Pinnwand p) throws IllegalArgumentException{
		pMapper.update(p);
	}
	
	@Override
	public void delete(Pinnwand p) throws IllegalArgumentException{
		this.pMapper.delete(p);
	}
	
	@Override
	public Vector<Pinnwand> getPinnwandOf(Nutzer n) throws IllegalArgumentException{
		return this.pMapper.findByOwner(n);
	}
	
	@Override
	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException{
		return this.pMapper.findByKey(id);
	}
	
	@Override
	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException{
		return this.pMapper.findAll();
	}
	
	@Override
	public void setPinnwand(Pinnwand pinnwand) throws IllegalArgumentException {
	    this.pinnwand = pinnwand;
	  }
	///////////////////////////////////////////////////////////////////////
	
	
}

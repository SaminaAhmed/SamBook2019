package de.hdm.sambook.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.sambook.shared.PinnwandAdministrationAsync;
import de.hdm.sambook.shared.bo.Nutzer;

public class FindNutzerByName extends Showcase{
	//blabla Test Test?
	@Override
	protected String getHeadlineText() {
		return "Finde Nutzer anhand von Namen";
	}
	
	@Override
	protected void run() {
		this.append("Ich suche Nutzer, die die E-Mail Adresse atef.ahmed.bhatti@gmail.com");
		PinnwandAdministrationAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandAdministration();
		pinnwandVerwaltung.getNutzerByEmail("atef.ahmed.bhatti@gmail.com", new  NutzerEmailAusgebencallback(this));
	}
		class NutzerEmailAusgebencallback implements AsyncCallback<Vector<Nutzer>>{
			private Showcase showcase = null;
			
			public NutzerEmailAusgebencallback(Showcase c) {
				this.showcase = c;
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				this.showcase.append("Fehler bei der Abfrage" + caught.getMessage());
			}
			
			@Override
			public void onSuccess(Vector<Nutzer> result) {
				if(result != null) {
					for (Nutzer n : result) {
						if(n != null) {
							this.showcase.append("Nutzer #" + n.getEmail() + ": " + n.getVorname()
							+ ", " + n.getNachname());
						}
					}
					if (result.size() == 1)
						this.showcase.append("1 Element erhalten");
					else
						this.showcase.append(result.size()+ "Elemente erhalten");
				}
				else {
					ClientsideSettings.getLogger().info("result==null");
				}
			}
		}
	}
		
	

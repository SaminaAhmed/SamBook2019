package de.hdm.sambook.client.gui;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.sambook.client.ClientsideSettings;
import de.hdm.sambook.client.FindNutzerByName;
import de.hdm.sambook.client.LoginInfo;
import de.hdm.sambook.client.Showcase;
import de.hdm.sambook.shared.LoginService;
import de.hdm.sambook.shared.LoginServiceAsync;
import de.hdm.sambook.shared.PinnwandAdministrationAsync;
import de.hdm.sambook.shared.bo.Nutzer;
import de.hdm.sambook.shared.bo.Pinnwand;

public class Sambook implements EntryPoint{

//	private LoginInfo loginInfo = null;
//	private VerticalPanel loginPanel = new VerticalPanel();
//	private Anchor signInLink = new Anchor("Sign In");
//	private Anchor signOutLink = new Anchor("Sign Out");
//	
//	private static PinnwandAdministrationAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandAdministration();
//	
//	private TextBox textBox = new TextBox();
//	private Button logoutButton = new Button("Logout");
//	private Button loginButton = new Button("> Bitte logge dich mit dem Google Account ein");
//	
//	Nutzer n = new Nutzer();
	
	
	
	@Override
	public void onModuleLoad() {
		
	
		
		/*Ich weiße der Pinnwand Administration ein Erstellungszeitpunkt zu
		 */
		
		PinnwandAdministrationAsync pinnwandVerwaltung = ClientsideSettings.getPinnwandAdministration();
			Pinnwand pinnwand = new Pinnwand();
			Date erstellungszeitpunkt = null;
			pinnwand.setErstellungszeitpunkt(erstellungszeitpunkt);
			pinnwandVerwaltung.setPinnwand(pinnwand, new SetPinnwandCallback());
			
			VerticalPanel navPanel = new VerticalPanel();
			
			/*
		     * Das VerticalPanel wird einem DIV-Element namens "Navigator" in der
		     * zugehörigen HTML-Datei zugewiesen und erhält so seinen Darstellungsort.
		     */
			RootPanel.get("Navigator").add(navPanel);
			 /*
		     * Ab hier bauen wir sukzessive den Navigator mit seinen Buttons aus.
		     * Neues Button Widget erzeugen und eine Beschriftung festlegen.
		     */
			final Button findNutzerButton = new Button("Nutzer suche");
			
			/*
		     * Unter welchem Namen können wir den Button durch die CSS-Datei des
		     * Projekts formatieren?
		     */
			findNutzerButton.setStylePrimaryName("nutzer-button");
			
			/*
		     * Hinzufügen des Buttons zum VerticalPanel.
		     */
			navPanel.add(findNutzerButton);
			
			/*
		     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
		     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
		     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
		     * wird.
		     */
			findNutzerButton.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event) {
					/*
			         * Showcase instantiieren.
			         */
					Showcase showcase = new FindNutzerByName();
					/*
			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
				}
			});
			
		
	}
	public class SetPinnwandCallback implements AsyncCallback<Void>{
		
		@Override
		public void onFailure(Throwable caught) {
			ClientsideSettings.getLogger().severe("Setzen der Pinnwand Fehlgeschlagen");
		}
		
		@Override
		public void onSuccess(Void result) {
			
		}
	}
}


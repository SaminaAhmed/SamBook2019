package de.hdm.sambook.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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

import de.hdm.sambook.shared.LoginService;
import de.hdm.sambook.shared.LoginServiceAsync;
import de.hdm.sambook.shared.PinnwandAdministrationAsync;
import de.hdm.sambook.shared.bo.Nutzer;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Sambook implements EntryPoint {
	
	 private LoginInfo loginInfo = null;
	 private VerticalPanel loginPanel = new VerticalPanel();
	 private Label loginLabel = new Label(
	      "Please sign in to your Google Account to access the StockWatcher application.");
	 private Anchor signInLink = new Anchor("Sign In");
	 
	 

	    // Check login status using login service.
	    LoginServiceAsync loginService = GWT.create(LoginService.class);

	@Override
	public void onModuleLoad() {
		//RootPanel.get("content".add(new Label("Hello, World!"));
		Window.alert("jj");
			    loginService.login(GWT.getHostPageBaseURL() + "Sambook.html", new AsyncCallback<LoginInfo>() {
			    	
			      public void onFailure(Throwable error) {
			    	  error.getMessage().toString();
			      }

			      public void onSuccess(LoginInfo result) {
			    	  Window.alert("he");
			        loginInfo = result;
			        if(loginInfo.isLoggedIn()) {
			        	loadPinnWand();
			        } else {
			          loadLogin();
			        }
			      }
			    });
			  

	}
	
	/**
	 * Wird beim Start der Applikation nach dem login geladen.
	 * (Wenn User vorhanden in der DB).
	 */
	public void loadPinnWand() {
	//Navigation Klasse reinladen
	}
	
	
	/**
	 * Methode wird aufgerufen, wenn User nicht eingeloggt.
	 */
	public void loadLogin() {
		
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		loginPanel.addStyleName("login");
		
		// Footer wird geelert fuer den LoginContainer
		RootPanel.get("navi").clear();
		RootPanel.get("content").clear();
		// Hinzufuegen des LoginPanels in den <div> Container "content"
		RootPanel.get("content").add(loginPanel);	
	}

}

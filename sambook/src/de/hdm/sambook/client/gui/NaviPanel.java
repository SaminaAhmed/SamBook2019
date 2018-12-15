package de.hdm.sambook.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NaviPanel extends VerticalPanel{
	
	public void onModuleLoad() {
	 VerticalPanel navPanel = new VerticalPanel();
	 RootPanel.get("body").add(navPanel);
	 
	 final Button btn1 = new Button("Abonnement Suche");
	
	
	navPanel.add(btn1);
	
	btn1.addClickHandler(new ClickHandler(){
		
		@Override
		public void onClick(ClickEvent event) {
			//DialogBox Klasse aufrufen
			DialogBox dbox = new DialogBox();
		}
		//
	
	});

}
}
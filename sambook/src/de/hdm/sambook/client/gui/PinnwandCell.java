package de.hdm.sambook.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.sambook.shared.bo.Nutzer;
/**
 * Diese Klasse dient der Dartsellungen von Nutzern(also der Name der Pinnwände,
 * die ich abonniert habe)
 * @author Samina
 *
 */

public class PinnwandCell extends AbstractCell<Nutzer>{
	boolean b =false;

	@Override
	public void render(Context context, Nutzer n, SafeHtmlBuilder sb) {
		if(n == null) {
			return;
		}
		
		sb.appendHtmlConstant("div");
		sb.appendHtmlConstant("<p><span class='glyphicon glyphicon-user'></span> &nbsp; ");
		sb.appendEscaped(n.getNickname());
		sb.appendHtmlConstant(" ");
		sb.appendHtmlConstant("</div>");
		
		
	}

}

package de.hdm.sambook.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import de.hdm.sambook.shared.CommonSettings;
import de.hdm.sambook.shared.PinnwandAdministration;
import de.hdm.sambook.shared.PinnwandAdministrationAsync;
import de.hdm.sambook.shared.LoginService;
import de.hdm.sambook.shared.LoginServiceAsync;


/**
 * Klasse mit Eigenschaften und Diensten, die f�r alle Client-seitigen Klassen
 * relevant sind.
 * 
 * @author thies
 * @version 1.0
 * @since 04.05.2018
 * 
 */
public class ClientsideSettings extends CommonSettings {
	/**
	 * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	 * namens <code>KontaktSharingAdministration</code>.
	 */

	private static PinnwandAdministrationAsync administration = null;

	private static LoginServiceAsync loginService = null;

	

	/**
	 * Name des Client-seitigen Loggers.
	 */
	private static final String LOGGER_NAME = "Pinnwand Web Client";

	/**
	 * Instanz des Client-seitigen Loggers.
	 */
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	/**
	 * <p>
	 * Auslesen des applikationsweiten (Client-seitig!) zentralen Loggers.
	 * </p>
	 * 
	 * <h2>Anwendungsbeispiel:</h2> Zugriff auf den Logger herstellen durch:
	 * 
	 * <pre>
	 * Logger logger = ClientSideSettings.getLogger();
	 * </pre>
	 * 
	 * und dann Nachrichten schreiben etwa mittels
	 * 
	 * <pre>
	 * logger.severe(&quot;Sie sind nicht berechtigt, ...&quot;);
	 * </pre>
	 * 
	 * oder
	 * 
	 * <pre>
	 * logger.info(&quot;Lege neuen Kunden an.&quot;);
	 * </pre>
	 * 
	 * <p>
	 * Bitte auf <em>angemessene Log Levels</em> achten! Severe und info sind nur
	 * Beispiele.
	 * </p>
	 * 
	 * <h2>HINWEIS:</h2>
	 * <p>
	 * Beachten Sie, dass Sie den auszugebenden Log nun nicht mehr durch
	 * bedarfsweise Einf�gen und Auskommentieren etwa von
	 * <code>System.out.println(...);</code> steuern. Sie belassen k�nftig
	 * s�mtliches Logging im Code und k�nnen ohne abermaliges Kompilieren den Log
	 * Level "von au�en" durch die Datei <code>logging.properties</code> steuern.
	 * Sie finden diese Datei in Ihrem <code>war/WEB-INF</code>-Ordner. Der dort
	 * standardm��ig vorgegebene Log Level ist <code>WARN</code>. Dies w�rde
	 * bedeuten, dass Sie keine <code>INFO</code>-Meldungen wohl aber
	 * <code>WARN</code>- und <code>SEVERE</code>-Meldungen erhielten. Wenn Sie also
	 * auch Log des Levels <code>INFO</code> wollten, m�ssten Sie in dieser Datei
	 * <code>.level = INFO</code> setzen.
	 * </p>
	 * 
	 * Weitere Infos siehe Dokumentation zu Java Logging.
	 * 
	 * @return die Logger-Instanz f�r die Server-Seite
	 */
	public static Logger getLogger() {
		return log;
	}

	/**
	 * <p>
	 * Anlegen und Auslesen der applikationsweit eindeutigen KontaktSharingAdministration.
	 * Diese Methode erstellt die KontaktShringAdministration, sofern sie noch nicht
	 * existiert. Bei wiederholtem Aufruf dieser Methode wird stets das bereits
	 * zuvor angelegte Objekt zur�ckgegeben.
	 * </p>
	 * 
	 * <p>
	 * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	 * <code>KontaktsharingAdministrationAsync kontaktsharingAdministration = ClientSideSettings.getKontaktSharingAdministration()</code>
	 * .
	 * </p>
	 * 
	 * @return eindeutige Instanz des Typs <code>KontaktSharingAdministrationAsync</code>
	 * @author Peter Thies, Jiayi
	 * @since 04.05.2018
	 */
	public static PinnwandAdministrationAsync getPinnwandAdministration() {
		// Gab es bislang noch keine KontaktsharingAdministration-Instanz, dann...
		if (administration == null) {
			// Zun�chst instantiieren wir KontaktSharingAdministration
			administration = GWT.create(PinnwandAdministration.class);
		}

		// So, nun brauchen wir die BankAdministration nur noch zur�ckzugeben.
		return administration;
	}

	public static LoginServiceAsync getLoginService() {
		if (loginService == null) {
			loginService = GWT.create(LoginService.class);
		}
		return loginService;
	}

	
	
	

}

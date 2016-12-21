package pis.hue2.client;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * 
 * @author Cornelius Knapp Matnr 719074
 * Gui für Fehlermeldungen
 */
@SuppressWarnings("serial")
public class FehlerMeldung extends JFrame {
	private Container c;
	
public FehlerMeldung() {
		
		c = getContentPane();
		
	}
	public Container gibContainer() {
	
	return c;
	
}

}

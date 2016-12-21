package pis.hue2.client;




/**
 *  
 * @author Cornelius Knapp Matnr 719074
 *  Graphische Oberfläche für den Doppelwürfelverschlüsseler bestehend aus 2 Textfeldern für die Losungsworte,
 *  2 Textareas für den ver und den Entschlüsselten Text, je einen Button für ver und entschlüsseln.
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;






public class Clientguy extends JFrame implements Clientinterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7821376444420741090L;
	private Container c;
	private JButton button[] = new JButton[3];
	private JLabel label[]= new JLabel[5];
	private JTextArea textArea[]= new JTextArea[3]; 
	private JTextField textfeld[]= new JTextField[2];
	private JPanel panel[] = new JPanel[3];
	
	
	public Clientguy () {
		
	
		
		c = getContentPane();
		c.setLayout(new BorderLayout(10,10));
		label[0] = new JLabel("Chat:", JLabel.CENTER);
		label[1] = new JLabel("Namensliste", JLabel.CENTER);
		textfeld[0]= new JTextField(20);
		textfeld[1]= new JTextField(20);
		textArea[0] = new JTextArea();
		textArea[1] = new JTextArea();
		label[2] = new JLabel("Name");
		label[3] = new JLabel("NamensListe");
		button[0] = new JButton("Senden");
		button[1] = new JButton("Empfangen");
		button[2]= new JButton("Beenden");
		panel[0] = new JPanel(new GridLayout(0,4,15,5));
		panel[1]= new JPanel();
		panel[2]= new JPanel();
		textArea[0].setRows(10); //Klartext
		textArea[0].setLineWrap(true);
		textArea[1].setRows(10); //verschlüsselter text
		textArea[1].setLineWrap(true);
		
		panel[0].add(label[0]);
		panel[0].add(textArea[0]);
		panel[1].add(label[2]);
		panel[1].add(textfeld[0]);
		panel[2].add(button[0]);
		panel[0].add(label[1]);
		panel[0].add(textArea[1]);
		panel[1].add(label[3]);
		panel[1].add(textfeld[1]);
		panel[2].add(button[1]);
		panel[2].add(button[2]);
		
		c.add(panel[0], BorderLayout.NORTH);
		c.add(panel[1], BorderLayout.CENTER);
		c.add(panel[2], BorderLayout.SOUTH);
		
		
		
	
	ActionListener bldecode = new ActionListener () { //
		public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
			
			try {
				
					if(textfeld[0].getText().length() == 0) 
					throw new IllegalArgumentException(" kein  Name");
				
					
			} 
			catch (IllegalArgumentException e1) 
			{
				
				textArea[1].setText("");
				FehlerMeldung exception = new FehlerMeldung();
				if(e1.getMessage().length() == 0)
					label[4] = new JLabel("Eingabe Text Fehler!", JLabel.CENTER);
				else
					label[4] = new JLabel(e1.getMessage(), JLabel.CENTER);
				exception.gibContainer().add(label[4]);
				exception.setTitle("Fehler");
				exception.setSize(200,200);
				exception.setLocationRelativeTo(c);
				exception.setVisible(true);
				exception.setDefaultCloseOperation(HIDE_ON_CLOSE);
			}
			}	
		};
		
		ActionListener BLencode = new ActionListener() {	//verschluesseln
		public void actionPerformed(ActionEvent e)throws IllegalArgumentException {
			try {
				
				if(textfeld[0].getText().length() == 0) 
					throw new IllegalArgumentException("Keine Name - kein Senden");
				
				
				
			} catch (IllegalArgumentException e1) {
				
				textArea[1].setText("");
				FehlerMeldung exception = new FehlerMeldung();
				if(e1.getMessage().length() == 0)
					label[4] =new JLabel("fehlerhafte Eingabe!", JLabel.CENTER);
				else
					label[4] =new JLabel(e1.getMessage(), JLabel.CENTER);
				exception.gibContainer().add(label[4]);
				exception.setTitle("Grosser unbekannter Fehler");
				exception.setSize(200,200);
				exception.setVisible(true);
				exception.setDefaultCloseOperation(HIDE_ON_CLOSE);
				
			}	
		}
		
		
		
	}; 
	ActionListener BLEnd = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		};
	
		button[0].addActionListener(BLencode);
		button[1].addActionListener(bldecode);
		button[2].addActionListener(BLEnd);
		}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new Clientguy ();
		frame.setTitle("Chatclient 1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400);
		frame.setVisible(true);
	}
	
	public void empfange(String message)
	{
		
	}
	
	public String sende(String message)
	{
		return message;
	}

}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {
	public interface Receive {
	public void Receive(String msg );
	}
	private JButton button1, button2;
	Container c;
	JLabel text1, text2;
	JTextArea text3, text4;
	JTextField text5, text6;
	JScrollPane jsp;
	Color hell = new Color(250,250,250);
	
	
	class ButtonListener implements ActionListener { //einloggen
		public void actionPerformed(ActionEvent e) {
			String eins= text3.getText();
			String zwei= text5.getText();
			String vier= text6.getText();
			
		}
		}
	class ButtonListener2 implements ActionListener { //senden
		public void actionPerformed(ActionEvent e) {
		String eins= text3.getText();
		String zwei= text5.getText();
		String vier= text6.getText();
		
		
		}
		}
	public void Receive(String msg )
	{
	text4.append(msg);	
	}
	
	public Gui() {
		c = getContentPane();
		c.setLayout(new FlowLayout());
		c.setBackground(Color.BLACK);
		text3 = new JTextArea("No User", 5, 40);
		button1= new JButton("Connect");
		text1 = new JLabel("USER im Chat:");
		text5 = new JTextField("Nickname",50);
		text1.setForeground(hell);
		text2 = new JLabel("Chat:");
		text2.setForeground(hell);
        text4 = new JTextArea(":", 15, 40);
		
		text6 = new JTextField("!",50);
		
		button2= new JButton("Send");
		jsp= new JScrollPane (text4);
        c.add(text1);
		c.add(text3);
		
		c.add(text5);
		c.add(button1);

		ButtonListener bL = new ButtonListener();
		button1.addActionListener(bL);
		c.add(text2);
		c.add(jsp);
		c.add(text6);
		c.add(button2);
		ButtonListener2 bsl = new ButtonListener2 ();
		button2.addActionListener(bsl);
		
		
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JFrame frame = new Gui ();
		
	//	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	frame.setSize(800, 800);
	//	frame.setVisible(true);
	}


}

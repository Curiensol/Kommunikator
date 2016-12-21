
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientGuia extends JFrame implements ActionListener {
	
	private JButton button1, button2;
	Container c;
	JLabel text1, text2;
	JTextArea text3;
	JTextArea text4;
	JTextField text5, text6;
	JScrollPane jsp;
	Color hell = new Color(250,250,250);
	String usrName;
	final static String HOST = "127.0.0.1";
	final static int PORT = 7575;
	
	static Socket socket;
	static BufferedReader socket_in, console_in;
	static PrintWriter socket_out, console_out;

	
	
	class ButtonListener implements ActionListener { //einloggen
		public void actionPerformed(ActionEvent e) {
			//socket = new Socket(HOST, PORT);
			//socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//	socket_out = new PrintWriter(socket.getOutputStream(), true);
			String zwei= text5.getText();
			usrName= text6.getText();
			socket_out.println("connect:" +usrName);    
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
	
	
	public ClientGuia() {
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
		String usrName ="Default";
		JFrame frame = new ClientGuia ();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(680, 500);
		frame.setVisible(true);
		

		console_in = new BufferedReader(new InputStreamReader(System.in));
		console_out = new PrintWriter(System.out, true);
		console_out.println("Client started. Connecting to server now ...");
		console_out.println("Enter Username:");
		
		try {
			usrName = console_in.readLine();
			socket = new Socket(HOST, PORT);
			socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			socket_out = new PrintWriter(socket.getOutputStream(), true);
			socket_out.println("connect:" +usrName);
			// read welcome message from server:
			console_out.println(socket_in.readLine());
			console_out.println(socket_in.readLine());
			console_out.println(socket_in.readLine());
			//console_out.println(socket_in.readLine());
			String messagec;
			while (true) {
				// prompt
				console_out.print("> ");
				console_out.flush();
				
				// send message:
				messagec = console_in.readLine();
					if (messagec.equals("stop"))
					{ 
					socket_out.println("disconnect");
					console_out.println(socket_in.readLine());
					break;
					}
					
				//}
				socket_out.println("message:"+usrName+":"+messagec);
				// read echo from server:
				console_out.println(socket_in.readLine());
				
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			console_out.println("Connection interrupted");
			
		}
		console_out.println("Client closed.");
	}
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("connect")) {

			// ... 

		} else if (command.equals("send")) {
			
			// ...
			
		} // else ...

	}
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

/** @author Cornelius Knapp */
public class ClientGUI {
	final static String HOST = "127.0.0.1";
	final static int PORT = 7575;
	
	static Socket socket;
	static BufferedReader socket_in, console_in;
	static PrintWriter socket_out, console_out;

	
	public static void main(String[] args) {
		console_in = new BufferedReader(new InputStreamReader(System.in));
		console_out = new PrintWriter(System.out, true);
		String usrName ="Default";
		console_out.println("Press c for console or g for Gui");
		String decision ="c";
		try {
			decision = console_in.readLine();
			} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
			}
		if (decision.equals("g"))
		{ 
		JFrame frame = new ClientGuia ();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(680, 500);
		frame.setVisible(true);
		
		}
		

		
		console_out.println("Client started. Connecting to server ...");
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

}

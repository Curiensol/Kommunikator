package pis.hue2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Vector;

/**
 * Nebenläufiger Chat-Server mit Steuerungskonsole zum Herunterfahren.
 * 
 * @author Cornelius Knapp pieces taken from Eugen Labun
 */
public class EchoServer implements Runnable {
	int port;
	ServerSocket serverSocket;
	volatile boolean started;
	volatile boolean interrupted;
	List<ConnectionHandler> clients = new Vector<ConnectionHandler>();

	EchoServer(int port) {
		this.port = port;
	}

	@Override
	public void run() {

		try {
			serverSocket = new ServerSocket(port);
			serverSocket.setSoTimeout(1000);
			started = true;

			while (!interrupted) {

				try {
					Socket clientSocket = serverSocket.accept();
					ConnectionHandler handler = new ConnectionHandler(clientSocket);
					clients.add(handler);
					new Thread(handler).start();
				} catch (SocketTimeoutException e) {
					// nothing to do here, the socket timeout has been set
					// for good responsibility to interrupt
				}

			}
			System.out.println("Closing connections ...");
			for (ConnectionHandler handler : clients) {
				handler.interrupted = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server stopped.");
	}

	public static void main(String[] args) throws Exception {
		int port = 7575;
		//String sword ="STOP";
		//int input=0;
		//String order= "";
		EchoServer server = new EchoServer(port);
		System.out.println("Starting server on port " + port + " ...");
		new Thread(server).start();
		do {
			Thread.sleep(100);
		} while (!server.started);

		String ip = server.serverSocket.getInetAddress().getHostAddress();
		if (ip.equals("0.0.0.0")) ip = "127.0.0.1";
		
		System.out.print("Server is running on " + ip);
		System.out.println(":" + server.serverSocket.getLocalPort());

		System.out.println("write Stop to shut down the server");
		
		/* byte buffer[] = new byte[80];	     
	     input = System.in.read(buffer, 0, 4);
	     order = new String(buffer, 0, input); 
		if (order.equals(sword)){**/
		System.in.read();
		System.out.println("Shutting down server ...");
		server.interrupted = true;
	}

}

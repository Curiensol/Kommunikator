package pis.hue2.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/** @author Cornelius Knapp pieces taken from Eugen Labun */
public class ConnectionHandler implements Runnable {

	Socket socket;
	volatile boolean interrupted;

	ConnectionHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		String userList[]= new String [3];
		int userSocket[] = {7576,7577,7578};
		int userCount=0; int b=0;int c=0;
		String command,content,name;
		try {
			System.out.println("ConnectionHandler started ...");
			socket.setSoTimeout(100);
			BufferedReader socket_in = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			PrintWriter socket_out = new PrintWriter(socket.getOutputStream(), true);
			//socket_out.println("server:Here is Chat-Server. Type 'stop' to disconnect");
			String message = null;
			String aMessage= null;
			while (!interrupted) {
				try {
					message = socket_in.readLine();
					
					 
					if (message == null) continue;
					if (message != null)
					{
						if (message.equals("disconnect"))
						{
						aMessage= message+":ok";
						socket_out.println(aMessage);
					
						break;
						}
					
						b= message.indexOf(":");
						command=message.substring(0, b);
						content=message.substring(b+1);
					
					if (command.contains("connect")) //neuer user 
					{
						if (userCount==3)aMessage="refused:too_many_users";
						//name zulang einfügen
						//namebesetzt einfügen
						
						else{
							userList[userCount]=content;
							userCount++;	
							socket_out.println("server:Here is Chat-Server. Type 'stop' to disconnect");
							socket_out.println("connect:ok");
							aMessage="Namelist";
							for (int i=0;i<userCount; i++){
							aMessage+=(":"+userList[0]);}
							
							socket_out.println(aMessage);
							}	
					
					}
					
					if (command.equals("message"))
					{
						c= content.indexOf(":");
						
						name=content.substring(0, c);
						content=content.substring(c+1);
						aMessage=name;
						aMessage+=" said:"+content; 
						socket_out.println(aMessage);
					}
					//socket_out.println(aMessage);
					}
						
				} catch (SocketTimeoutException e) {
					// nothing to do here, the socket timeout has been set
					// for good responsibility to interrupt
				}
			}
			userCount--;
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

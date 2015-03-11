import java.net.*;
import java.io.*;

public class ReadServer{
	
	private static final int LISTENING_PORT = 10000;
	
	public static void main(String[] args){
		
		//create a server socket
		ServerSocket server = new ServerSocket(LISTENING_PORT);
		Socket connection;
		PrintWriter output = new PrintWriter
		System.out.println("Listening on port " + LISTENING_PORT);
		
		//listen for a connection
		try{
			while(true){
				connection = server.accept();
				readClient(connection);
				connection.close();
			}
		}
		catch(IOException e){
			System.out.println("Error in attempting to connect to the client: " + e);
		}
		
	}
	
	static void readClient(Connection connection){
		
		Scanner output = new PrintWriter(connection.getOutputStream());
		while(true){
			output.println("Hello!");
			output.println("This");
			output.println("Is");
			output.println("Working!!!");
			output.flush();
		}
		
	}
	
}
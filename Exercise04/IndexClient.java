/**
This class is meant to be used in conjunction with IndexServer. Commands can be sent by the client to IndexServer, which has access to a certain directory.
Command can be "INDEX," which returns the name of the directory accessed by the server, or "GET <filename>," which returns the file in this directory,
provided it exists.
*/
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class IndexClient{
	
	private static final int LISTENING_PORT = 10000;
	
	public static void main(String[] args){
				
		System.out.println("Type in the name of a file");
		Scanner in = new Scanner(System.in);
		
		try{
			Socket connection = new Socket(InetAddress.getByName(null), LISTENING_PORT);
			Scanner input = new Scanner(new InputStreamReader(connection.getInputStream()));
			PrintWriter output = new PrintWriter(connection.getOutputStream());
			
			while(!input.hasNext("\n")){//Prints messages from server before request is required of client.
				System.out.println(input.nextLine());
			}
			output.println(in.nextLine());//User's request of the server.
			output.flush();
			
			while(true){
				System.out.println(input.nextLine());
			}
			
			//input.close();
		}
		catch(Exception e){
			System.out.println("Error occurred while trying to connect to the host: " + e);
		}
		
	}
	
}
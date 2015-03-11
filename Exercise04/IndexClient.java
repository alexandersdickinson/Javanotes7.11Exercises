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
				
		Scanner in = new Scanner(System.in);
		File copy = new File("/Users/alexandersdickinson/documents", "copy");
		String command;
		String line;
		
		try{
			Socket connection = new Socket(InetAddress.getByName(null), LISTENING_PORT);
			Scanner input = new Scanner(new InputStreamReader(connection.getInputStream()));
			PrintWriter output = new PrintWriter(connection.getOutputStream());
			PrintWriter copyWrite = new PrintWriter(new FileWriter(copy));
			
			System.out.println(input.nextLine());
			System.out.println(input.nextLine());
			System.out.println(input.nextLine());
			
			System.out.println("Type in the name of a file");
			command = in.nextLine();
			output.println(command);//User's request of the server.
			output.flush();
			
			while(input.hasNext()){				
				line = input.nextLine();
				System.out.println(line);
				copyWrite.println(line);
			}
			copyWrite.flush();
			copyWrite.close();			
			input.close();
		}
		catch(Exception e){
			System.out.println("Error occurred while trying to connect to the host: " + e);
			e.printStackTrace();
		}
		
	}
	
}
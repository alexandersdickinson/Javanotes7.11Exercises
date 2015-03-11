import java.net.*;
import java.io.*;

public class WriteClient{

	private static final int LISTENING_PORT = 10000;
	
	public static void main(String[] args){
		
		try{
			Socket connection = new Socket(InetAddress.getByName(null), LISTENING_PORT);
		}
		catch(Exception e){
			System.out.println("An error occurred attempting to connect to the host: " + e);
		}
		
	}

}
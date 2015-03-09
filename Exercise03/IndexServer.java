/**
Given the name of a directory, this program can be used to print the directory's contents. If the client sends the command, "INDEX," the list of files in
the chosen directory will be sent to the client's outputstream. If the command is GET <fileName>, the program checks to see if the file exists, and then
sends the contents of that file.
*/

import java.net.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class IndexServer{
	
	private static final int LISTENING_PORT = 10000;
		
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		File directory;
		
		//Create directory object.
		if(args.length == 1){
			directory = new File(args[0]);
		}
		else{
			System.out.println("No name for the directory was provided as a command line argument.");
			System.out.println("Please type in the name of the desired directory.");
			directory = new File(scanner.nextLine());
			scanner.close();
		}
		
		if(directory.isDirectory()){
			
			if(!directory.exists()){
				System.out.println("This directory does not exist.");
				System.out.println("Goodbye!");
				return;
			}
		
		}
		else{
			System.out.println("The chosen file is not a directory.");
			System.out.println("Goodbye!");
			return;
		}
	
		try{
		
			ServerSocket server = new ServerSocket(LISTENING_PORT);
		
			while(true){
				System.out.println("Listening on port " + LISTENING_PORT);
				Socket connection = server.accept();
				directoryInfo(connection, directory);
			}
		
		}
		catch(IOException e){
			System.out.println("Error has occurred in connecting with the client: " + e);
		}
		
	}
	
	/**
		This subroutine waits for a command from the client and provides the appropriate information about the directory specified in server's
		command-line argument.
		Precondition: There must be a connection to a client. A file that is a directory and that exists must be given as well.
		Postcondition: Either a list of files in the chosen directory will be sent to the client, or the name of the directory itself,
		depending on the command given by the client.
		@param connection The client socket returned by server.accept() in main().
		@param directory The directory specified in the command line, that commands from the client are applied to.
	*/
	static void directoryInfo(Socket connection, File directory){
		
		String[] files = directory.list();//List of the files in the chosen directory.
		File txtFile;
		String fileName;
		String inputToken;
				
		try{
			
			System.out.println("Now connected to " + connection.getInetAddress().toString());
			PrintWriter output = new PrintWriter(connection.getOutputStream());
			output.println("You have connected to the host");
			output.println("Please type in a command.");
			output.println("The chosen directory is " + directory.getName());
			output.println();
			output.flush();
			Scanner input = new Scanner(connection.getInputStream());
			inputToken = input.next().toUpperCase();
			
			if(inputToken.equals("INDEX")){
				//send list of files in directory through connection's outputstream.				
				for(String file:files){
					output.println(file);
					output.flush();
				}
			}
			else if(inputToken.equals("GET")){
				//send confirmation that file exists, and if so, the contents of that file.
				input.useDelimiter("[\\<\\>]");
				input.next();//get rid of "GET"
				fileName = input.next();
				
				if(Arrays.asList(files).contains(fileName)){//Check if file exists in the chosen directory.
					txtFile = new File(fileName);
				}
				else{
					output.println("This file is not in the chosen directory.");
					return;
				}
				
				input.reset();//use whitespace delimiter again
					
				try(Scanner fileScan = new Scanner(new FileReader(txtFile))){
					
					while(fileScan.hasNext()){
						output.println(fileScan.nextLine());
					}
					output.println();
					
				}
				catch(IOException e){
					output.println("An error occurred in finding the chosen file: " + e);
				}
				
			}
			else{//Command is not of the correct format.
				output.println("Command is invalid.");
				output.flush();
			}
			
			connection.close();
			
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
		
	}
	
}
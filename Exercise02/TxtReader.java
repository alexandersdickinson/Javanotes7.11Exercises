/**
	This class reads files (assumed to be .txt) specified by the user in the command line and prints the number of lines for each file.
*/

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TxtReader{
	
	public static void main(String[] args){
				
		String line;
		int counter;
		
		//check if args has any elements
		if(args.length == 0){
			System.out.println("No files were specified.");
			return;
		}
		
		for(int i = 0; i < args.length; i++){//for each file
			
			counter = 0;
			line = "";
			
			try(FileReader file = new FileReader(args[i]); BufferedReader in = new BufferedReader(file)){
								
				while(line != null){
					line = in.readLine();
					counter ++;
				}
				
				System.out.println(args[i] + " has " + counter + " lines.");
				
			}
			catch(IOException e){
				System.out.println(e.getMessage());
				continue;
			}
			
		}
		
	}
	
}
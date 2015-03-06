import java.io.File;
import java.util.Scanner;

/**
 * This program lists the files in a directory specified by
 * the user.  The user is asked to type in a directory name.
 * If the name entered by the user is not a directory, a
 * message is printed and the program ends.
 */
public class DirectoryList {


    public static void main(String[] args) {

        String directoryName;  // Directory name entered by the user.
        File directory;        // File object referring to the directory.
        String[] files;        // Array of file names in the directory.
        Scanner scanner;       // For reading a line of input from the user.

        scanner = new Scanner(System.in);  // scanner reads from standard input.

        System.out.print("Enter a directory name: ");
        directoryName = scanner.nextLine().trim();
        directory = new File(directoryName);

        if (directory.isDirectory() == false) {
            if (directory.exists() == false)
                System.out.println("There is no such directory!");
            else
                System.out.println("That file is not a directory.");
        }
        else {
            filePrint(directory);
        }

    } // end main()
	
	/**
		This subroutine prints out all of the subfiles of a directory. This means all files of all subdirectories are printed.
		Precondition: A root directory.
		Postcondition: A printed list of all files and directories subordinate to the root directory.
		
	*/
	private static void filePrint(File file){
				
		//print all child files of directory
		String[] files;
		files = file.list();
		File subFile;
		
		if(file.isDirectory()){
			System.out.println(file);
			for(int i = 0; i < files.length; i++){
				subFile = new File(file, files[i]);
				filePrint(subFile);
			}
		}
		else{
			System.out.println(file);
		}
		
	}

} // end class DirectoryList
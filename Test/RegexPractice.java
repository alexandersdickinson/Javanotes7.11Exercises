import java.util.Scanner;

public class RegexPractice{
	
	public static void main(String[] args){
		
		String string;
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("[\\<\\>]");
		
		System.out.println(scanner.next());
		
	}
	
}
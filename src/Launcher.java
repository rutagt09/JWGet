import java.util.Scanner;

public class Launcher {
	
	private static DownloadDetails d;
	
	public static void main(String args[]){
		
		if (args.length == 0 ){
			Scanner kb = new Scanner(System.in);
			String input;
			System.out.printf("Input directory URL: ");
			input = kb.next();
			JWget j = new JWget(input);
			kb.close();
		}
		else if (args.length == 1){
			JWget j = new JWget(args[0]);
		}
		else {
			System.out.println("Only argument should be the URL.");
		}
	}
}

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class JWget {
	private String dLocation; // Download Location
	private String fPath; // File Path
	
	public JWget(String x){
		// specify target location, save location
		System.out.println("Enter a name for the save folder: ");
		Scanner kb = new Scanner(System.in);
		String folderName = kb.nextLine();
		this.dLocation = x;
		fPath = new File("").getAbsolutePath();
		fPath += "/" + folderName + "/";
		DownloadList dl = new DownloadList();
		System.out.println("Output location: " + fPath);
		new File(fPath).mkdir();
		
		// generate download list from target location
		dl.genList(dLocation);
		Downloader d = new Downloader(fPath);
		
		// while downlaod list is not empty, download the next file
		while (!dl.isEmpty()){
			System.out.println("Current File size: " + toMB(dl.tryGetFileSize(dl.getCurrent().getURL())) + "MB. " + dl.size() + " to go.");
			d.download(dl.pop());
		}
		
		// open the directory that the files were downloaded to
		try {
			Desktop.getDesktop().open(new File(fPath));
		} catch (IOException e) {
			System.out.println("Could not open path.");
		}
		
		// print completion message and wait for user input to quit
		System.out.println("Download completed. Press enter to close.");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	    try { in.readLine(); } 
	    catch (IOException e) { System.out.println("Could not leave console open."); }
	}
		
	// convert bytes to MB with max 2 trailing decimal
	public double toMB(int x){
		x = x - (x%10000);
		double temp = x;
		return temp/1000000; 
	}
}

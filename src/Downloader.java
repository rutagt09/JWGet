import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class Downloader {
	
    private InputStream is = null;
    private File file;
    private String fileName;
    private int readByte;
    private String filePath;
    
	public Downloader(){ }	
	
	public Downloader(String x){
		this.filePath = x;
		
	}
	
	public void download(URLObject url){
	      // prevent 403 and get filename
	      System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 5.1; rv:10.0.2) Gecko/20100101 Firefox/10.0.2");
	      this.fileName = url.getName();
    
	      // Create input stream 
	      try {
	         this.is = url.getURL().openStream();

	         // Create file if it doesn't exist (OVERWRITES BY DEFAULT) 
	         this.file = new File(filePath + fileName);
	         if (!this.file.exists()) {
					this.file.createNewFile();
				}
	         
	         // Create output stream and write
	         try (FileOutputStream os = new FileOutputStream(file)){
	        	 byte[] bytes = new byte[1024];
	             while ((this.readByte = this.is.read(bytes)) != -1) {
	     			os.write(bytes, 0, this.readByte);
	     		}
	            os.close();
	         } catch (Exception e) {
	        	 System.out.println("Error outputting file "+ fileName);
	         }
	         
	      // catch other exceptions   
	      } catch (MalformedURLException mue) {

	         System.out.println("MalformedURLException parsing URL.");
	         mue.printStackTrace();
	         System.exit(1);

	      } catch (IOException ioe) {

	         System.out.println("IOException occured.");
	         ioe.printStackTrace();
	         System.exit(1);

	      } finally {

	         try {
	            this.is.close();
	         } catch (IOException ioe) {
	            System.out.println("Error closing input stream");
	         }
	      }  
	   }

}

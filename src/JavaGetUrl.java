//------------------------------------------------------------//
//  JavaGetUrl.java:                                          //
//------------------------------------------------------------//
//  A Java program that demonstrates a procedure that can be  //
//  used to download the contents of a specified URL.         //
//------------------------------------------------------------//
//  Code created by Developer's Daily                         //
//  http://www.DevDaily.com                                   //
//------------------------------------------------------------//

import java.io.*;
import java.net.*;

public class JavaGetUrl {

   public static void main (String[] args) {
	   URLObject u = new URLObject("http://www.jarnot.com/misc/roms/gba/Atari%20Anniversary%20Advance%20(USA).gba");
	   downloader(u);
   }
   
   
   public static void downloader(URLObject url){
      URL u;
      InputStream is = null;
      File file;
      String urlOrig;
      String fileName;
      int readByte;
      
      // spoof webclient so that sites with download protection wont throw a 403
      System.setProperty("http.agent", "Mozilla/5.0 (Windows NT 5.1; rv:10.0.2) Gecko/20100101 Firefox/10.0.2");
      
      // get URL and filename
      urlOrig = url.getURLString();
      fileName = url.getName();
      System.out.println("Working");
      try {
			fileName = URLDecoder.decode(fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
      // Create input stream 
      try {
         u = new URL(urlOrig);
         is = u.openStream();         // throws an IOException

         // Create file if it doesn't exist    
         file = new File(fileName);
         if (!file.exists()) {
				file.createNewFile();
			}
         
         // Create output stream and write
         try (FileOutputStream os = new FileOutputStream(file)){
        	 byte[] bytes = new byte[1024];
             while ((readByte = is.read(bytes)) != -1) {
     			os.write(bytes, 0, readByte);
     		}
            os.close();
         } catch (Exception e) {
        	 System.out.println("Error outputting file "+ fileName);
         }
         
        
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
            is.close();
         } catch (IOException ioe) {
            System.out.println("Error closing input stream");
         }
      }
   System.out.println("Done");
   }
}
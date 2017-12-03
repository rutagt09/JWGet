import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadList {
	private List<URLObject> list;
	
	DownloadList(){
		this.list = new ArrayList<URLObject>();
	}
	
	public boolean isEmpty(){ return this.list.isEmpty(); }
	public URLObject getCurrent(){ return this.list.get(0); }
	public URLObject pop(){ return this.list.remove(0); }
	public int size(){ return this.list.size(); }
	public void add(URLObject u){ list.add(u); }
	public void remove(int x){ this.list.remove(x);}
	
	// Pulled from a random comment on stackoverflow LUL
	public int tryGetFileSize(URL url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("HEAD");
            conn.getInputStream();
            return conn.getContentLength();
        } catch (IOException e) {
            return -1;
        } finally {
            conn.disconnect();
        }
    }
	
	@SuppressWarnings("deprecation")
	public void genList(String string) {
		  URL u;
	      InputStream is = null;
	      DataInputStream dis;
	      String s;
	      String html;
	      try {
	         u = new URL(string);
	         html="";
	         is = u.openStream();
	         // read webpage and add to string "html"
	         dis = new DataInputStream(new BufferedInputStream(is));
	         while ((s = dis.readLine()) != null) {
	            html+=s;
	         }
	         
	        List<String> results = new ArrayList<String>();
			String y ="<a href=\"";
			String z ="\">";
			String sample = "";

			// while the remainding string contains <a href=\" and also \"</a> at a later index
			while (html.contains(y) && html.substring(html.indexOf(y)).contains(z)){
				int iy = html.indexOf(y)+y.length();
				int iz = html.substring(iy).indexOf(z)+iy;
				sample = html.substring(iy,iz);
				results.add(sample);
				html = html.substring(iz+z.length());
			}

			for(String tstring : results){
				if (tstring.contains(".") && !tstring.contains("..")){ // only grab files
					if (!tstring.contains(u.toString())){
						URLObject tempu = new URLObject(u.toString() + tstring);
						list.add(tempu);
					}
					else {
						list.add(new URLObject(tstring));
					}
				}
			}

	      } catch (MalformedURLException mue) {

	         System.out.println("MalformedURLException happened.");
	         mue.printStackTrace();
	         System.exit(1);

	      } catch (IOException ioe) {

	         System.out.println("IOException happened.");
	         ioe.printStackTrace();
	         System.exit(1);

	      } finally {

	         try {
	            is.close();
	         } catch (IOException ioe) {
	           
	         }

	      } 
	};
}

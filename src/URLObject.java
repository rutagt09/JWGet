import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

// TODO: Add in object download progress information (IE filesize)

public class URLObject {
	private String urlString;
	private URL url;
	private String name;
	
	public URLObject(){
		this.url = null;
		this.urlString = null;
		this.name = "(blank)";
	}
	
	public URLObject(String s){
		this(s, false);
	}
	
	public URLObject(String s, boolean b){
		if (s.contains("&amp;")){
			s= s.replace("&amp;", "&");
		}
		this.stringToURL(s);
		this.setName(s);
		this.urlString = s;
	}
	
	public URLObject(String s, String n){
		if (s.contains("&amp;")){
			s= s.replace("&amp;", "");
		}
		this.stringToURL(s);
		this.name = n;
		this.urlString = s;
	}
	
	public boolean stringToURL(String s){
		try {
			this.url = new URL(s);
			return true;
		} catch (MalformedURLException e) {
			System.out.println("Bad URL");
			return false;
		}
	}
	
	public boolean isAFile(){
		return this.name.contains(".");
	}
	
	public void setName(String s){
		this.name = s.substring(s.lastIndexOf("/")+1, s.length());
		try {
			this.name = URLDecoder.decode(this.name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error encoding name.");
		}
	}
	
	
	public String getName(){ return this.name; }
	public String getURLString(){ return this.urlString; }
	public URL getURL(){ return this.url; }

}

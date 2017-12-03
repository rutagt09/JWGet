import java.util.ArrayList;

public class DownloadDetails {
	private ArrayList<String> list;
	private String types;
	private int maxSize;
	
	public DownloadDetails(){ }
	
	// Methods for Links
	public void addLink(String t){ list.add(t); }
	
	public void removeLink(){ list.remove(0); }
	
	public String getLink(){
		if (!this.list.isEmpty()){ return this.list.get(0); }
		else return "empty";
	}
	
	// Methods for fileSize
	public void setMaxSize(String x){ 
		int temp = 0;
		for (int i = 0; i < x.length(); i++){
			if (isNum(x.charAt(i))){
				temp += ((int)x.charAt(i) - 48);
				if (i+1 != x.length()){
					temp*=10;
				}
			}
			else temp = -99999999;
		}
		this.maxSize = temp; 
	}
	
	private boolean isNum(char charAt) {
		if (charAt >= '0' && charAt <= '9'){
			return true;
		}
		return false;
	}

	public int getMaxSize(){ return this.maxSize; }
	
	// Methods for specifying types
	public void setTypes(String x){ this.types = x; }
	
	public String getTypes(){ return this.types; }
}

import java.io.File;

public class tester {
	public static void main(String args[]){
		String x = new File("").getAbsolutePath();
		x += "/testing/";
		new File(x).mkdir();
	}
}

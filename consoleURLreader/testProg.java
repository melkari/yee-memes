import java.util.Scanner;

public class testProg {
	public static void main(String[] args) {
		testProg tp = new testProg();
		tp.urlReader();
	}
	
	public void urlReader() {
		try {
			System.out.println("Gibe URL: ");
			Scanner kb = new Scanner(System.in);
			String url = kb.nextLine();
			HttpJuttu myHJ = new HttpJuttu(url);
			myHJ.start();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
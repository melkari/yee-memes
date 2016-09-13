import java.util.Scanner;

public class HelloWorldApp {
    public static void main(String[] args) {
		try {
			Thread1 yee = new Thread1();
			yee.start();
			while(true) {
				Scanner kb = new Scanner(System.in);
				String komento = kb.nextLine();
				
				if(komento.equals("start"))
				{
					Thread1 yee2 = new Thread1();
					Thread.sleep(1000);
					yee2.start();
				}
				else if(komento.equals("stop"))
				{
					Thread.sleep(1000);
					yee.interrupt();
				}			  
			} 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
import java.util.Scanner;

public class HelloWorldApp {
    public static void main(String[] args) {
		try {
			Thread1 currentThread = new Thread1();
			while(true) {
				Scanner kb = new Scanner(System.in);
				String komento = kb.nextLine();			
				if(komento.equals("start"))
				{
					Thread1 yee = new Thread1();
					currentThread = yee;
					yee.start();
				}
				else if(komento.equals("stop"))
				{
					currentThread.interrupt();				
				}			  
			} 
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
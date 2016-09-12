import java.util.Scanner;

public class TestiOhjelma {
	public static void main(String args[]) {

		ThreadJuttu R1 = new ThreadJuttu("Thread-1");
		R1.start();
		try {
			while(true) {
				Scanner kb = new Scanner(System.in);
				String komento = kb.nextLine();
				
				if(komento.equals("start"))
				{
					Thread.sleep(1000);
					R1.resume();
				}
				else if(komento.equals("stop"))
				{
					Thread.sleep(1000);
					R1.suspend();
				}			  
			} 
		}catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		
	}   
}
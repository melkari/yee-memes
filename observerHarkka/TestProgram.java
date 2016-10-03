import java.util.Scanner;

public class TestProgram implements ThreadThing.StatusInterface{
	
	public void startThreads() {
		ThreadThing threadthing = new ThreadThing(this);
		threadthing.start();
	}
	
	public static void main(String[] args) {
		TestProgram testProgram = new TestProgram();
		System.out.println("'s' to start a new thread.");
		Scanner sc = new Scanner(System.in);
		String cmd;
		while(true) {
			cmd = sc.nextLine();
			if(cmd.equals("s")) {
				testProgram.startThreads();
			}
		}
	}
	
	@Override
	public void reportStatus(String progress) {
		System.out.println(progress);
	}
}
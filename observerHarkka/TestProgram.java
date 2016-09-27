import java.util.Scanner;

public class TestProgram implements ThreadThing.StatusInterface{
	static ThreadThing threadthing = new ThreadThing(this);
	public static void main(String[] args) {
		try {
			threadthing.start();
		} catch(Exception e) {
			
		}
	}
	
	@Override
	public void reportStatus(String progress) {
		System.out.println(progress);
	}
}
import java.util.Date;

public class ThreadThing extends Thread {
	private String progress;
	
	public interface StatusInterface {
		public void reportStatus(String progress);
	}

	StatusInterface observer;
	public ThreadThing(StatusInterface cb) {
		this.observer = cb;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i <100; i+=10){
				Thread.sleep(3000);
				String date = new Date().toString();
				progress = date + ": " + "On Progress: " + i;
				observer.reportStatus(progress);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getStatus() {
		return progress;
	}
	
	public void startThread(String givenname) {
		//what the fugs am i doing
	}
}
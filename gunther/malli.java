import java.util.Date;
import java.io.*;
import java.util.Scanner;

class Thread1 extends Thread {
	public void run() {
		try{
			while(true){
				String date = new Date().toString();
				System.out.println(date + "GUNTHER TULE TAKAISIN!");
				sleep(5000);
			}
		}
		catch(Exception e) {
			System.out.print("thread interrupted");
		}
	}
}
import java.net.*;
import java.io.*;

class HttpJuttu extends Thread {
	private String url;
	private String charset = "UTF-8";
	HttpJuttu(String givenurl) {
		url = givenurl;
	}
	
	public void run() {
		try {
			URL myURL = new URL(url);
			URLConnection myconn = myURL.openConnection();
			myconn.connect();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
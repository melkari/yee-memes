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
			URLConnection yc = myURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
			in.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
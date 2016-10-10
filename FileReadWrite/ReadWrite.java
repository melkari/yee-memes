import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class ReadWrite extends Thread {
	ArrayList<String> filecontent = new ArrayList<String>();
	String txt = null;
	
	ReaderInterface readerInterface;
	public interface ReaderInterface {
		public void readFile(ArrayList<String> content);
	}
	
	public ReadWrite(ReaderInterface ri) {
		this.readerInterface = ri;
	}
	
	public void run() {
		try {
			if(txt != null) {
				Files.write(Paths.get("malli.txt"), txt.getBytes(), StandardOpenOption.APPEND);
			} else {
				for(String line : Files.readAllLines(Paths.get("malli.txt"))) {
					filecontent.add(line);
				}
				readerInterface.readFile(filecontent);
				System.out.println("Gibe new text: ");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void setText(String giventxt) {
		txt = "\n" + giventxt;
		start();
	}
}
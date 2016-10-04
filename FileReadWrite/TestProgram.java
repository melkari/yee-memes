import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TestProgram implements ReadWrite.ReaderInterface{
	ReadWrite rw = new ReadWrite(this);
	public void startReadThread() {
		rw.start();
	}
	
	public static void main(String args[]) {
		TestProgram testProgram = new TestProgram();
		testProgram.startReadThread();
		System.out.println("Anna uusi teksti: ");
		Scanner sc = new Scanner(System.in);
		String newtext = sc.nextLine();
		testProgram.startWriteThread(newtext);
	}
	
	public void startWriteThread(String newtext) {
		rw.setText(newtext);
	}
	
	@Override
	public void readFile(ArrayList<String> content) {
		System.out.println("Text written on file: ");
		for(String line : content ) {
            System.out.println(line);
        }
	}
}
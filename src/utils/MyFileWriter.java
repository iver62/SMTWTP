package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {
	
	public static void writeData(String filename, int[] tab) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < tab.length; i++) {
				bw.write(" " + tab[i]);
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeData(String filename, double d, long t) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			bw.write(" " + d + " " + t);
			bw.newLine();
			bw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

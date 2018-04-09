package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	public static void info(String input) {
		try {
			BufferedWriter Log = new BufferedWriter(new FileWriter("Log.txt"));
			Log.newLine();
			System.out.println("INFO: " + input);
			Log.write("INFO: " + input);
			Log.flush();  
			Log.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

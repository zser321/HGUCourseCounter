package edu.handong.analysis.utils;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;

public class Utils {
	public static ArrayList<String> getLines(String file,boolean removeHeader){
		
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			Scanner inputStream = new Scanner(new File(file));
			String line = inputStream.nextLine();
			
			while(inputStream.hasNextLine())
			{
				line = inputStream.nextLine();
				data.add(line);
			}
			
		}
		catch(FileNotFoundException e ){
			System.out.println("The file path does not exist. Please check your CLI argument! ");
			System.exit(0);
		}
		
		return data;
	}
	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		String fileName = targetFileName;
		PrintWriter outputStream = null;
		
		try {
			File file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			outputStream = new PrintWriter(fileName);
			
		}
		catch(FileNotFoundException e)
		{
			
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		for(String line : lines) {
			outputStream.println(line);
		}
		outputStream.close();
	}
	
}

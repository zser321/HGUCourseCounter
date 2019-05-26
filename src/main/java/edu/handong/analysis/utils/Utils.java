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
		//String directoryPath = targetFileName.split("\")[targetFileName.split(",").length];
		try {
			outputStream = new PrintWriter(fileName);
			/*
			File theDir = new File(directoryPath);
			if (!theDir.exists()) theDir.mkdirs();
			*/
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The file path does not exist. Please check your CLI argument! ");
			System.exit(0);
		}
		
		for(String line : lines) {
			outputStream.println(line);
		}
		outputStream.close();
	}
	
}

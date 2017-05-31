/*LZW Decompression Algorithm:
 * Implemented by: Sharath Chandra Bagur Suryanarayanaprasad
 * Student ID: 800974802
 * Email ID: sbagursu@uncc.edu  
 */



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class LZWDecompression {
	public static void main(String[] args) {
		int MaxTableSize,reader, asciiTracker = 0, bitLength, receivedCode;
		String string = "";
		String newString;
		String stringFromFile = null;
		ArrayList<String> table = new ArrayList<String>();
		ArrayList<String> charFromEncodedString = new ArrayList<String>();
		BufferedReader br = null;
		//Generate table with standard list of ASCII characters
		while(asciiTracker!=256){
			table.add(String.valueOf((char) asciiTracker));
			asciiTracker++;
		}
		//Read content from file and store the data in a string buffer to be used later.
		StringBuilder textStreamFromFile = new StringBuilder();
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), Charset.forName("UTF-16BE")));
			while((reader = br.read())!= -1){
				textStreamFromFile.append((char) reader);
			}	
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: File Could not be read.");
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		stringFromFile = textStreamFromFile.toString(); //get entire encoded string from encoded file 
		
		bitLength = Integer.parseInt(args[1]); 	//accept encoding bits from user
		if(bitLength<9 || bitLength>16){
			System.out.println("Please use bit Length in the specified range of 9-16");
			System.exit(0);
		}
		MaxTableSize = (int) Math.pow(2, bitLength); //set table size to 2^bit_length
		
		System.out.println("-------------LZW Decompression Algorithm------------");
		
		stringFromFile = textStreamFromFile.toString();
		int initialIndex = (int) stringFromFile.charAt(0);
		string = table.get(initialIndex);
		charFromEncodedString.add(string);
		int codesToReceiveCounter = 1;
		
		while(codesToReceiveCounter < stringFromFile.length()){
			receivedCode =  (int) stringFromFile.charAt(codesToReceiveCounter);
			if(receivedCode < asciiTracker){
				newString = table.get(receivedCode);
				
			}
			else{
				newString = string + string.charAt(0);
				
			}
			charFromEncodedString.add(newString);
			if(asciiTracker < MaxTableSize){
				table.add(string+newString.charAt(0));
				asciiTracker++;
				codesToReceiveCounter++;
			}
			string = newString;
			
			
		}
		StringBuilder printDecoded = new StringBuilder();
		for(String s: charFromEncodedString){
			printDecoded.append(s);
		}
		
		System.out.println("\n\nDecoded String: " +printDecoded +"\n\n");
		System.out.println("----------------------------------------------------");
		BufferedReader writeToFile = new BufferedReader(new StringReader(printDecoded.toString()));
		try {
			PrintWriter writer = new PrintWriter(new FileWriter(args[0].split("\\.")[0] + "Decoded.txt"));
			writeToFile.lines().forEach(lineValue -> writer.println(lineValue));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

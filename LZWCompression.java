/* LZW Compression Algorithm:
 * Implemented by: Sharath Chandra Bagur Suryanarayanaprasad
 * Student ID: 800974802
 * Email ID: sbagursu@uncc.edu  
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class LZWCompression {
	public static void main(String[] args) {
		int MaxTableSize,reader, asciiTracker = 0, bitLength;
		String string = "";
		char[] charsFromFile;
		ArrayList<Integer> codeTable = new ArrayList<Integer>();
		ArrayList<String> table = new ArrayList<String>();
		ArrayList<Character> charactersArray = new ArrayList<Character>();
		//Generate table with standard list of ASCII characters
		while(asciiTracker!=256){
			table.add(String.valueOf((char) asciiTracker ));
			asciiTracker++;
		}
		//Read content from file and store the data in a string buffer to be used later.
		StringBuilder textStreamFromFile = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
			while((reader = br.read())!= -1){
				textStreamFromFile.append((char) reader);
			}
			br.close();		
		} catch (FileNotFoundException e) {
			System.out.println("Error: File Not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: File Could not be read.");
			e.printStackTrace();
		}
		bitLength = Integer.parseInt(args[1]); 	//accept encoding bits from user
		if(bitLength<9 || bitLength>16){
			System.out.println("Please use bit Length in the specified range of 9-16");
			System.exit(0);
		}

		System.out.println("-------------------LZW Compression Algorithm-------------------\n");
		System.out.println("Input from file:" +textStreamFromFile.toString()+ "\n");
		
		charsFromFile = textStreamFromFile.toString().toCharArray();  
		//generate an array containing the individual characters from the input
		for (char c : charsFromFile) {
			if (c != '\r')
				charactersArray.add(c);
		}
		
		
		MaxTableSize = (int) Math.pow(2, bitLength); //set table size to 2^bit_length
		
		//algorithm for encoding
		for(char c : charactersArray){
			if(table.contains(string + c)){
				string+=c;
			}
			else{
				codeTable.add(table.indexOf(string));
				if(asciiTracker < MaxTableSize){
					table.add(string+c);
				}
				string = String.valueOf(c);	
			}
		}
		codeTable.add(table.indexOf(string));
		
		System.out.println("Encoded Values:"); //Print the encoded values
		StringBuilder codesToLzwFile = new StringBuilder();
		for (int codes : codeTable) {
			System.out.println(codes);
			codesToLzwFile.append((char) codes);
		}
		System.out.println("---------------------------------------------------------------\n");
		//enter the encoded values to file and generate file in the specified .lzw format
		try {
			FileOutputStream os = new FileOutputStream(new File(args[0].split("\\.")[0] + ".lzw"));
			try {
				os.write(codesToLzwFile.toString().getBytes("UTF-16BE"));
				os.close();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}

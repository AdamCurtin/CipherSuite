
//
// Purpose: utility class with static methods to encapsulate the encoding
// and decoding logic



package caesarcipherencoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CipherUtility {
	
	
	/**
	 * Prompts the user to enter text to encode or decode and returns the provided text.
	 * @return The input text entered by the user
	 */
	public static String getInputText() {
        System.out.print("Enter the text to encode/decode: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    } // end getInputText method
	
	/**
     * Prompts the user to enter the shift amount, and returns the provided shift amount
     * @return The shift amount entered by the user
     */
	public static int getShiftAmount() {
        System.out.print("Enter the shift amount: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    } // end getShiftAmount method

	// get file name from user
    public static String getOutputFileName() {
        System.out.print("Enter the output file name: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    } // end getOutputFileName method
		
    // Get input text from a file
    public static String readFromFile(String fileName) {
        StringBuilder inputText = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                inputText.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        return inputText.toString();
    } // end readFromFile method
	
    // encode the text
	public static String caesarEncode (String input, int shift) {
	        StringBuilder encodedText = new StringBuilder();
	        for (char c : input.toCharArray()) {
	            if (Character.isLetter(c)) {
	                char base = Character.isUpperCase(c) ? 'A' : 'a';
	                char encodedChar = (char) ((c - base + shift) % 26 + base);
	                encodedText.append(encodedChar);
	            } else {
	                encodedText.append(c);
	            }
	        }
	        return encodedText.toString();
	} // end encodeText

	 // decode text
	public static String caesarDecode(String input, int shift) {
	     return caesarEncode(input, 26 - shift); // Decoding is the reverse of encoding with a shift of 26 - original shift
	} // end decodeText
	 
	public static void writeToFile(String text, String fileName) {
	        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
	            writer.println(text);
	        } catch (FileNotFoundException e) {
	            System.out.println("Error writing to file: " + fileName);
	        }
	} // end writeToFile method
	
	// ask user if they want to print or not
	public static boolean getPrintToFileChoice() {
		    System.out.print("Do you want to print the encoded text to a file? (y/n): ");
		    Scanner scanner = new Scanner(System.in);
		    String choice = scanner.nextLine().trim().toLowerCase();
		    return choice.equals("y") || choice.equals("yes");
	} // end getPrintToFileChoice
	
	
	// Substitution Encoding and decoding methods
	public static String substitutionEncode(String input) {
	       // Define your substitution cipher mapping
	       // For example, let's use a simple letter shift of 3
	       String substitutionMap = "defghijklmnopqrstuvwxyzabc";

	       StringBuilder encodedText = new StringBuilder();
	       for (char c : input.toCharArray()) {
	           if (Character.isLetter(c)) {
	               char base = Character.isUpperCase(c) ? 'A' : 'a';
	               int index = c - base;
	               char encodedChar = substitutionMap.charAt(index);
	               encodedText.append(encodedChar);
	           } else {
	               encodedText.append(c);
	           }
	       }
	       return encodedText.toString();
	   } // end subEncode Method
	 

	   public static String substitutionDecode(String input) {
	       String substitutionMap = "defghijklmnopqrstuvwxyzabc";
	       String reverseSubstitutionMap = "abcdefghijklmnopqrstuvwxyz";

	       StringBuilder decodedText = new StringBuilder();
	       for (char c : input.toCharArray()) {
	           if (Character.isLetter(c)) {
	               char base = Character.isUpperCase(c) ? 'A' : 'a';
	               int index = substitutionMap.indexOf(Character.toLowerCase(c));
	               char decodedChar = reverseSubstitutionMap.charAt(index);
	               if (Character.isUpperCase(c)) {
	                   decodedChar = Character.toUpperCase(decodedChar);
	               }
	               decodedText.append(decodedChar);
	           } else {
	               decodedText.append(c);
	           }
	       }
	       return decodedText.toString();
	   } // end substitutionDecode Method
	   
	   
	   // Begin Transposition Coding methods
	   
	   public static String transpositionEncode(String input) {
	        // Transposition Cipher logic for encoding
	        int rows = (int) Math.ceil(Math.sqrt(input.length()));
	        int cols = (int) Math.ceil((double) input.length() / rows);

	        char[][] grid = new char[rows][cols];
	        int index = 0;

	        // Fill the grid with the input characters
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                if (index < input.length()) {
	                    grid[i][j] = input.charAt(index++);
	                } else {
	                    grid[i][j] = ' ';
	                }
	            }
	        }

	        // Read the encoded text row by row
	        StringBuilder encodedText = new StringBuilder();
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                encodedText.append(grid[i][j]);
	            }
	        }

	        return encodedText.toString();
	    } // end transpositionEncode method

	    public static String transpositionDecode(String input) {
	        // Transposition Cipher logic for decoding
	        int cols = (int) Math.ceil(Math.sqrt(input.length()));
	        int rows = (int) Math.ceil((double) input.length() / cols);

	        char[][] grid = new char[rows][cols];
	        int index = 0;

	        // Fill the grid with the input characters
	        for (int i = 0; i < cols; i++) {
	            for (int j = 0; j < rows; j++) {
	                if (index < input.length()) {
	                    grid[j][i] = input.charAt(index++);
	                } else {
	                    grid[j][i] = ' ';
	                }
	            }
	        }

	        // Read the decoded text column by column
	        StringBuilder decodedText = new StringBuilder();
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                decodedText.append(grid[i][j]);
	            }
	        }

	        return decodedText.toString();
	    } // end transposition Decode method
	    
	    // Begin Vigenere Cipher Methods
	    // Encode text using Vigenère Cipher
	    public static String vigenereEncode(String input, String key) {
	        StringBuilder encodedText = new StringBuilder();
	        int inputLength = input.length();
	        int keyLength = key.length();

	        for (int i = 0; i < inputLength; i++) {
	            char inputChar = input.charAt(i);
	            char keyChar = key.charAt(i % keyLength); // Repeating the key if it's shorter than the input

	            if (Character.isLetter(inputChar)) {
	                char base = Character.isUpperCase(inputChar) ? 'A' : 'a';
	                int inputIndex = inputChar - base;
	                int keyIndex = keyChar - base;

	                char encodedChar = (char) ((inputIndex + keyIndex) % 26 + base);
	                encodedText.append(encodedChar);
	            } else {
	                encodedText.append(inputChar);
	            }
	        }

	        return encodedText.toString();
	    } // end encode

	    // Decode text using Vigenère Cipher
	    public static String vigenereDecode(String input, String key) {
	        StringBuilder decodedText = new StringBuilder();
	        int inputLength = input.length();
	        int keyLength = key.length();

	        for (int i = 0; i < inputLength; i++) {
	            char inputChar = input.charAt(i);
	            char keyChar = key.charAt(i % keyLength); // Repeating the key if it's shorter than the input

	            if (Character.isLetter(inputChar)) {
	                char base = Character.isUpperCase(inputChar) ? 'A' : 'a';
	                int inputIndex = inputChar - base;
	                int keyIndex = keyChar - base;

	                char decodedChar = (char) ((inputIndex - keyIndex + 26) % 26 + base);
	                decodedText.append(decodedChar);
	            } else {
	                decodedText.append(inputChar);
	            }
	        }

	        return decodedText.toString();
	    } // end decode method
	
	
	
	
	
	
	
	
	
} // end utility method class


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
	
	// begin sub cipher methods
	// substitution encode
	public static String substitutionEncode(String input, String substitutionMap) {
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
	}

	public static String substitutionDecode(String input, String substitutionMap) {
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
	}

	   
	   
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

	    public static void introduction() {
	    	System.out.println("Welcome to the Cipher Encoder!");
	    	System.out.println("This program will showcase a few basic encoding techniques: ");
	    	System.out.println("\t\tThe Caesar Cipher: \nShifts the letters in your message in an amount chosen by you.\n");
	    	System.out.println("\t\tThe Substitution Cipher: \nUses a random or preset key to encode your message.\n");
	    	System.out.println("\t\tThe Transposition Cipher: \nReaarranges the characters of you message in a grid-like fashion\n");
	    	System.out.println("For more information on these, select more information from the choices below.");
	    	System.out.println("");
	    	
	    } // end introduction

		public static void userEntry() {
			System.out.println("Please select a cipher type:");
	        System.out.println("1. Caesar Cipher");
	        System.out.println("2. Substitution Cipher");
	        System.out.println("3. Transposition Cipher");
	        System.out.println("4. More Information");
	        System.out.println("5. Quit the program");
		}
		
		public static void moreInformation() {
			System.out.println("\nCipher Descriptions:");
		    System.out.println("   Caesar Cipher: Shifts the letters in your message by a specified amount.");
		    System.out.println("   Example: If the shift amount is 1, 'A' becomes 'B', 'B' becomes 'C', and so on.");
		    System.out.println("   Decoding is done by shifting the letters in the opposite direction.");
		    System.out.println();
		    
		    System.out.println("   Substitution Cipher: Uses a random or preset key to encode your message.");
		    System.out.println("   Example: If the key is 'DEFGHIJKLMNOPQRSTUVWXYZABC', 'A' becomes 'D', 'B' becomes 'E', and so on.");
		    System.out.println("   Decoding is done by using the reverse mapping of the key.");
		    System.out.println();
		    
		    System.out.println("   Transposition Cipher: Rearranges the characters of your message in a grid-like fashion.");
		    System.out.println("   Example: If the message is 'HELLO WORLD', it could be rearranged as 'HRELO LLWOD'.");
		    System.out.println("   Decoding is done by reversing the grid-like arrangement.");

		    System.out.println();
		}
	
} // end utility method class


// Programmer: Adam Curtin
// CS 145
// Purpose: Entry point. Prompts user for input text and a shift amount, 
// then creates instances of encoder and decoder classes to perform said operations.


/*
 * Other Encryption Options:
 * Substitution Cipher
 * Vigenere Cipher
 * Transposition Cipher
 * RSa Encryption
 */
package caesarcipherencoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CipherMain {
	 public static void main(String[] args) {
		 String inputText;
	     int shiftAmount;
	     
	     if (args.length == 2) {
	    	 // Use command line arguments for input file and shift amount
	    	 inputText = readFromFile(args[0]);
	    	 shiftAmount = Integer.parseInt(args[1]);
	     } else {
	    	 // prompt the user for input text and shift amount
	    	 inputText = getInputText();
	    	 shiftAmount = getShiftAmount();
	     } // end if else
	        
	      

         Encoder encoder = new Encoder();
         String encodedText = encoder.encodeText(inputText, shiftAmount);
         System.out.println("Encoded text: " + encodedText);

         Decoder decoder = new Decoder();
         String decodedText = decoder.decodeText(encodedText, shiftAmount);
         System.out.println("Decoded text: " + decodedText);
        
         // write encoded text to a file
         String outputFileName = getOutputFileName(); // Prompt for output file name
         writeToFile(encodedText, outputFileName);
         System.out.println("Encoded text written to file: " + outputFileName); // Print the output file name
	} // end main method

	 /**
	  * Prompts the user to enter text to encode or decode and returns the provided text.
	  * @return The input text entered by the user
	  */
	    private static String getInputText() {
	        System.out.print("Enter the text to encode/decode: ");
	        Scanner scanner = new Scanner(System.in);
	        return scanner.nextLine();
	    } // end getInputText

	    /**
	     * Prompts the user to enter the shift amount, and returns the provided shift amount
	     * @return The shift amount entered by the user
	     */
	    private static int getShiftAmount() {
	        System.out.print("Enter the shift amount: ");
	        Scanner scanner = new Scanner(System.in);
	        return scanner.nextInt();
	    } // end getShiftAmount
	    
	    // Method to read file
	    private static String readFromFile(String fileName) {
	    	StringBuilder inputText = new StringBuilder();
	    	try (Scanner scanner = new Scanner(new File(fileName))) {
	    		while (scanner.hasNextLine()) {
	    			inputText.append(scanner.nextLine());
	    		}
	    	} catch (FileNotFoundException e) {
	    		System.out.println("File not found: " + fileName);
	    	}
	    	return inputText.toString();
	    } // end readFromFile
	    
	    // Method to write to a file
	    private static void writeToFile(String text, String fileName) {
	        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
	            writer.println(text);
	        } catch (FileNotFoundException e) {
	            System.out.println("Error writing to file: " + fileName);
	        }
	    } // end writeToFile
	    
	    // Get file name from user
	    private static String getOutputFileName() {
	        System.out.print("Enter the output file name: ");
	        Scanner scanner = new Scanner(System.in);
	        return scanner.nextLine();
	    } // end getOutputFileName
	    
	
}




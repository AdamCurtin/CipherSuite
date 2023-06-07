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
	    	 inputText = CipherUtility.readFromFile(args[0]);
	    	 shiftAmount = Integer.parseInt(args[1]);
	     } else {
	    	 // prompt the user for input text and shift amount
	    	 inputText = CipherUtility.getInputText();
	    	 shiftAmount = CipherUtility.getShiftAmount();
	     } // end if else
	        
	      
         Encoder encoder = new Encoder();
         String encodedText = encoder.encodeText(inputText, shiftAmount);
         System.out.println("Encoded text: " + encodedText);

         Decoder decoder = new Decoder();
         String decodedText = decoder.decodeText(encodedText, shiftAmount);
         System.out.println("Decoded text: " + decodedText);
        
      // Prompt the user to print the encoded text to a file
         boolean printToFile = CipherUtility.getPrintToFileChoice();
         if (printToFile) {
             String outputFileName = CipherUtility.getOutputFileName(); // Prompt for output file name
             CipherUtility.writeToFile(encodedText, outputFileName);
             System.out.println("Encoded text written to file: " + outputFileName); // Print the output file name
         } else {
             System.out.println("Encoded text will not be printed to a file.");
         } // end if else
         
         
         
//         // write encoded text to a file
//         String outputFileName = CipherUtility.getOutputFileName(); // Prompt for output file name
//         CipherUtility.writeToFile(encodedText, outputFileName);
//         System.out.println("Encoded text written to file: " + outputFileName); // Print the output file name
	} // end main method

	
	   

	   
	   
	    
	   
	   
	    
	
} // end CipherMain Class




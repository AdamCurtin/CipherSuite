// Programmer: Adam Curtin
// CS 145
// Purpose: Entry point. Prompts user for input text and a shift amount, 
// then creates instances of encoder and decoder classes to perform said operations.

package caesarcipherencoder;

import java.util.Scanner;

public class CipherMain {
    public static void main(String[] args) {
        // Introduction and menu
    	CipherUtility.introduction();
    	

        // Get user's cipher type choice
        Scanner scanner = new Scanner(System.in);
        CipherType cipherType = null;
        boolean validChoice = false;
        
        // Prompt until a valid choice is entered
        while (!validChoice) {
        	CipherUtility.userEntry();
	        String choice = scanner.nextLine();
	
	        // Set cipher type based on user's choice
	        switch (choice) {
	            case "1":
	                cipherType = CipherType.CAESAR;
	                validChoice = true;
	                break;
	            case "2":
	                cipherType = CipherType.SUBSTITUTION;
	                validChoice = true;
	                break;
	            case "3":
	                cipherType = CipherType.TRANSPOSITION;
	                validChoice = true;
	                break;
	            case "4":
	            	CipherUtility.moreInformation();
	            	 System.out.println("Press any key to return to the main menu...");
	                 scanner.nextLine(); // Wait for user to press any key
	                 System.out.println("Returning to main menu...");
	                 System.out.println();
	            	break;
	            case "5":
	            	System.out.println("Quitting the program...");
	            	return; 
	            default:
	                System.out.println("Invalid choice. Please select a valid cipher type:");
	        } // end switch case
        } // end while loop

        // Get user input
        String inputText = CipherUtility.getInputText();
        int shiftAmount = 0; // Initialize shift amount for Caesar cipher
        String outputFileName = CipherUtility.getOutputFileName();
        boolean printToFile = CipherUtility.getPrintToFileChoice();

        if (cipherType == CipherType.CAESAR) {
            shiftAmount = CipherUtility.getShiftAmount();
        }

        // Encode the input text
        Encoder encoder = new Encoder();
        String encodedText = encoder.encodeText(inputText, shiftAmount, cipherType);

        // Decode the encoded text
        Decoder decoder = new Decoder();
        String decodedText = decoder.decodeText(encodedText, shiftAmount, cipherType);

        // Print the results
        System.out.println("Encoded Text: " + encodedText);
        System.out.println("Decoded Text: " + decodedText);

        // Write the encoded text to a file if requested
        if (printToFile) {
            CipherUtility.writeToFile(encodedText, outputFileName);
            System.out.println("Encoded text written to file: " + outputFileName);
        }
    }
}


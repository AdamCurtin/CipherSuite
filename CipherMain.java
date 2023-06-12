// Programmer: Adam Curtin
// CS 145
// Purpose: Entry point. Prompts user for input text and a shift amount, 
// then creates instances of encoder and decoder classes to perform said operations.

package caesarcipherencoder;

import java.util.Scanner;

public class CipherMain {
    public static void main(String[] args) {
        // Introduction 
        CipherUtility.introduction();

        // Get user's cipher type choice
        CipherType cipherType = null;
        boolean running = true;

        // Prompt until a valid choice is entered
        while (running) {
            // Display main menu
            CipherUtility.userEntry();

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            // Set cipher type based on user's choice
            switch (choice) {
                case "1":
                    cipherType = CipherType.CAESAR;
                    break;
                case "2":
                    cipherType = CipherType.SUBSTITUTION;
                    break;
                case "3":
                    cipherType = CipherType.TRANSPOSITION;
                    break;
                case "4":
                    CipherUtility.moreInformation();
                    System.out.println("Press any key to return to the main menu...");
                    scanner.nextLine(); // Wait for user to press any key
                    System.out.println("Returning to main menu...");
                    System.out.println();
                    continue; // Prompt again for user choice
                case "5":
                    System.out.println("Quitting the program...");
                    running = false; // Set to false to exit loop
                    System.exit(0); // Quit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid cipher type:");
                    continue; // Prompt again for valid choice
            }

            // Get user input
            String inputText = CipherUtility.getInputText();
            int shiftAmount = 0; // Initialize shift amount for Caesar cipher

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
            boolean printToFile = CipherUtility.getPrintToFileChoice();

            if (printToFile) {
               
                    System.out.print("Enter the output file name: ");
                    String outputFileName = scanner.nextLine();
                    CipherUtility.writeToFile(encodedText, outputFileName);
                    System.out.println("Encoded text written to file: " + outputFileName);
            } // end if print to file
        } // end while running
    } // end main method
} // end CipherMain Class


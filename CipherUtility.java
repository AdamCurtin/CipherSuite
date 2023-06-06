
//
// Purpose: utility class with static methods to encapsulate the encoding
// and decoding logic



package caesarcipherencoder;

public class CipherUtility {
	 public static String encodeText(String input, int shift) {
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
	    }

	    public static String decodeText(String input, int shift) {
	        return encodeText(input, 26 - shift); // Decoding is the reverse of encoding with a shift of 26 - original shift
	    }
}

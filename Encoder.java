package caesarcipherencoder;

public class Encoder {
    public String encodeText(String input, int shift, CipherType cipherType) {
        switch (cipherType) {
            case CAESAR:
                return CipherUtility.caesarEncode(input, shift);
            case SUBSTITUTION:
            	String substitutionMap = "defghijklmnopqrstuvwxyzabc";
                return CipherUtility.substitutionEncode(input, substitutionMap);
            case TRANSPOSITION:
                return CipherUtility.transpositionEncode(input);
            default:
                throw new IllegalArgumentException("Invalid cipher type: " + cipherType);
        }
    } // end encodeText
} // end Encoder Class

package caesarcipherencoder;

public class Encoder {
    public String encodeText(String input, int shift, String key, CipherType cipherType) {
        switch (cipherType) {
            case CAESAR:
                return CipherUtility.caesarEncode(input, shift);
            case SUBSTITUTION:
                return CipherUtility.substitutionEncode(input);
            case VIGENERE:
                return CipherUtility.vigenereEncode(input, key);
            case TRANSPOSITION:
                return CipherUtility.transpositionEncode(input);
            default:
                throw new IllegalArgumentException("Invalid cipher type: " + cipherType);
        }
    } // end encodeText
} // end Encoder Class





package caesarcipherencoder;

public class Decoder {
    public String decodeText(String input, int shift, CipherType cipherType) {
        switch (cipherType) {
            case CAESAR:
                return CipherUtility.caesarDecode(input, shift);
            case SUBSTITUTION:
            	String substitutionMap = "defghijklmnopqrstuvwxyzabc";
                return CipherUtility.substitutionDecode(input, substitutionMap);
            case TRANSPOSITION:
                return CipherUtility.transpositionDecode(input);
            default:
                throw new IllegalArgumentException("Invalid cipher type: " + cipherType);
        }
    }
}
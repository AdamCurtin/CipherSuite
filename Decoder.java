



package caesarcipherencoder;

public class Decoder {
    public String decodeText(String input, int shift, String key, CipherType cipherType) {
        switch (cipherType) {
            case CAESAR:
                return CipherUtility.caesarDecode(input, shift);
            case SUBSTITUTION:
                return CipherUtility.substitutionDecode(input);
            case VIGENERE:
                return CipherUtility.vigenereDecode(input, key);
            case TRANSPOSITION:
                return CipherUtility.transpositionDecode(input);
            default:
                throw new IllegalArgumentException("Invalid cipher type: " + cipherType);
        }
    }
}
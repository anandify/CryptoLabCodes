/*
This program implements the repeating-key XOR encryption. It takes a plaintext message and a key, and encrypts the message using the key.
The plaintext message and key are converted to byte arrays, and then each byte of the message is XORed with the corresponding byte of the key.
The encrypted message, represented as a hexadecimal string, is then printed.

The RepeatingKeyXOR class demonstrates the usage of the repeatedKeyXor function by encrypting a sample plaintext message with a key "ICE".
*/

public class RepeatingKeyXor {
    public static void main(String[] args) {
        byte[] plainText = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal".getBytes();
        byte[] key = "ICE".getBytes();

        byte[] encrypted = repeatedKeyXor(plainText, key);
        System.out.println("Output: " + bytesToHex(encrypted));
    }

    // Function to perform repeated-key XOR encryption
    public static byte[] repeatedKeyXor(byte[] plainText, byte[] key) {
        byte[] encoded = new byte[plainText.length];
        int lenKey = key.length;

        // Iterate through each byte of the plaintext message
        for (int i = 0; i < plainText.length; i++) {
            // XOR each byte of the plaintext with the corresponding byte of the key (repeated if necessary)
            encoded[i] = (byte) (plainText[i] ^ key[i % lenKey]);
        }
        return encoded;
    }

    // Helper function to convert bytes to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

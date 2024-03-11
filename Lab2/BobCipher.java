import java.math.BigInteger;
import java.util.Scanner;

public class RSAEncryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Receive public key (e, n) from Bob
        System.out.println("Enter the value of e:");
        BigInteger e = scanner.nextBigInteger();
        System.out.println("Enter the value of n:");
        BigInteger n = scanner.nextBigInteger();

        // Step 2: Determine if input is integer or ASCII
        System.out.println("Enter the plaintext message:");
        String plaintext = scanner.next();
        BigInteger m;
        if (plaintext.matches("\\d+")) { // If input consists of only digits
            m = new BigInteger(plaintext);
        } else { // If input contains characters other than digits
            m = asciiToBigInteger(plaintext);
        }

        // Step 3: Compute ciphertext c
        BigInteger c = m.modPow(e, n);

        // Print ciphertext
        System.out.println("Ciphertext (c): " + c);

        scanner.close();
    }

    // Function to convert ASCII string to BigInteger
    private static BigInteger asciiToBigInteger(String ascii) {
        StringBuilder sb = new StringBuilder();
        for (char c : ascii.toCharArray()) {
            sb.append((int) c);
        }
        return new BigInteger(sb.toString());
    }
}

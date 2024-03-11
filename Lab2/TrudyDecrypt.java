import java.math.BigInteger;
import java.util.Scanner;
// RSADecryption
public class TrudyDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Receive public key (e, n) from Bob
        System.out.println("Step 1: Enter the value of e:");
        BigInteger e = scanner.nextBigInteger();
        System.out.println("Enter the value of n:");
        BigInteger n = scanner.nextBigInteger();

        // Step 2: Factor n to find p and q
        BigInteger[] factors = factorize(n);
        BigInteger p = factors[0];
        BigInteger q = factors[1];

        // Step 3: Compute z = (p-1)*(q-1)
        BigInteger z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Step 4: Compute decryption exponent d
        BigInteger d = computeDecryptionExponent(e, z);

        // Step 5: Wait to receive ciphertext message c from Alice
        System.out.println("Step 5: Enter the ciphertext message c:");
        BigInteger c = scanner.nextBigInteger();

        // Step 6: Decrypt the ciphertext message
        BigInteger m = c.modPow(d, n);

        // Print original message
        System.out.println("Original message (m): " + m);

        scanner.close();
    }

    // Function to factorize n
    private static BigInteger[] factorize(BigInteger n) {
        BigInteger[] factors = new BigInteger[2];
        BigInteger i = BigInteger.valueOf(2);
        while (i.multiply(i).compareTo(n) <= 0) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                factors[0] = i;
                factors[1] = n.divide(i);
                break;
            }
            i = i.add(BigInteger.ONE);
        }
        return factors;
    }

    // Function to compute decryption exponent d
    private static BigInteger computeDecryptionExponent(BigInteger e, BigInteger z) {
        BigInteger d = BigInteger.ONE;
        while (e.multiply(d).mod(z).compareTo(BigInteger.ONE) != 0) {
            d = d.add(BigInteger.ONE);
        }
        return d;
    }
}

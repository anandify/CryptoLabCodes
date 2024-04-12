/**
 * This Java program demonstrates a cryptographic problem involving the Digital Signature Algorithm (DSA).
 * It initializes certain parameters such as prime numbers (p and q), a global public element (g), a private key,
 * a random number (k), and a hash of a message. Then it calculates the digital signature (r, s) using the DSA algorithm.
 * The calculated signature is printed as output.
 */
import java.math.BigInteger;
import java.util.Scanner;

public class dsacheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of P:");
        BigInteger p = scanner.nextBigInteger();
        System.out.println("Enter the value of Q:");
        BigInteger q = scanner.nextBigInteger();
        System.out.println("Enter the value of g:");
        BigInteger g = scanner.nextBigInteger();
        System.out.println("Enter the value of the private key x:");
        BigInteger privateKey = scanner.nextBigInteger();
        System.out.println("Enter the value of k:");
        BigInteger k = scanner.nextBigInteger();
        System.out.println("Enter the hash of the message H(m):");
        BigInteger messageHash = scanner.nextBigInteger();

        // Calculate y = g^privateKey mod p
        BigInteger y = g.modPow(privateKey, p);

        // Calculate r = (g^k mod p) mod q
        BigInteger r = g.modPow(k, p).mod(q);

        // Calculate s = (k^-1 * (messageHash + privateKey * r)) mod q
        BigInteger kInverse = k.modInverse(q);
        BigInteger s = (kInverse.multiply(messageHash.add(privateKey.multiply(r)))).mod(q);

        System.out.println("DSA Signature (r, s): (" + r + ", " + s + ")");
        scanner.close();
    }
}

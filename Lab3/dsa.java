/**
 * This Java program implements the Digital Signature Algorithm (DSA) for generating and verifying digital signatures.
 * It prompts the user to input prime numbers (p and q), a global public element (h), the hash of a message (H(M)),
 * a random number (k), and a private key (x). Then it calculates the digital signature (r, s) using the DSA algorithm.
 * The calculated signature is printed as output.
 */

import java.math.BigInteger;
import java.util.Scanner;

public class dsa {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to input prime number p
        System.out.println("Enter the prime number (p): ");
        BigInteger p = scanner.nextBigInteger();

        // Prompt user to input prime number q
        System.out.println("Enter the prime number (q): ");
        BigInteger q = scanner.nextBigInteger();

        // Prompt user to input global public element h
        System.out.println("Enter the global public element (h): ");
        BigInteger h = scanner.nextBigInteger();

        // Prompt user to input the hash of the message H(M)
        System.out.println("Enter the hash of the message (H(M)): ");
        BigInteger H_M = scanner.nextBigInteger();

        // Prompt user to input the random number k
        System.out.println("Enter the random number (k): ");
        BigInteger k = scanner.nextBigInteger();

        // Calculate g
        BigInteger g = h.modPow(p.subtract(BigInteger.ONE).divide(q), p);

        // Prompt user to input private key x
        System.out.println("Enter the private key (x): ");
        BigInteger x = scanner.nextBigInteger();

        // Calculate public key y
        BigInteger y = g.modPow(x, p);

        // Calculate r
        BigInteger r = g.modPow(k, p).mod(q);

        // Calculate s
        BigInteger kInverse = k.modInverse(q);
        BigInteger s = kInverse.multiply(H_M.add(x.multiply(r))).mod(q);

        // Display Bob's Signature (r, s)
        System.out.println("Bob's Signature (r, s): ");
        System.out.println(r + ", " + s);

        scanner.close();
    }
}


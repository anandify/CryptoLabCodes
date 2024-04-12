/**
 * This program implements the Elgamal Digital Signature Scheme.
 * It takes the parameters q, alpha, the public key, the secret key,
 * and the message m as inputs, and outputs the signature (r, s).
 */
import java.math.BigInteger;

public class Elgamal{

    public static void main(String[] args) {
        // Define parameters
        BigInteger q = new BigInteger("73"); // Prime modulus
        BigInteger alpha = new BigInteger("11"); // Generator
        BigInteger privateKey = new BigInteger("47"); // Private key
        BigInteger publicKey = new BigInteger("39"); // Public key
        BigInteger k = new BigInteger("23"); // Random number
        BigInteger m = new BigInteger("25"); // Message

        // Generate signature
        BigInteger[] signature = generateSignature(q, alpha, privateKey, k, m);
        BigInteger r = signature[0];
        BigInteger s = signature[1];

        // Output generated signature
        System.out.println("Generated Signature (r, s): ");
        System.out.println(r + ", " + s);

        // Verify the generated signature
        if (verifySignature(q, alpha, publicKey, m, r, s)) {
            System.out.println("Signature is valid.");
        } else {
            System.out.println("Signature is not valid.");
        }
    }

    
    public static BigInteger[] generateSignature(BigInteger q, BigInteger alpha, BigInteger privateKey, BigInteger k, BigInteger m) {
        BigInteger[] signature = new BigInteger[2];

        // Calculate r
        BigInteger r = alpha.modPow(k, q);

        // Calculate s
        BigInteger kInverse = k.modInverse(q.subtract(BigInteger.ONE));
        BigInteger s = m.subtract(privateKey.multiply(r)).multiply(kInverse).mod(q.subtract(BigInteger.ONE));

        signature[0] = r;
        signature[1] = s;

        return signature;
    }

    
    public static boolean verifySignature(BigInteger q, BigInteger alpha, BigInteger publicKey, BigInteger m, BigInteger r, BigInteger s) {
        BigInteger v1 = alpha.modPow(m, q);
        BigInteger v2 = publicKey.modPow(r, q).multiply(r.modPow(s, q)).mod(q);

        return v1.equals(v2);
    }
}

import java.math.BigInteger;
import java.util.Random;

public class Alice {
    
    // Function to check if a number is prime
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
    
    // Function to generate a random 8-bit number
    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(256); // 256 = 2^8
    }
    
    // Function to set the highest and lowest bits of a number
    public static int setBits(int num) {
        return num | 0b10000001; // Setting highest and lowest bits to 1
    }
    
    // Function to find the next prime number
    public static int getNextPrime(int num) {
        while (!isPrime(num)) {
            num += 2;
        }
        return num;
    }
    
    // Function to check if (p-1) is coprime with e=17
    public static boolean isCoprime(int p) {
        return (p - 1) % 17 != 0; // Checking if (p-1) mod e != 0
    }
    
    public static void main(String[] args) {
        int e = 17;
        int p, q;
        
        // Step 2: Select p
        do {
            // Generate a random 8-bit number
            p = generateRandomNumber();
            // Set the highest and lowest bits
            p = setBits(p);
            // Check if p is prime, if not, find the next prime
            if (!isPrime(p)) {
                p = getNextPrime(p);
            }
            // Check if (p-1) is coprime with e=17
        } while (!isCoprime(p));
        
        // Step 3: Select q (different from p)
        do {
            // Generate a random 8-bit number
            q = generateRandomNumber();
            // Set the highest and lowest bits
            q = setBits(q);
            // Check if q is prime, if not, find the next prime
            if (!isPrime(q)) {
                q = getNextPrime(q);
            }
        } while (q == p);
        
        System.out.println("Generated prime number p: " + p);
        System.out.println("Generated prime number q: " + q);
        
        // Calculate n
        BigInteger n = BigInteger.valueOf(p).multiply(BigInteger.valueOf(q));
        
        System.out.println("Calculated n: " + n);
        System.out.println("Calculated e: " + e);
    }
}

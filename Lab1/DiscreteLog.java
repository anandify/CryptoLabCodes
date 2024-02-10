/*
This program computes discrete logarithms using modular exponentiation method. It includes functions to perform modular exponentiation and find discrete logarithms using modular exponentiation.

The DiscreteLog class demonstrates the usage of the discreteLogModularExponentiation function by computing logarithms for two different sets of base, result, and modulus values.
*/

public class DiscreteLog {

    // Function to compute discrete logarithm using modular exponentiation
    public static int discreteLogModularExponentiation(int base, int result, int modulus) {
        // Iterate over possible exponents
        for (int x = 1; x < modulus; x++) {
            // Perform modular exponentiation and check if it equals the result
            if (modularExponentiation(base, x, modulus) == result) {
                return x;
            }
        }
        // If no solution found
        return -1;
    }

    // Function to compute modular exponentiation
    public static int modularExponentiation(int base, int exponent, int modulus) {
        if (modulus == 1)
            return 0;
        int result = 1;
        base = base % modulus;
        while (exponent > 0) {
            if (exponent % 2 == 1)
                result = (result * base) % modulus;
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }

    public static void main(String[] args) {
        // First problem
        int base1 = 10; // base for log10
        int result1 = 22; // value for which logarithm is to be calculated
        int modulus1 = 47; // prime modulus

        int logarithm1 = discreteLogModularExponentiation(base1, result1, modulus1);
        if (logarithm1 != -1) {
            System.out.println("log10(" + result1 + ") mod " + modulus1 + " = " + logarithm1);
        } else {
            System.out.println("No discrete logarithm found for the first problem.");
        }

        // Second problem
        int base2 = 627; // base for log627
        int result2 = 608; // value for which logarithm is to be calculated
        int modulus2 = 941; // prime modulus

        int logarithm2 = discreteLogModularExponentiation(base2, result2, modulus2);
        if (logarithm2 != -1) {
            System.out.println("log627(" + result2 + ") mod " + modulus2 + " = " + logarithm2);
        } else {
            System.out.println("No discrete logarithm found for the second problem.");
        }
    }
}

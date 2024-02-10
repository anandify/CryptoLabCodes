/*
This program performs a fixed XOR operation on two hexadecimal strings and returns the result.

For example, given the hexadecimal strings:
hexValue1 = "1c0111001f010100061a024b53535009181c"
hexValue2 = "686974207468652062756c6c277320657965"

The expected output is: "746865206b696420646f6e277420706c6179"
*/

import java.math.BigInteger;
import java.util.Scanner;

public class FixedXor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the first hexadecimal string: ");
        String hexValue1 = scanner.nextLine();

        System.out.print("Enter the second hexadecimal string: ");
        String hexValue2 = scanner.nextLine();
        
        // Step 1-2: Convert hexadecimal strings to binary strings
        String binValue1 = hexToBin(hexValue1);
        String binValue2 = hexToBin(hexValue2);

        // Step 3: Ensure equal length by padding with zeros
        int desiredLength = Math.max(binValue1.length(), binValue2.length());
        binValue1 = zeroPad(binValue1, desiredLength);
        binValue2 = zeroPad(binValue2, desiredLength);

        // Step 4: Perform XOR operation
        String stringResult = xor(binValue1, binValue2);

        // Step 5: Convert result back to hexadecimal
        String finalOutput = binToHex(stringResult);

        // Output the final result
        System.out.println("XOR Result: " + finalOutput);
        
        scanner.close();
    }

    // Helper function to convert hexadecimal string to binary string
    public static String hexToBin(String hexString) {
        return new BigInteger(hexString, 16).toString(2);
    }

    // Helper function to zero pad a binary string to desired length
    public static String zeroPad(String binaryString, int desiredLength) {
        StringBuilder paddedString = new StringBuilder(binaryString);
        while (paddedString.length() < desiredLength) {
            paddedString.insert(0, "0");
        }
        return paddedString.toString();
    }

    // Helper function to perform XOR operation on two binary strings
    public static String xor(String binaryString1, String binaryString2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binaryString1.length(); i++) {
            result.append(binaryString1.charAt(i) == binaryString2.charAt(i) ? "0" : "1");
        }
        return result.toString();
    }

    // Helper function to convert binary string to hexadecimal string
    public static String binToHex(String binaryString) {
        return new BigInteger(binaryString, 2).toString(16);
    }
}

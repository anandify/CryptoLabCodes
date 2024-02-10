/*
This program takes a hexadecimal string as input and converts it to a base64 string.
*/

import java.util.Scanner;

public class HexToBase64 {
    
    private static final String HEX_CHARS = "0123456789ABCDEF";
    private static final String BASE64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the hexadecimal string: \n");
        String hexInput = scanner.nextLine();
        
        String base64Output = hexToBase64(hexInput);
        System.out.println("Base64 String: \n" + base64Output);
        
        scanner.close();
    }

    public static String hexToBase64(String hexString) {
        StringBuilder base64String = new StringBuilder();
        StringBuilder binaryString = new StringBuilder();

        // Convert hex string to binary string
        for (int i = 0; i < hexString.length(); i++) {
            int hexIndex = HEX_CHARS.indexOf(Character.toUpperCase(hexString.charAt(i)));
            String binary = Integer.toBinaryString(hexIndex);
            while (binary.length() < 4) {
                binary = "0" + binary;
            }
            binaryString.append(binary);
        }

        // Add padding if necessary
        while (binaryString.length() % 6 != 0) {
            binaryString.append("0");
        }

        // Convert binary string to base64 string
        for (int i = 0; i < binaryString.length(); i += 6) {
            int startIndex = i;
            int endIndex = Math.min(i + 6, binaryString.length());
            String binaryChunk = binaryString.substring(startIndex, endIndex);
            int decimalValue = Integer.parseInt(binaryChunk, 2);
            base64String.append(BASE64_CHARS.charAt(decimalValue));
        }

        // Add padding if necessary
        while (base64String.length() % 4 != 0) {
            base64String.append("=");
        }

        return base64String.toString();
    }
}

/*
This program cracks a single-byte XOR encryption by attempting all possible keys and performing frequency analysis.
It iterates over each possible key (lowercase and uppercase alphabets) and decrypts the input string using XOR with the key.
After decryption, it calculates the score for each decryption based on English letter frequency.
The key with the highest score is considered the most likely key, and the corresponding decrypted message is returned.

Example:
Input: 1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736
Output:
    Key: X
    Message: Cooking MC's like a pound of bacon
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SingleCharXor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the hexadecimal string: ");
        String input = scanner.nextLine();
        
        Map<Character, String> resultMap = crack(input);
        System.out.println("Key: " + resultMap.keySet().iterator().next());
        System.out.println("Message: " + resultMap.values().iterator().next());
        
        scanner.close();
    }

    // Function to crack the XOR encryption
    public static Map<Character, String> crack(String input) {
        Map<Character, String> res = new HashMap<>(); // Map to store results
        char candidateKey = '\0'; // Variable to store the candidate key
        int highestScore = 0; // Variable to store the highest score

        // Frequency analysis of English characters
        String[] freq = {" ", "e", "t", "a", "o", "i", "n", "s", "h", "r", "d", "l", "u"};
        
        // Possible keys: lowercase and uppercase alphabets
        char[] keys = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // Iterate over each possible key
        for (char key : keys) {
            StringBuilder output = new StringBuilder(); // StringBuilder to store decrypted output
            int cursor = 0; // Cursor to traverse through the input string

            // Decrypt the input string using the current key
            while (cursor < input.length()) {
                String hex = input.substring(cursor, cursor + 2); // Extract two characters from the input as hexadecimal
                int xor = Integer.parseInt(hex, 16) ^ (int) key; // Perform XOR operation with the key
                output.append((char) xor); // Append the decrypted character to the output
                cursor += 2; // Move the cursor to the next pair of hexadecimal characters
            }

            int score = 0; // Variable to store the score for the current key
            
            // Calculate the score based on frequency analysis
            for (int i = 0; i < freq.length; i++) {
                String re = freq[i]; // Current character for frequency analysis
                long matches = output.toString().toLowerCase().chars().filter(ch -> ch == re.charAt(0)).count(); // Count occurrences of the character
                score += matches * (12 - i); // Update the score based on the frequency and position
            }

            // Update the candidate key and result if the current score is higher than the highest score
            if (score > highestScore) {
                highestScore = score;
                candidateKey = key;
                res.put(key, output.toString());
            }
        }

        // Create a map to return the result
        Map<Character, String> result = new HashMap<>();
        result.put(candidateKey, res.get(candidateKey));
        return result;
    }
}

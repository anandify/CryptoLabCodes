import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/*
This program reads encrypted text from a file, decrypts it using single-character XOR encryption,
and identifies potential English language text among the decrypted results based on frequency analysis.
It then outputs the possible English language texts along with their location information.
*/
public class SingleCharXorDecryption {

    public static void main(String[] args) {
        // File path
        String filePath = "sample.txt";

        // Read encrypted text from file
        String[] encryptedLines = readFromFile(filePath);

        // Perform decryption and get possible English language outcomes
        List<Map.Entry<String, Integer>> possibleEnglishTexts = getPossibleEnglishTexts(encryptedLines);

        // Output the possible English language texts and their location info
        for (Map.Entry<String, Integer> entry : possibleEnglishTexts) {
            System.out.println("Decrypted Text: \n" + entry.getKey());
            System.out.println("Location: Line " + entry.getValue() + "\n");
        }
    }

    // Reads lines of text from a file and returns them as an array of strings
    public static String[] readFromFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString().split("\n");
    }

    // Decrypts the lines of text using single-character XOR encryption and identifies potential English language texts
    public static List<Map.Entry<String, Integer>> getPossibleEnglishTexts(String[] encryptedLines) {
        List<Map.Entry<String, Integer>> possibleEnglishTexts = new ArrayList<>();

        // Iterate through each line and score it based on English language frequency analysis
        for (int i = 0; i < encryptedLines.length; i++) {
            String encryptedLine = encryptedLines[i];
            String decryptedText = decryptSingleCharacterXOR(encryptedLine);
            int score = scoreEnglishText(decryptedText);

            // Only consider lines with scores above the threshold
            if (score > 100) { // Adjust the threshold as needed
                possibleEnglishTexts.add(Map.entry(decryptedText, i + 1)); // Store decrypted text along with line number
            }
        }

        return possibleEnglishTexts;
    }

    // Decrypts a line of text encrypted using single-character XOR encryption
    public static String decryptSingleCharacterXOR(String encryptedHex) {
        byte[] encryptedBytes = hexStringToByteArray(encryptedHex);
        char key = findKey(encryptedBytes);
        byte[] decryptedBytes = new byte[encryptedBytes.length];
        for (int i = 0; i < encryptedBytes.length; i++) {
            decryptedBytes[i] = (byte) (encryptedBytes[i] ^ key);
        }
        return new String(decryptedBytes);
    }

    // Finds the key used for single-character XOR encryption
    public static char findKey(byte[] encryptedBytes) {
        char key = ' ';
        int maxSpaces = 0;

        for (char candidateKey = 0; candidateKey < 256; candidateKey++) {
            int spaces = 0;
            for (byte encryptedByte : encryptedBytes) {
                char decryptedChar = (char) (encryptedByte ^ candidateKey);
                if (Character.isLetterOrDigit(decryptedChar) || Character.isWhitespace(decryptedChar)) {
                    if (Character.isWhitespace(decryptedChar)) {
                        spaces++;
                    }
                } else {
                    spaces = 0;
                    break;
                }
            }
            if (spaces > maxSpaces) {
                maxSpaces = spaces;
                key = candidateKey;
            }
        }
        return key;
    }

    // Converts a hexadecimal string to a byte array
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    // Scores a text based on frequency analysis of English characters
    public static int scoreEnglishText(String text) {
        // Frequency analysis of English characters
        String[] freq = {" ", "e", "t", "a", "o", "i", "n", "s", "h", "r", "d", "l", "u"};

        // Score the text based on frequency analysis
        int score = 0;
        for (int i = 0; i < freq.length; i++) {
            String re = freq[i]; // Current character for frequency analysis
            long matches = text.toLowerCase().chars().filter(ch -> ch == re.charAt(0)).count(); // Count occurrences of the character
            score += matches * (12 - i); // Update the score based on the frequency and position
        }
        return score;
    }
}

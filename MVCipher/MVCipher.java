import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The MVCipher class implements the encryption and decryption using the MV Cipher technique.
 * It reads from an input file, processes each line, and writes the result to an output file.
 * The class supports both encryption and decryption based on a user-provided keyword.
 * 
 * @author Shrey Vishen
 * @since September 23, 2024
 */
public class MVCipher {
    // Keyword used for encryption/decryption, converted to uppercase.
    private String key = "";
    
    // Current index to track position in the keyword.
    private int index = 0;
    
    // Debug flag to show additional information during runtime.
    private boolean DEBUG = false;

    /**
     * The main method to start the program.
     * It initializes the MVCipher object and calls the run method.
     * 
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        MVCipher cipher = new MVCipher();
        cipher.run();
    }

    /**
     * This method controls the flow of the program.
     * It prompts the user for a keyword, input/output file names, and whether to encrypt or decrypt.
     * The method reads the input file line by line and processes each character.
     */
    public void run() {
        System.out.println("\nWelcome to the MV Cipher machine!\n");
        
        // Retrieve and validate the encryption/decryption keyword.
        this.key = this.getKey();
        
        if (DEBUG) {
            System.out.println("The key is " + this.key);
        }

        // Determine whether to encrypt (true) or decrypt (false).
        boolean encrypt = Prompt.getInt("\nEncrypt or decrypt? (1 for encrypt, 2 for decrypt): ", 1, 2) == 1;

        // Prompt for the file to process.
        String inputFileName = Prompt.getString("Name of file to process: ");
        Scanner inputFile = FileUtils.openToRead(inputFileName);

        // Prompt for the output file.
        String outputFileName = Prompt.getString("Name of output file: ");
        PrintWriter outputFile = FileUtils.openToWrite(outputFileName);

        // Process each line of the input file.
        while (inputFile.hasNext()) {
            String line = inputFile.nextLine();
            String processedLine = processLine(line, encrypt);
            outputFile.println(processedLine); // Write the processed line to the output file.
        }

        // Close the output file.
        outputFile.close();

        // Display the completion message.
        System.out.println("\nThe " + (encrypt ? "encrypted" : "decrypted") + 
                           " file " + outputFileName + " has been created using the keyword -> " + this.key);
    }

    /**
     * Prompts the user for a valid keyword that will be used for encryption/decryption.
     * The keyword must be at least 3 characters long and contain only alphabetic characters.
     * 
     * @return The validated keyword in uppercase.
     */
    public String getKey() {
        boolean valid = false;
        String keyword = "";

        while (!valid) {
            keyword = Prompt.getString("Please input a word to use as key (letters only): ").toUpperCase();
            valid = isValidKey(keyword);
            if (!valid) {
                System.out.println("ERROR: Key must be all letters and at least 3 characters long.");
            }
        }

        return keyword;
    }

    /**
     * Validates the keyword ensuring it contains at least 3 alphabetic characters.
     * 
     * @param key The keyword entered by the user.
     * @return True if the keyword is valid, false otherwise.
     */
    public boolean isValidKey(String key) {
        if (key.length() < 3) {
            return false;
        }

        // Check if all characters are alphabetic (A-Z).
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch < 'A' || ch > 'Z') {
                return false;
            }
        }

        return true;
    }

    /**
     * Processes each line by encrypting or decrypting individual characters.
     * It handles both uppercase and lowercase alphabetic characters.
     * 
     * @param line The input line to be processed.
     * @param encrypt True for encryption, false for decryption.
     * @return The processed line.
     */
    private String processLine(String line, boolean encrypt) {
        StringBuilder processedLine = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            char processedChar = currentChar;

            if (Character.isLetter(currentChar)) {
                if (Character.isLowerCase(currentChar)) {
                    processedChar = encryptDecryptLowerCase(currentChar, encrypt);
                } else if (Character.isUpperCase(currentChar)) {
                    processedChar = encryptDecryptUpperCase(currentChar, encrypt);
                }
            }

            processedLine.append(processedChar);
        }

        return processedLine.toString();
    }

    /**
     * Encrypts or decrypts a single lowercase character using the MV Cipher method.
     * 
     * @param ch The character to be encrypted or decrypted.
     * @param encrypt True for encryption, false for decryption.
     * @return The encrypted or decrypted character.
     */
    public char encryptDecryptLowerCase(char ch, boolean encrypt) {
        char keyChar = this.key.charAt(this.index);
        this.index = (this.index + 1) % this.key.length();
        int shift = keyChar - 'A'; // Calculate shift based on the key character.

        char result;
        if (encrypt) {
            result = (char) (ch + shift);
            if (result > 'z') {
                result -= 26;
            }
        } else {
            result = (char) (ch - shift);
            if (result < 'a') {
                result += 26;
            }
        }

        return result;
    }

    /**
     * Encrypts or decrypts a single uppercase character using the MV Cipher method.
     * 
     * @param ch The character to be encrypted or decrypted.
     * @param encrypt True for encryption, false for decryption.
     * @return The encrypted or decrypted character.
     */
    public char encryptDecryptUpperCase(char ch, boolean encrypt) {
        char keyChar = this.key.charAt(this.index);
        this.index = (this.index + 1) % this.key.length();
        int shift = keyChar - 'A'; // Calculate shift based on the key character.

        char result;
        if (encrypt) {
            result = (char) (ch + shift);
            if (result > 'Z') {
                result -= 26;
            }
        } else {
            result = (char) (ch - shift);
            if (result < 'A') {
                result += 26;
            }
        }

        return result;
    }
}

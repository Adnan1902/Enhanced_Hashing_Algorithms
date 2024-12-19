import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class SecureHash {
    private static final Map<Integer, String> HASH_ALGORITHMS = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    
    static {
        HASH_ALGORITHMS.put(1, "SHA-1");
        HASH_ALGORITHMS.put(2, "SHA-256");
        HASH_ALGORITHMS.put(3, "MD5");
        HASH_ALGORITHMS.put(4, "SHA-512");
    }

    public static void main(String[] args) {
        while (true) {
            try {
                displayMenu();
                int choice = getMenuChoice();
                
                if (choice == 5) {
                    System.out.println("Thank you for using the Enhanced Hashing Program!");
                    break;
                }
                
                processUserChoice(choice);
                
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Enhanced Hashing Program ===");
        System.out.println("1. Hash text input");
        System.out.println("2. Hash file content");
        System.out.println("3. Compare two hashes");
        System.out.println("4. Batch process files");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    private static int getMenuChoice() {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 5) {
                    return choice;
                }
                System.out.print("Please enter a valid choice (1-5): ");
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number (1-5): ");
            }
        }
    }

    private static void processUserChoice(int choice) throws Exception {
        switch (choice) {
            case 1:
                hashTextInput();
                break;
            case 2:
                hashFileContent();
                break;
            case 3:
                compareHashes();
                break;
            case 4:
                batchProcessFiles();
                break;
        }
    }

    private static void hashTextInput() throws NoSuchAlgorithmException {
        System.out.println("\n=== Hash Text Input ===");
        String algorithm = selectHashingAlgorithm();
        
        System.out.print("Enter the text to hash: ");
        String text = scanner.nextLine();
        
        String hash = generateHash(text.getBytes(), algorithm);
        displayHashResult(text, hash, algorithm);
    }

    private static void hashFileContent() throws Exception {
        System.out.println("\n=== Hash File Content ===");
        String algorithm = selectHashingAlgorithm();
        
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();
        
        if (!Files.exists(Paths.get(filePath))) {
            throw new FileNotFoundException("File not found: " + filePath);
        }
        
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        String hash = generateHash(fileContent, algorithm);
        
        System.out.println("\nFile: " + filePath);
        System.out.println(algorithm + " Hash: " + hash);
    }

    private static void compareHashes() throws NoSuchAlgorithmException {
        System.out.println("\n=== Compare Hashes ===");
        String algorithm = selectHashingAlgorithm();
        
        System.out.print("Enter first text: ");
        String text1 = scanner.nextLine();
        String hash1 = generateHash(text1.getBytes(), algorithm);
        
        System.out.print("Enter second text: ");
        String text2 = scanner.nextLine();
        String hash2 = generateHash(text2.getBytes(), algorithm);
        
        System.out.println("\nResults:");
        System.out.println("Text 1 Hash: " + hash1);
        System.out.println("Text 2 Hash: " + hash2);
        System.out.println("Hashes " + (hash1.equals(hash2) ? "match!" : "do not match!"));
    }

    private static void batchProcessFiles() throws Exception {
        System.out.println("\n=== Batch Process Files ===");
        String algorithm = selectHashingAlgorithm();
        
        System.out.print("Enter directory path: ");
        String dirPath = scanner.nextLine();
        
        if (!Files.isDirectory(Paths.get(dirPath))) {
            throw new NotDirectoryException("Not a valid directory: " + dirPath);
        }
        
        System.out.println("\nProcessing files...");
        Files.walk(Paths.get(dirPath))
             .filter(Files::isRegularFile)
             .forEach(file -> {
                 try {
                     byte[] content = Files.readAllBytes(file);
                     String hash = generateHash(content, algorithm);
                     System.out.println("\nFile: " + file.getFileName());
                     System.out.println("Hash: " + hash);
                 } catch (Exception e) {
                     System.err.println("Error processing file " + file.getFileName() + ": " + e.getMessage());
                 }
             });
    }

    private static String selectHashingAlgorithm() {
        System.out.println("\nAvailable hashing algorithms:");
        HASH_ALGORITHMS.forEach((key, value) -> System.out.println(key + ". " + value));
        
        while (true) {
            System.out.print("Select algorithm (1-" + HASH_ALGORITHMS.size() + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (HASH_ALGORITHMS.containsKey(choice)) {
                    return HASH_ALGORITHMS.get(choice);
                }
            } catch (NumberFormatException e) {
                // Continue loop
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static String generateHash(byte[] input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] digest = md.digest(input);
        
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private static void displayHashResult(String input, String hash, String algorithm) {
        System.out.println("\nResults:");
        System.out.println("Input: " + input);
        System.out.println(algorithm + " Hash: " + hash);
    }
}
# Enhanced Hashing Program

This is a Java program that allows users to generate hashes for text inputs and file contents using various cryptographic algorithms. The program also supports batch processing of files and the comparison of hashes.

## Features

1. *Hash Text Input*: Hashes the text input using various algorithms.
2. *Hash File Content*: Hashes the contents of a file.
3. *Compare Hashes*: Compares the hashes of two inputs to check if they are the same.
4. *Batch Process Files*: Processes all files in a specified directory, hashing each file's content.
5. *Supports Multiple Hashing Algorithms*:
    - SHA-1
    - SHA-256
    - MD5
    - SHA-512

## Requirements

- JDK 8 or higher
- Java IDE or a text editor to run the program

## How to Run

1. Clone this repository to your local machine:

    bash
    git clone https://github.com/yourusername/enhanced-hashing-program.git
    

2. Navigate to the project folder:

    bash
    cd enhanced-hashing-program
    

3. Compile and run the program:

    bash
    javac Main.java
    java Main
    

4. Follow the interactive menu to choose an option.

## Usage

Upon running the program, you will be presented with the following menu:

1. *Hash text input*: Enter text to generate its hash using the selected algorithm.
2. *Hash file content*: Provide a file path to hash the file content.
3. *Compare two hashes*: Compare the hashes of two text inputs.
4. *Batch process files*: Process all files in a specified directory and hash their contents.
5. *Exit*: Exit the program.

### Example

When you select "1. Hash text input", the program will ask you to enter the text, choose a hashing algorithm (SHA-1, SHA-256, MD5, or SHA-512), and then display the hash of the input text.

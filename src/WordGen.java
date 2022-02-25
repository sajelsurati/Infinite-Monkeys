/**
 * Infinite Monkey Theorem
 * 
 * This class takes the file input, converts the file to a string, splits the string into sizes of 
 * specified k, creates a string with the characters after each string "chunk," and generates a new
 * text sample based off of the file input.
 * 
 * Methods:
 * 1. Main
 * 2. readFileAsString to read a file
 * 
 * @author Sajel Surati
 * 
 */

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class WordGen {
    
    private static Scanner scan = new Scanner(System.in);
    private static SequenceTable seqTable = new SequenceTable();
    
    /**
     * Main method
     * 
     * Takes in file name and k value. Splits text file into chunks of k length, goes through each
     * chunk and gets the next characters and calls SequenceTable mathods to track how often the
     * character occur.
     * 
     * @param args
     */

    public static void main(String[] args) {

        boolean fileFound = false;
        String textFile = "";

        while (!fileFound) {
            System.out.println("Enter file to read: ");
            String inputText = scan.next();

            textFile = readFileAsString(inputText);

            if (textFile == null) {
                System.out.println("Text file not found. Please enter new file name.");
            }

            else {
                fileFound = true;
            }
        }

        System.out.println("Enter desired value of k");

        int kValue = scan.nextInt();

        int numberOfChunks = textFile.length() - kValue + 1;

        String[] listOfChunks = new String[numberOfChunks];

        String newText = textFile.substring(0, kValue);

        for (int i = 0; i < (numberOfChunks); i++) {
            String kLengthChars = textFile.substring(i, i + kValue);

            String charsAfterChunks = "";

            listOfChunks[i] = kLengthChars;

            for (int k = 0; k < (numberOfChunks); k++) {
                String testSubstring = textFile.substring(k, (k + kValue));

                if ((k == (numberOfChunks - 1)) && testSubstring.equals(kLengthChars) && 
                (!(seqTable.testIfPresent(kLengthChars)))) {
                    charsAfterChunks += textFile.charAt(0);

                }

                else if(testSubstring.equals(kLengthChars)) {
                    charsAfterChunks += textFile.charAt(k + kValue);
                }
            }

            seqTable.appendValue(kLengthChars, charsAfterChunks);
        }

        for (int i = 0; i < 500; i++) {
            String stringToAddTo = newText.substring(i, (i + kValue));

            newText += seqTable.generateChar(stringToAddTo);
        }
        System.out.println(newText);
    }
    
    /**
     * Read the contents of a file into a string. If the file does not
     * exist or cannot not be read for any reason, returns null.
     *
     * @param filename The name of the file to read.
     * @return The contents of the file as a string, or null.
     */

    private static String readFileAsString(String fileName) {
        try {
            Path path = (Paths.get("text-files/" + fileName)).toAbsolutePath();

            return Files.readString(path);
        } catch (IOException e) {
            return null;
        }
    }

}
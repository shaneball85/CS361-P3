package tm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * @Author Shane Ball
 * @Author Austin Andersen
 * CS 361 Fall 2023
 * Project 3
 *
 * FileParser is an object that holds the file path of the text file with the instructions for the turing machine.
 * The parseFile() method scans through a .txt of a specific format per the project 3 instructions. First it scans to
 * count the number of lines with a comma. Then it scans one more time and converts each line of the file to the
 * correct ParseObject attribute. It then returns a ParseObject to be used by tm.TMSimulator.
 */
public class FileParser {
    String filePath;

    /**
     * Initialize a FileParser by providing a file path to the .txt file
     * @param filePath
     */
    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Helper method for parseFile() that counts all the lines in filePath that have a comma.
     * Lines with a comma are transitions
     * @return int that is the count of the total lines that contain a comma
     * @throws IOException
     */
    private int countLines() throws IOException {
        // try-with-resources statement to automatically close the stream after used
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            //Return an int that is the total lines in filePath that match the filter
            return (int) stream.
                    filter(line -> line.contains(",")) // Filter any line that contains a ","
                    .count(); // Count each line that passes the filter
        }
    }

    /**
     * Parses through the .txt file in filePath and converts each line into the appropriate ParseObject attribute
     * @return ParseObject
     */
    public ParseObject parseFile() {
        int numberOfLines; // Used to store the count of lines with a comma returned from the countLines() helper
        try {
            numberOfLines = countLines(); // run countLines on the .txt file
        } catch (IOException e) { // catch an error and exit with message if caught
            e.printStackTrace();
            return null;
        }

        // Initialize a ParseObject that has a transitions array large enough to fit all transitions in the file
        ParseObject parseObject = new ParseObject(numberOfLines);

        // try-with-resources statement creates a new scanner to parse through a file also creates a File object that
        // is a representation of the .txt file in filePath that will be parsed by scanner
        try (Scanner scanner = new Scanner(new File(filePath))) {
            // The first line of the file is the number of states in the machine
            parseObject.setNumberOfStates(Integer.parseInt(scanner.nextLine()));
            // The second line of the file is the number of characters (1 to m) in the input alphabet
            parseObject.setSigma(Integer.parseInt(scanner.nextLine()));

            // Iterate through the next lines block of lines in the file (equal to numberOfLines) and add a
            // transition to the transitions array for each line
            for (int i = 0; i < numberOfLines; i++) {
                String line = scanner.nextLine(); // Create a string of the line

                /**
                 * Create an array from the string
                 * Index 0 before first "," is the nextState of the transition
                 * Index 1 between first and second "," is the writeSymbol of the transition
                 * Index 3 after second "," is the move char of the transition
                 * The format of these lines of the file is known in advance
                 */
                String[] segments = line.split(",");
                int nextState = Integer.parseInt(segments[0]);
                int writeSymbol = Integer.parseInt(segments[1]);
                char move = segments[2].charAt(0);

                // Initialize a temporary transition using the variables created from the current line
                Transition tempTransition = new Transition(nextState, writeSymbol, move);
                // Add the transition to teh transitions array of the ParseObject
                parseObject.addTransition(tempTransition);
            }

            // Check to see if there is another line that contains a string and if so assign the string as
            // the input string
            if (scanner.hasNextLine()) {
                parseObject.setInputString(scanner.nextLine());
            }
            // If there is no string, the input string is initialized as an empty string and will remain empty
            // which is a valid option

        } catch (FileNotFoundException e) { // catch a file not found error and exit with a message if caught
            e.printStackTrace();
            return null; // if a complete parseObject is not created due to an error, return null instead
        }
        return parseObject;
    }
}

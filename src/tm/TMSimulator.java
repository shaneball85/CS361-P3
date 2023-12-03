package tm;

import java.io.File;

/**
 * @author Shane Ball
 * @author Austin Andersen
 * CS 361 Fall 2023
 * Project 3
 *
 * This is the driver class for the touring machine project and takes a single command line argument for a text file,
 * which pulls elements from the text file and uses them to create a touring machine, printing out the results.
 */
public class TMSimulator {
      public static void main(String[] args) {
            if (args.length < 1) { // Returns an error message to the terminal if no arguments entered
                  System.out.println("TMSimulator Usage: java tm.TMSimulator file_name [Y]\n" +
                          "Example: java tm.TMSimulator file0.txt\n" +
                          "The optional Y indicates you would like the tape length and sum\n");
                  return;
            }
            String fileName = args[0]; // pull first command line argument to get file name
            String filePath = findFile(fileName); // use the helper method to check current directory and tm for file

            if (filePath == null) { // if the file isn't found return an error message
                System.out.println("Error: " + fileName + " not found in current directory or tm folder.\n" +
                        "Please check that the file name is correct and is this directory or in the tm folder\n");
                return;
            }

            FileParser fileParser = new FileParser(filePath); //Create file parser using file path
            ParseObject textParse = fileParser.parseFile(); // Parse the text to get each element of the Turing Machine
            TuringMachine machine = new TuringMachine(textParse); // Create the Turing Machine
            // Run the machine until it reaches its final state
            while (machine.getStatePointer() < (machine.getNumberOfStates() - 1)) {
                  machine.takeTransition();
            }
            System.out.println(machine.getTape()); // Print the resulting tape to the terminal

          // If the optional arg is entered, print the output lent and sum of all symbols of the tape
          if (args.length > 1 && args[1].equalsIgnoreCase("Y")) {
              System.out.println("output length: " + machine.getTape().tapeLength());
              System.out.println("sum of symbols: " + machine.getTape().tapeSum());
          }
      }

    /**
     * Allows the simulator to check the current directory or sub (TM) directory for the given file name.
     * If the file is in the tm directory it will update the file name appropriately so that the machine will run.
     * @param fileName
     * @return String file name or path
     */
    private static String findFile(String fileName) {
          File fileInFolder = new File(fileName);
          if (fileInFolder.exists()) {
              return fileName;
          }
          File fileInTmFolder = new File("tm/" + fileName);
          if (fileInTmFolder.exists()) {
              return "tm/" + fileName;
          }
          return null;
      }
}

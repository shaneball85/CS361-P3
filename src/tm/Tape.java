package tm;

/**
 * @Author Shane Ball
 * @Author Austin Andersen
 * CS 361 Fall 2023
 * Project 3
 *
 * This class stores the currentTapeNode that the Turing Machine is pointing to. It also manages all the methods
 * needed by the Turing Machine to move back and forth along the tape, read from the tape, and write to it. It also
 * a way to get a String representation of the tape and some methods to sum the values in the tape and get its length
 * that are used for testing.
 */
public class Tape {
    private TapeNode currentTapeNode;

    /**
     * Initialize the tape with one default TapeNode
     */
    public Tape() {
        this.currentTapeNode = new TapeNode();
    }

    /**
     * Move the currentTapeNode one step to the right. Will add a new TapeNode if there isn't one already
     */
    public void moveRight() {
        if (currentTapeNode.getRight() == null) { // Check if there is a node to the right
            currentTapeNode.setRight(new TapeNode()); // Initialize a new TapeNode to the right of current if needed
            currentTapeNode.getRight().setLeft(currentTapeNode); // Set the new node's Left pointer back to current
        }
        currentTapeNode = currentTapeNode.getRight(); // Move the current node to the right
    }

    /**
     * Move the currentTapeNode one step to the left. Will add a new TapeNode if there isn't one already
     */
    public void moveLeft() {
        if (currentTapeNode.getLeft() == null) { // Check if there is a node to the left
            currentTapeNode.setLeft(new TapeNode()); // Initialize a new TapeNode to the left of current if needed
            currentTapeNode.getLeft().setRight(currentTapeNode); // Set the new node's Right pointer back to current
        }
        currentTapeNode = currentTapeNode.getLeft(); // Move the current node to the left
    }

    /**
     * Get the int of the TapeNode's tapeContents
     * @return int TapeNode.tapeContents
     */
    public int read() {
        return currentTapeNode.getTapeContents();
    }

    /**
     * Write a new int value to the TapeNode.tapeContents
     * @param writeValue int to be written to the TapeNode
     */
    public void write(int writeValue) {
        currentTapeNode.setTapeContents(writeValue);
    }

    /**
     * Helper method that moves back to the beginning of the tape. Used for all the following methods
     * @return temp TapeNode
     */
    private TapeNode moveToBeginning() {
        TapeNode temp = currentTapeNode;

        // Move back to the beginning of the tape;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        return temp;
    }

    /**
     * Sum all the int values in each TapeNode's tapeContents
     * @return int
     */
    public int tapeSum() {
        int sum = 0;
        TapeNode temp = moveToBeginning(); // Get the first TapeNode in the tape

        while (temp.getRight() != null) { // Iterate through the tape until the final TapeNode
            sum += temp.getTapeContents(); // Add the int in each TapeNode's tapeContents to the return sum
            temp = temp.getRight(); // Walk to the next node
        }
        sum += temp.getTapeContents(); // Make sure to check the final TapeNode and add it to the sum
        return sum;
    }

    /**
     * Get the length of the tape (total number TapeNodes)
     * @return int
     */
    public int tapeLength() {
        int length = 1; // Start at 1 since the last node won't be counted in the while loop
        TapeNode temp = moveToBeginning(); // Get the first TapeNode in the tape

        while (temp.getRight() != null) { // Iterate through the tape until the final TapeNode
            length++; // Increment the length by one for each node visited
            temp = temp.getRight(); // Walk to the next node
        }
        return length;
    }

    @Override
    /**
     * Get a string that is the tapeContents of each TapeNode in Tape in order
     * @return String
     */
    public String toString() {
        String returnString = "";
        TapeNode temp = moveToBeginning(); // Get the first TapeNode in the tape

        while (temp.getRight() != null) { // Iterate through the tape until the final TapeNode
            returnString += temp.getTapeContents(); // Add the int in each TapeNode's tapeContents to the return String
            temp = temp.getRight(); // Walk to the next node
        }
        returnString += temp.getTapeContents(); // Make sure to check the final TapeNode and add it to the String
        return returnString;
    }
}

package tm;

/**
 * @Author Shane Ball
 * @Author Austin Andersen
 * CS 361 Fall 2023
 * Project 3
 *
 * This class is part of the TM package. The transition object holds all the necessary information for one possible
 * transition of the turing machine. These are built one by one as the given txt file is parsed.
 */
public class Transition {
    private int nextState; // Stores the label of the next state that will be reached if the transition is taken
    private int writeSymbol; // Stores the symbol that will be written to the tape if the transition is taken
    private char move; // Stores the direction the head will move over the tape if the transition is taken

    /**
     * Initialize a Transition with all the properties that will be provided in the .txt files
     * @param next int value of the next state
     * @param write int value to write to tape
     * @param move char of direction to move on tape
     */
    public Transition(int next, int write, char move) {
        this.nextState = next;
        this.writeSymbol = write;
        this.move = move;
    }

    /**
     * Get the int value of the next State that will be reached if the transition is taken
     * @return int
     */
    public int getNextState() {
        return nextState;
    }

    /**
     * Get the symbol that will be written to the tape if the transition is taken
     * @return int
     */
    public int getWriteSymbol() {
        return writeSymbol;
    }

    /**
     * Return the character that designates what direction to move on the tape if taking the transition
     * @return the 'R' or 'L' character in the Transition
     */
    public char getMove() {
        return move;
    }

    @Override
    /**
     * @return a String object that represents the characteristics of a transition exactly as shown in the .txt files
     */
    public String toString() {
        return nextState + "," + writeSymbol + "," + move;
    }
}

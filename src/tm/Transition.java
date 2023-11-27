package tm;

/**
 * @Author Shane Ball
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

    public Transition(int next, int write, char move) {
        this.nextState = next;
        this.writeSymbol = write;
        this.move = move;
    }

    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public int getWriteSymbol() {
        return writeSymbol;
    }

    public void setWriteSymbol(int writeSymbol) {
        this.writeSymbol = writeSymbol;
    }

    public char getMove() {
        return move;
    }

    public void setMove(char move) {
        this.move = move;
    }

    @Override
    /**
     * @return a String object that represents the characteristics of a transition exactly as shown in the .txt files
     */
    public String toString() {
        return nextState + "," + writeSymbol + "," + move;
    }
}

/**
 * Author: Shane Ball
 * CS 361 Fall 2023
 * Project 3
 *
 * This class is part of the TM package. The transition object holds all the necessary information for one possible
 * transition of the turing machine. These are built one by one as the given txt file is parsed.
 */
public class Transition {
    private int nextState;
    private int writeSymbol;
    private char move;

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
    public String toString() {
        return nextState + "," + writeSymbol + "," + move;
    }
}

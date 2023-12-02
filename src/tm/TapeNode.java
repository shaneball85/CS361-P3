package tm;

/**
 * @Author Shane Ball
 * @Author Austin Andersen
 * CS 361 Fall 2023
 * Project 3
 *
 * This creates a TapeNode object that will be used to create the tape in the Turing Machine and provides all the
 * necessary getters, setters, and toString.
 */
public class TapeNode {
    int tapeContents;
    TapeNode right;
    TapeNode left;

    /**
     * Initialize a blank TapeNode (0 represents blank)
     */
    public TapeNode() {
        this.tapeContents = 0;
        this.right = null;
        this.left = null;
    }

    /**
     * Get the tapeContents of the TapeNode
     * @return int
     */
    public int getTapeContents() {
        return tapeContents;
    }

    /**
     * Set the int value of the tapeContents
     * @param tapeContents
     */
    public void setTapeContents(int tapeContents) {
        this.tapeContents = tapeContents;
    }

    /**
     * Get the TapeNode that is to the right
     * @return TapeNode
     */
    public TapeNode getRight() {
        return right;
    }

    /**
     * Set the TapeNode that is to the right
     * @param right
     */
    public void setRight(TapeNode right) {
        this.right = right;
    }

    /**
     * Get the TapeNode that is to the left
     * @return TapeNode
     */
    public TapeNode getLeft() {
        return left;
    }

    /**
     * Set the TapeNode that is to the left
     * @param left
     */
    public void setLeft(TapeNode left) {
        this.left = left;
    }

    @Override
    /**
     * Return a String that shows the value stored in the TapeNode's tapeContents
     * @return String
     */
    public String toString() {
        return String.valueOf(tapeContents);
    }
}

package tm;

public class Tape {
    private TapeNode currentTapeNode;

    public Tape() {
        this.currentTapeNode = new TapeNode(0);
    }

    public void moveRight() {
        if (currentTapeNode.getRight() == null) {
            currentTapeNode.setRight(new TapeNode(0)); // Initialize a new TapeNode to the right of current
            currentTapeNode.getRight().setLeft(currentTapeNode); // Set the new node's Left pointer back to current
        }
        currentTapeNode = currentTapeNode.getRight(); // Move the current node to the right
    }

    public void moveLeft() {
        if (currentTapeNode.getLeft() == null) {
            currentTapeNode.setLeft(new TapeNode(0)); // Initialize a new TapeNode to the left of current
            currentTapeNode.getLeft().setRight(currentTapeNode); // Set the new node's Right pointer back to current
        }
        currentTapeNode = currentTapeNode.getLeft(); // Move the current node to the left
    }

    public TapeNode getCurrentTapeNode() {
        return this.currentTapeNode;
    }

    public int read() {
        return currentTapeNode.getTapeContents();
    }

    public void write(int writeValue) {
        currentTapeNode.setTapeContents(writeValue);
    }

    private TapeNode moveToBeginning() {
        TapeNode temp = currentTapeNode;

        // Move back to the beginning of the tape;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        return temp;
    }

    public int tapeSum() {
        int sum = 0;
        TapeNode temp = moveToBeginning();

        // Add the TapeContents at each node to the sum from the beginning to the end of the tape
        while (temp.getRight() != null) {
            sum += temp.getTapeContents();
            temp = temp.getRight();
        }
        return sum;
    }

    public int tapeLength() {
        int length = 1;
        TapeNode temp = moveToBeginning();

        while (temp.getRight() != null) {
            length++;
            temp = temp.getRight();
        }
        return length;
    }

    @Override
    public String toString() {
        String returnString = "";
        TapeNode temp = moveToBeginning();

        // Add the TapeContents at each node to the String from the beginning to the end of the tape
        while (temp.getRight() != null) {
            returnString += temp.getTapeContents();
            temp = temp.getRight();
        }
        return returnString;
    }
}

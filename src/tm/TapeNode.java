package tm;

public class TapeNode {
    int tapeContents;
    TapeNode right;
    TapeNode left;

    public TapeNode(int write) {
        this.tapeContents = write;
        this.right = null;
        this.left = null;
    }

    public int getTapeContents() {
        return tapeContents;
    }

    public void setTapeContents(int tapeContents) {
        this.tapeContents = tapeContents;
    }

    public TapeNode getRight() {
        return right;
    }

    public void setRight(TapeNode right) {
        this.right = right;
    }

    public TapeNode getLeft() {
        return left;
    }

    public void setLeft(TapeNode left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return String.valueOf(tapeContents);
    }
}

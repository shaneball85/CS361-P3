package tm;

/**
 * @Author Shane Ball
 * CS 361 Fall 2023
 * Project 3
 *
 * This is the object that will be created and returned after tm.FileParser scans files that are appropriately designed
 * for this project. A ParseObject will contain all the instructions for how the Touring Machine will run and will be
 * used by tm.TMSimulator to produce the output
 */
public class ParseObject {
    private int numberOfStates; // number of states in the Touring Machine
    private int sigma; // number of characters in the input alphabet (1 to m)
    private Transition[] transitions; // array of transition objects
    private String inputString; // pre-defined input into the machine (can be empty)

    /**
     * Constructor for ParseObject
     * @param numberOfTransitions
     *
     * Needs numberOfTransitions to initialize the transitions array to a size to the exact number of transitions
     */
    public ParseObject(int numberOfTransitions) {
        numberOfStates = 0;
        sigma = 0;
        transitions = new Transition[numberOfTransitions];
        inputString = "";
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public int getSigma() {
        return sigma;
    }

    public void setSigma(int sigma) {
        this.sigma = sigma;
    }

    public Transition[] getTransitions() {
        return transitions;
    }

    /**
     * Get the transition at the provided index in the transitions array
     * @param index
     * @return Transition
     */
    public Transition getTransitionAtIndex(int index) {
        return transitions[index];
    }

    /**
     * Get a smaller Transition[] from the parseObject's Transition[]. Used when initializing a State to set the
     * transitions specific to that State
     * @param startIndex in the parseObject's Transition[]
     * @param endIndex in the parseObject's Transition[]
     * @return a subset Transition[] of the parseObject's Transition[]
     */
    public Transition[] getTransitionsInRange(int startIndex, int endIndex) {
        int transitionsLength = endIndex - startIndex + 1; // Set the size of the subset array

        Transition[] returnTransitions = new Transition[transitionsLength];

        /**
         * Loop through the parseObject's Transition[] for as many iterations as the length of the subarray.
         * Get the transitions from the start index to the end index by using the startIndex and increment it
         * each pass through the loop until it equals the endIndex.
         */
        for (int i = 0; i < transitionsLength; i++) {
            returnTransitions[i] = transitions[startIndex];
            startIndex++;
        }
        return returnTransitions;
    }

    public void setTransitions(Transition[] transitions) {
        this.transitions = transitions;
    }

    /**
     * Adds a transition into the first empty space in the transitions array.
     * Used by tm.FileParser as it creates the complete ParseObject
     * @param transition
     */
    public void addTransition(Transition transition) {
        for (int i = 0; i < transitions.length; i++) {
            if (transitions[i] == null) {
                transitions[i] = transition;
                return;
            }
        }
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    @Override
    /**
     * Creates a string representation of the parse object with labels. If the labels are removed, the output should
     * exactly match the parsed .txt file
     */
    public String toString() {
        String returnString = "Number of states: " + numberOfStates + "\n";
        returnString += "Sigma: { " + sigma + " }\n";
        returnString += "Transitions: \n";
        for (int i = 0; i < transitions.length; i++) {
            Transition tempTransition = transitions[i];
            returnString += tempTransition.toString() + "\n";
        }
        returnString += "Input String: " + inputString + "\n";

        return returnString;
    }
}

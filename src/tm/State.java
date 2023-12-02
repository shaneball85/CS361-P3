package tm;
/**
 * @Author Shane Ball
 * @Author Austin Anderson
 * CS 361 Fall 2023
 * Project 3
 *
 * This class is used to represent each state in the Turing Machine. A state will know all its possible transitions and
 * will know if it is a final (halting) state or not
 */
public class State {
    private Transition[] transitions;
    private boolean isFinal;

    public State(int numberOfTransitions) {
        if (numberOfTransitions != 0) {
            transitions = new Transition[numberOfTransitions];
            isFinal = false;
        } else {
            isFinal = true;
        }
    }

    /**
     * Initialize a State with an array of transitions. If a State has transitions it cannot be final
     * @param transitions to be added to the State
     */
    public State(Transition[] transitions) {
        this.transitions = transitions;
        isFinal = false;
    }

    /**
     * Get a transitions at a specific index in the Transition[] array
     * @param index for the desired Transition
     * @return the Transition at the index
     */
    public Transition getTransitionAtIndex(int index) {
        return transitions[index];
    }

    /**
     * Check if the state is a final state
     * @return boolean if the state is final or not
     */
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    /**
     * This to string was used for testing that each State was initialized correctly inside the Turing Machine with the
     * correct transitions or set as final
     */
    public String toString() {
        String returnString = "";
        if (isFinal) {
            returnString += "Final state reached. No more transitions\n";
        } else {
            for (int i = 0; i < transitions.length; i++) {
                returnString += "Transition " + (i + 1) + " is " + transitions[i] + "\n";
            }
        }
        return returnString;
    }
}

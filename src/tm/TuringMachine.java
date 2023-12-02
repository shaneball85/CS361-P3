package tm;

/**
 * @Author Shane Ball
 * @Author Austin Andersen
 * CS 361 Fall 2023
 * Project 3
 *
 * This is the Turing Machine that will be run by the TMSimulator. Takes a parseObject and creates a machine that knows
 * all of its states and is able to choose the proper transitions from each state based on the state's available
 * transitions and the tape that the machine maintains.
 */
public class TuringMachine {
    private Tape tape; // Keep track of the current TapeNode being used by the machine
    private int statePointer = 0; // Always start at State 0
    private int numberOfStates; // Keep track of the number of states in the machine
    private State[] states; // All States in the machine that each store their own transitions


    public TuringMachine(ParseObject parseObject) {
        String inputString = parseObject.getInputString(); // Get the input string from the parseObject
        this.tape = new Tape();

        // If there is an input string, iterate through the string and add each character in the string into the Tape
        if (inputString.length() > 0) {
            for (int i = 0; i < inputString.length(); i++) {
                tape.write(Character.getNumericValue(inputString.charAt(i))); // Get the int value and write to tape
                tape.moveRight(); // Move the current TapeNode to the right to be ready for the input
            }
        }
        this.numberOfStates = parseObject.getNumberOfStates();

        states = new State[numberOfStates]; // Initialize the state array to the correct size for the machine

        // Get the total number of possible transitions
        int numberOfTransitions = parseObject.getTransitions().length;

        // Calculate the number of transitions per state considering that the final state will not have any transitions
        int transitionsPerState = numberOfTransitions / (numberOfStates - 1);

        // start and end index used for the parseObject getTransitionsInRange() method to get the subset of transitions
        int startIndex = 0;
        int endIndex = transitionsPerState - 1;

        for (int i = 0; i < states.length; i++) {
            // Initialize the final state as a final state
            if (i == states.length - 1) {
                states[i] = new State(0);
            // If it is not a final state initialize the state with the correct subset of transitions
            } else {
                // get the subset of transitions based on starting and ending index
                Transition[] transitions = parseObject.getTransitionsInRange(startIndex, endIndex);
                states[i] = new State(transitions);
                startIndex += transitionsPerState; // Increment the start index to the range of the next state
                endIndex += transitionsPerState; // Increment the end index to the range of the next state
            }
        }
    }

    /**
     * Get the int stored in the tape at the currentTapeNode. The int that is read from the tape is also the index in
     * the current state's Transition[] of the transition that needs to be taken. This is a helper method for the
     * takeTransition() method.
     * @return the transition to be taken
     */
    private Transition chooseTransition() {
        Transition chosenTransition = states[statePointer].getTransitionAtIndex(tape.read());
        return chosenTransition;
    }

    /**
     * Takes the next transition in the TouringMachine and updates all the attributes of the machine accordingly
     */
    public void takeTransition() {
        if (states[statePointer].isFinal()) {
            return;
        }
        // Get the transition that will be taken
        Transition chosenTransition = chooseTransition();

        // Write to the tape at the current location of headPointer based on the instructions of the current transition
        tape.write(chooseTransition().getWriteSymbol());

        // Update the state pointer to the next state based on the transition
        statePointer = chosenTransition.getNextState();

        // Update the currentTapeNode by moving (R)ight else or (L)eft depending on the currentTransition.move char
        if (chosenTransition.getMove() == 'R') {
            tape.moveRight();;
        } else {
            tape.moveLeft();
        }
    }

    /**
     * Get the current value of statePointer
     * @return int
     */
    public int getStatePointer() {
        return statePointer;
    }

    /**
     * Get the number of states in the machine
     * @return int
     */
    public int getNumberOfStates() {
        return numberOfStates;
    }

    /**
     * Get the current TapeNode being used by the machine
     * @return TapeNode
     */
    public Tape getTape() {
        return tape;
    }

    @Override
    /**
     * Returns a string representation of the Turing Machine. Used primarily for testing if the machine was initialized
     * correctly. Can also be used after running the machine to a halt state to see the tape.
     * @return String
     */
    public String toString() {
        String returnString = "The Touring Machine built from the file looks like: \n" +
                "Number of States: " + numberOfStates + "\n";
        for (int i = 0; i < states.length; i++) {
            returnString += "State at " + i + " transitions:\n" + states[i].toString();
        }
        returnString += "Tape: ";
        returnString += tape + "\n";

        return returnString;
    }
}

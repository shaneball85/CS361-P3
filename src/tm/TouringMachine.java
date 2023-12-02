package tm;

import java.util.LinkedList;

public class TouringMachine {
    private String inputString;
    private LinkedList<Integer> tape;
    private int headPointer;
    private int statePointer;
    private int numberOfStates;
    private int sigmaSize;
    private State[] states;


    public TouringMachine(ParseObject parseObject) {
        this.inputString = parseObject.getInputString();
        tape = new LinkedList<>();
        // If there is an input string, iterate through the string and add each character in the string one by one
        // into the linked list as an integer
        if (inputString.length() > 0) {
            for (int i = 0; i < inputString.length(); i++) {
                tape.add(i, Character.getNumericValue(inputString.charAt(i)));
            }
            System.out.println("Tape of " + inputString + " Added To Machine"); // Remove after testing complete
        // If the input string from the file is blank, add a 0 into the head index of the linked list to represent
        // the blank
        } else {
            tape.add(0,0);
            System.out.println("Blank Tape Added To Machine"); // Remove after testing complete
        }
        headPointer = 0;
        statePointer = 0;
        this.numberOfStates = parseObject.getNumberOfStates();
        this.sigmaSize = parseObject.getSigma();
        states = new State[numberOfStates]; // Initialize the state array to the correct size for the machine

        // Get the total number of transitions from the file to calculate the number of transitions that should be
        // in each state
        int numberOfTransitions = parseObject.getTransitions().length;
        // Calculate the number of transitions per state considering that the final state will not have any transitions
        int transitionsPerState = numberOfTransitions / (numberOfStates - 1);
        System.out.println("Transitions Per State = " + transitionsPerState); // Remove after testing complete
        // start and end index used for the parseObject getTransitionsInRange() method to get the specified
        // transitions subset
        int startIndex = 0;
        int endIndex = transitionsPerState - 1;

        System.out.println("Starting to create states and add transitions..."); // Remove after testing complete

        for (int i = 0; i < states.length; i++) {
            // Initialize the final state as a final state
            if (i == states.length - 1) {
                states[i] = new State(0);
                System.out.println("Halt state created at States index " + i); // Remove after testing complete
            // If it is not a final state initialize the state with the correct subset of transitions that belong to
            // that state
            } else {
                // get the subset of transitions based on starting and ending index
                Transition[] transitions = parseObject.getTransitionsInRange(startIndex, endIndex);
                states[i] = new State(transitions);
                System.out.println("State added to States index " + i); // Remove after testing complete
                System.out.println(states[i]); // Remove after testing complete
                startIndex += transitionsPerState; // Increment the start index to the range of the next state
                System.out.println("Start Index = " + startIndex); // Remove after testing complete
                endIndex += transitionsPerState; // Increment the end index to the range of the next state
                System.out.println("End Index = " + endIndex); // Remove after testing complete
            }
        }
        System.out.println("Machine created"); // Remove after testing complete
    }

    /**
     * Get the int stored in the tape at the current index of the headPointer. The int that is read from the
     * tap is also the index in the current state's Transition[] of the transition that needs to be taken. This is
     * a helper method for the takeTransition() method.
     * @return the transition to be taken given the current state and current location of the headPointer
     */
    private Transition chooseTransition() {
        return states[statePointer].getTransitionAtIndex(tape.get(headPointer));
    }

    /**
     * Takes the next transition in the TouringMachine and updates all the attributes of the machine accordingly
     */
    public void takeTransition() {
        // Get the transition that will be taken
        Transition chosenTransition = chooseTransition();
        // Update the state pointer to the next state based on the transition
        statePointer = chosenTransition.getNextState();
        // Write to the tape at the current location of headPointer based on the instructions of the current transition
        tape.set(headPointer, chosenTransition.getWriteSymbol());

        // Update the location of the head pointer on the tape +1 if move (R)ight else -1 for (L)eft
        if (chosenTransition.getWriteSymbol() == 'R') {
            headPointer += 1;
        } else {
            headPointer -= 1;
        }

        // Check if the headPointer has gone beyond the last index of the tape and if so, add a blank to the
        // tape
        if (headPointer > tape.size() - 1) {
            tape.add(0);
        }
        // If the headPointer moves left it can move past index 0 of the tape. If it does add a new blank at the
        // beginning of the tape and have headPointer point to it
        if (headPointer == -1) {
            tape.add(0, 0);
            headPointer = 0;
        }
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    public LinkedList<Integer> getTape() {
        return tape;
    }

    public void setTape(LinkedList<Integer> tape) {
        this.tape = tape;
    }

    public int getHeadPointer() {
        return headPointer;
    }

    public void setHeadPointer(int headPointer) {
        this.headPointer = headPointer;
    }

    public int getStatePointer() {
        return statePointer;
    }

    public void setStatePointer(int statePointer) {
        this.statePointer = statePointer;
    }

    public int getNumberOfStates() {
        return numberOfStates;
    }

    public void setNumberOfStates(int numberOfStates) {
        this.numberOfStates = numberOfStates;
    }

    public int getSigmaSize() {
        return sigmaSize;
    }

    public void setSigmaSize(int sigmaSize) {
        this.sigmaSize = sigmaSize;
    }

    public State[] getStates() {
        return states;
    }

    public void setStates(State[] states) {
        this.states = states;
    }

    @Override
    public String toString() {
        String returnString = "The Touring Machine built from the file looks like: \n" +
                "Number of States: " + numberOfStates + "\n";
        for (int i = 0; i < states.length; i++) {
            returnString += "State at " + i + " transitions:\n" + states[i].toString();
        }
        returnString += "Tape: ";
        for (int i = 0; i < tape.size(); i++) {
            returnString += tape.get(i);
        }
        return returnString;
    }
}

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
        if (inputString.length() > 0) {
            for (int i = 0; i < inputString.length(); i++) {
                tape.add(i, Character.getNumericValue(inputString.charAt(i)));
            }
            System.out.println("Tape of " + inputString + " Added To Machine");
        } else {
            tape.add(0,0);
            System.out.println("Blank Tape Added To Machine");
        }
        headPointer = 0;
        statePointer = 0;
        this.numberOfStates = parseObject.getNumberOfStates();
        this.sigmaSize = parseObject.getSigma();
        states = new State[numberOfStates];

        int numberOfTransitions = parseObject.getTransitions().length;
        int transitionsPerState = (numberOfStates - 1) / numberOfTransitions;
        int transitionsIndex = 0;

        System.out.println("Starting to create states and add transitions...");

        for (int i = 0; i < states.length; i++) {
            // Determine the number of transitions for the current state
            int currentTransitionsCount = (i < states.length - 1) ? transitionsPerState : 0;
            states[i] = new State(currentTransitionsCount);
            System.out.println("State " + i + " created with " + currentTransitionsCount + " transitions");

            /**
             * This loop is currently not being reached to add the transitions to the state...
             */
            for (int j = 0; j < currentTransitionsCount; j++) {
                int transitionIndex = i * transitionsPerState + j;
                if (transitionIndex < parseObject.getTransitions().length) {
                    states[i].setTransitionAtIndex(j, parseObject.getTransitionAtIndex(transitionIndex));
                    System.out.println("Transition index = " + transitionIndex + " added to State " + i);
                }
            }
        }
        System.out.println("Machine created");
    }


//    private Transition chooseTransition() {
//    }

    private void takeTransition() {
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

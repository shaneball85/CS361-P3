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
        tape.add(0,0);
        if (inputString.length() > 0) {
            for (int i = 0; i < inputString.length(); i++) {
                tape.add(i + 1, Character.getNumericValue(inputString.charAt(i)));
            }
        }
        headPointer = 0;
        statePointer = 0;
        this.numberOfStates = parseObject.getNumberOfStates();
        this.sigmaSize = parseObject.getSigma();

    }

    private Transition chooseTransition() {


    }

    private void takeTransition() {

    }

}

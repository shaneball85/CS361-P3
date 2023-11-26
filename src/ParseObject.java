public class ParseObject {
    private int numberOfStates;
    private int sigma;
    private Transition[] transitions;
    private String inputString;

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

    public Transition getTransitionAtIndex(int index) {
        return transitions[index];
    }

    public void setTransitions(Transition[] transitions) {
        this.transitions = transitions;
    }

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

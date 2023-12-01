package tm;

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

    public State(Transition[] transitions) {
        this.transitions = transitions;
        isFinal = false;
    }

    public Transition[] getTransitions() {
        return transitions;
    }

    public void setTransitions(Transition[] transitions) {
        this.transitions = transitions;
    }

    public void setTransitionAtIndex(int index, Transition transition) {
        transitions[index] = transition;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < transitions.length; i++) {
            if (isFinal != true) {
                returnString += "Transition " + (i + 1) + " is " + transitions[i] + "\n";
            } else {
                returnString += "Final state reached. No more transitions\n";
            }
        }
        return returnString;
    }
}

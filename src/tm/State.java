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

    public Transition[] getTransitions() {
        return transitions;
    }

    public void setTransitions(Transition[] transitions) {
        this.transitions = transitions;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }
}

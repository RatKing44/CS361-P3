package turing.TM;

import java.util.LinkedHashSet;
import turing.State;


public class TuringState extends State{
    protected LinkedHashSet<Transition> transitions;
    protected String name;

    public TuringState(String name){
        super(name);
        transitions = new LinkedHashSet<>();
    }

    public void addTransition(State to, char read, char write, char move){
        Transition t = new Transition(to, read, write, move);
        transitions.add(t);
    }

    public Transition getTransition(char readSymb){
        for (Transition t : transitions){
            if (t.getReadSymb() == readSymb){
                return t;
            }
        }
        return null;
    }

    public class Transition{
        private State toState;
        private char readSymb;
        private char writeSymb;
        private char moveSymb;

        public Transition(State to, char read, char write, char move){
            this.toState = to;
            this.readSymb = read;
            this.writeSymb = write;
            this.moveSymb = move;
        }

        public State getToState(){
            return toState;
        }

        public char getReadSymb(){
            return readSymb;
        }

        public char getWriteSymb(){
            return writeSymb;
        }

        public char getMoveSymb(){
            return moveSymb;
        }
    }
    
}

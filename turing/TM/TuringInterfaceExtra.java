package turing.TM;

import java.util.Set;

import turing.TuringInterface;
import turing.TM.TuringState.Transition;

public interface TuringInterfaceExtra extends TuringInterface{
    public Transition getToState(TuringState from, char onSymb);

    public boolean addTransition(String fromState, Set<String> toStates, char readSymb, char writeSymb, char moveSymb);

}
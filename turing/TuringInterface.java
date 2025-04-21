package turing;

import java.util.Set;



public interface TuringInterface{

    public boolean addState(String name);

    public boolean setAccept(String name);

    public boolean setStart(String name);

    public void addSigma(char symbol);

    public void addToTape(char sybmol);

    public abstract boolean accepts(String s);

    public Set<Character> getSigma();
    
    public Set<Character> getTapeAlphabet();

    public State getState(String name);

    public boolean isAccept(String name);

    public boolean isStart(String name);
}
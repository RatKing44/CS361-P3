package turing.TM;
import java.io.*;
import java.util.*;

import turing.State;
import turing.TM.TuringState.Transition;

public class TMSimulator implements TuringInterfaceExtra {
    public static int totalStates;
    public static int totalAlphabet;
    public static String[] transitions = new String[50];
    public static String inputString = null;

    public static void main(String[] args) {
        readTMFile("file0.txt");
        System.out.println("Total States: " + totalStates);
        System.out.println("Total Alphabet: " + totalAlphabet);
        System.out.println("Transitions: ");
        for (String transition : transitions) {
            if (transition == null) break; // Stop if we reach an empty transition
            System.out.println(transition);
        }
        System.out.println("Input String: " + inputString);
        
    }

    public static void readTMFile(String fileName){
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String line = fileScanner.nextLine();
            totalStates = Integer.parseInt(line);
            totalAlphabet = Integer.parseInt(fileScanner.nextLine());
            int i = 0;
            // Begin reading transitions
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                if (line.contains(",")){
                    transitions[i] = "State:" + (i / (totalAlphabet + 1)) + "  " + line;
                    i++;
                }
                //Last line contains the imput string
                else {
                    inputString = line;
                }
            }
            fileScanner.close();
            
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    @Override
    public boolean addState(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addState'");
    }

    @Override
    public boolean setAccept(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAccept'");
    }

    @Override
    public boolean setStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStart'");
    }

    @Override
    public void addSigma(char symbol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addSigma'");
    }

    @Override
    public void addToTape(char sybmol) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToTape'");
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accepts'");
    }

    @Override
    public Set<Character> getSigma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSigma'");
    }

    @Override
    public State getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public boolean isAccept(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isAccept'");
    }

    @Override
    public boolean isStart(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStart'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char readSymb, char writeSymb, char moveSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }
}
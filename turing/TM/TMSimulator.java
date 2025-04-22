package turing.TM;
import java.io.*;
import java.util.*;

import turing.State;
import turing.TM.TuringState.Transition;

public class TMSimulator implements TuringInterfaceExtra {
    private LinkedHashSet<State> states;
    private LinkedHashSet<Character> sigma;
    private LinkedHashSet<Character> tapeAlphabet;
    //private static char tape[];
    private static HashMap<Integer, Integer> tape;
    private State startingState;
    private State acceptState;
    private int numStates;
    private int numSymb;
    private String input;
    private static int index;

    public TMSimulator(String filePath){
        states = new LinkedHashSet<>();
        sigma = new LinkedHashSet<>();
        tapeAlphabet = new LinkedHashSet<>();
        //tape = new char[50000];
        tape = new HashMap<>();
        index = 0;
        try{
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            numStates = Integer.parseInt(scanner.nextLine());

            //adding states
            for(int i = 0; i < numStates; i++){
                states.add(new TuringState(String.valueOf(i)));
                if(i==0){
                    setStart(String.valueOf(i));
                }else if(i == numStates - 1){
                    setAccept(String.valueOf(i));
                }
            }

            //adding characters to language
            numSymb = Integer.parseInt(scanner.nextLine());
            for(int i = 0; i <= numSymb; i++){
                addSigma((char) (i + '0'));
            }


            //adding transitions
            for(int i = 0; i < numStates - 1; i++){
               for(int j = 0; j < numSymb + 1; j++){
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    addTransition(String.valueOf(i), Set.of(parts[0]), (char)(j + '0'), parts[1].charAt(0), parts[2].charAt(0));
               }
            }

            if(scanner.hasNext()){
                input = scanner.nextLine();
                for(int i = 0; i < input.length(); i++){
                    //tape[i] = input.charAt(i);
                    tape.put(i, (int)input.charAt(i));
                }
            }
            System.out.println("For input string: " + input + "\nAccept?:" + (accepts(input) ? "Yes" : "No"));

            scanner.close();
        
        }catch (FileNotFoundException e){
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Usage: java TMSimulator <file-path>");
            System.exit(1);
        }
        String filePath = args[0];
        new TMSimulator(filePath);
        System.out.println(index);
        System.out.println(printTape());
        
    }


    @Override
    public boolean addState(String name) {
        
        boolean response = false;
        if(states.size() == 0){
            response = true;
            states.add(new TuringState(name));
        }else{
            for(State s : states){
                if(s.getName().equals(name)){
                    response = false;
                    break;
                }else{
                    response = true;
                }
            }
            if(response){
                states.add(new TuringState(name));
            }
        }
        return response;
    }

    @Override
    public boolean setAccept(String name) {
        boolean response = false;
        for(State s : states){
            if(s.getName().equals(name)){
                acceptState = s;
                response = true;
                break;
            }else{
                response = false;
            }
        }
        return response;
    }

    @Override
    public boolean setStart(String name) {
        boolean response = false;
        for(State s : states){
            if(s.getName().equals(name)){
                startingState = s;
                response = true;
                break;
            }else{
                response = false;
            }
        }
        return response;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public void addToTape(char sybmol) {
        tapeAlphabet.add(sybmol);
    }

    @Override
    public boolean accepts(String s) {
        //index = 0;
        TuringState state = (TuringState) startingState;
        while(!isAccept(state.getName())){
            int input = 0;
            try{
                //input = tape[index -25000];
                input = tape.get(index);
            }catch(Exception e){
                 tape.put(index, 0);
                //tape[index  + 25000] = 0;
            }
            char characterInput;
            if(input == 0){
                characterInput = (char) (input + '0');
            }else{
                characterInput = (char) (input);
            }
            Transition transition = getToState(state, characterInput);
            if(transition == null){
                return false;
            }
            //tape[index+25000] = transition.getWriteSymb();
            tape.put(index, (int)transition.getWriteSymb());
            state = (TuringState) transition.getToState();
            if(transition.getMoveSymb() == 'R'){
                index++;
            }else if(transition.getMoveSymb() == 'L'){
                index--;    
            }
        }
        return true;
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        State chosenState = null;
        for(State s : states){
            if(s.getName().equals(name)){
                chosenState = s;
                break;
            }
        }
        return chosenState;
       
    }

    @Override
    public boolean isAccept(String name) {
        boolean response = false;
        if(acceptState.getName().equals(name)){
            response = true;
        }else{
            response = false;
        }
        return response;
    }

    @Override
    public boolean isStart(String name) {
        boolean response = false;
        if(startingState.getName().equals(name)){
            response = true;
        }else{
            response = false;
        }
        return response;
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char readSymb, char writeSymb, char moveSymb) {
        TuringState from = (TuringState) getState(fromState);
        for(String stateName: toStates){
            TuringState to = (TuringState) getState(stateName);
            from.addTransition(to, readSymb, writeSymb, moveSymb);
        } 
        return true;
    }

    @Override
    public Transition getToState(TuringState from, char onSymb) {
        return from.getTransition(onSymb);
    }

    @Override
    public Set<Character> getTapeAlphabet() {
        return tapeAlphabet;
    }

    public static String printTape(){
        StringBuilder str = new StringBuilder();
        str.append(tape.size() + "\n");
        int sum = 0;
        List<Integer> set = new LinkedList<>(tape.keySet());
        Collections.sort(set);
        for(int key: set){
            int value = tape.get(key);
            str.append((char) value);
            sum = sum + Integer.parseInt(String.valueOf((char)value));

        }

        // for (int i = 0; i < tape.length; i++) {
        //     str.append(tape[i]);
        //     sum += tape[i];
        // }
        str.append("\n" + sum + "\n");
        return str.toString();
    }
}
package turing;

public abstract class State {
    private String name;

    public State(){

    }

    public State(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return String.valueOf(name);
    }
}

import java.io.*;
import java.util.Scanner;

public class TMSimulator {
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
}
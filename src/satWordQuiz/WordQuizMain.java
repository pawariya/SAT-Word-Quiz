package satWordQuiz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordQuizMain {

    public final static int NUM_TWO = 2;
    private static ArrayList<WordSorter> records = new ArrayList<>();

    public static void main(String[] args) {

//        String currentWorkingDir = System.getProperty("user.dir");
//        System.out.println("Current working directory: " + currentWorkingDir);

        try {
            File file = new File("./src/satWordQuiz/vocabulary.txt");
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                String wordData = scnr.nextLine();
                loadData(wordData);

            }
            // scnr.close();
        } catch (FileNotFoundException e) {
            System.out.println("This file does not exist");
            e.printStackTrace();

        }

        initalSetupForQuiz();
//        for (int i = 0; i < records.size(); i++) {
//            System.out.println(records.get(i).getWord());
//            System.out.println(records.get(i).getDefinition());
//
//        }
    }

    public static void loadData(String line) {

        String[] parts = line.split(" - ");
        if (parts.length == NUM_TWO) {
            String word = parts[0].trim();
            String definition = parts[1].trim();
            records.add(new WordSorter(word, definition));
        } else {
            System.out.println("Skipping malformed line: " + line);
        }
    }

    public static void initalSetupForQuiz() {
        Random rand = new Random();
        Scanner scnr = new Scanner(System.in);

        System.out.println("Welcome to the SAT Words Quiz! Please type the number of questions you'd like to answer:");
        int numQuestions = scnr.nextInt();
        scnr.nextLine();
        // int questionCounter = numQuestions;

        while (numQuestions != 0) {

            int selectedIndex = rand.nextInt(records.size());

            // grabs a random word by index
            WordSorter selectedWord = records.get(selectedIndex);

            while (selectedWord.isVisited()) {
                selectedIndex = rand.nextInt(records.size());
                
                // grabs a random word by index
                selectedWord = records.get(selectedIndex);
                //System.out.println("Skipping duplicate question " + selectedWord.getWord() );
            }
            if (selectedWord.isVisited() == false) {
                selectedWord.setVisited(true);
            }

            records.set(selectedIndex, selectedWord);

            String correctDefinition = selectedWord.getDefinition(); // grabs the corresponding correct definition

            ArrayList<String> options = new ArrayList<>();
            options.add(correctDefinition);

            while (options.size() < 4) {
                String wrongRandomOptions = records.get(rand.nextInt(records.size())).getDefinition();
                if (!options.contains(wrongRandomOptions)) {
                    options.add(wrongRandomOptions);
                }
            }

            Collections.shuffle(options);

            System.out.println("Your word is: " + selectedWord.getWord());
            char letter = 'a';
            for (int i = 0; i < options.size(); i++) {
                System.out.println(letter + ") " + options.get(i));
                letter++;
                // numQuestions--;
            }

            System.out.print("Please type in your answer (a, b, c, d) ");
            String userAnswer = scnr.nextLine();
            userAnswer = userAnswer.toLowerCase();
            //System.out.println("User entered: " + userAnswer);
            while (!userAnswer.equals("a") && !userAnswer.equals("b") && !userAnswer.equals("c")
                    && !userAnswer.equals("d")) {
                System.out.println("Invalid answer, please try again.");
                userAnswer = scnr.nextLine();
            }
            numQuestions--;

            char correctAnswer = ' ';

            for (int i = 0; i < options.size(); i++) {
                if (options.get(i).equals(correctDefinition)) {
                    correctAnswer = (char) (i + 97);
                    break;
                }

            }

           // System.out.println(correctAnswer);

            // Character.toString(correctAnswer);

            if (userAnswer.equals(Character.toString(correctAnswer))) {
                System.out.println("Correct!");
                System.out.println();
            } else {
                System.out.println("Incorrect, the answer was: " + correctDefinition);
                System.out.println();
            }

        }

    }

}

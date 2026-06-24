import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static String[] words = {"JAVA", "LAPTOP", "SONA", "CODING", "LAPTOP", "PYTHON"};
    static String wordToGuess;
    static char[] guessedWord;
    static int attemptsLeft = 6;
    static String wrongGuesses = "";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        // Random word choose karo
        wordToGuess = words[rand.nextInt(words.length)];
        guessedWord = new char[wordToGuess.length()];

        // Shuru me saare dash dikhao _ _ _ _
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        System.out.println("=== SONA KA HANGMAN GAME ===");
        System.out.println("Maine ek " + wordToGuess.length() + " letter ka word socha hai.");
        System.out.println("6 galat guess pe GAME OVER 😱");

        // Game loop
        while (attemptsLeft > 0 &&!isWordGuessed()) {
            printGameStatus();
            System.out.print("\nEk letter guess karo: ");
            char guess = sc.next().toUpperCase().charAt(0); // Capital me convert

            if (checkGuess(guess)) {
                System.out.println("Sahi hai! 🔥");
            } else {
                attemptsLeft--;
                wrongGuesses += guess + " ";
                System.out.println("Galat! 😭 Attempts bache: " + attemptsLeft);
            }
        }

        // Game khatam
        if (isWordGuessed()) {
            System.out.println("\n🎉🎉 JEET GAYI SONA! 🎉🎉");
            System.out.println("Sahi word tha: " + wordToGuess);
        } else {
            System.out.println("\n💀 HANG HO GAYA! GAME OVER 💀");
            System.out.println("Sahi word tha: " + wordToGuess);
        }
        sc.close();
    }

    // Game ka status print karo
    static void printGameStatus() {
        System.out.print("\nWord: ");
        for (char c : guessedWord) {
            System.out.print(c + " ");
        }
        System.out.println("\nGalat guesses: " + wrongGuesses);
        System.out.println("Attempts left: " + attemptsLeft);
    }

    // Guess check karo
    static boolean checkGuess(char guess) {
        boolean found = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess) {
                guessedWord[i] = guess; // Sahi jagah pe letter daal do
                found = true;
            }
        }
        return found;
    }

    // Kya pura word guess ho gaya?
    static boolean isWordGuessed() {
        for (char c : guessedWord) {
            if (c == '_') return false; // Abhi bhi dash baki hai
        }
        return true;
    }
}
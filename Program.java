import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Program {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("1. Play the Game");
            System.out.println("2. See the Rules");
            System.out.println("3. See Past Scores");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice (1-4): ");

            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> playGame(scanner);
                    case 2 -> displayRules();
                    case 3 -> displayPastScores();
                    case 4 -> System.out.println("Exiting the game");
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } 
            catch (InputMismatchException e) {
                System.err.println("Please enter a number!");
                scanner.next();
            }
        }
        scanner.close();
    }

    //Game Function
    public static void playGame(Scanner scanner) {
        Random random = new Random();
        int gameNumber = getLastGameNumber() + 1;
        int randomNumber = random.nextInt(100) + 1;
        int guessTotal = 0;
        int guess = 0;

        System.out.println("Starting a new game...");

        //Main game loop
        while (guess != randomNumber) {
            System.out.print("Enter your guess (between 1 and 100): ");
            try {
                guess = scanner.nextInt();
                guessTotal++;
                calcGuess(guess, randomNumber);
            } catch (InputMismatchException e) {
                System.err.println("Please enter a number!");
                scanner.next();
            }
        }

        //End game and record the results
        endGame(guessTotal);
        saveGameData(gameNumber, guessTotal);
    }

    //This Function displays the rules
    public static void displayRules() {
        System.out.println("Game Rules:");
        System.out.println("1. You will guess a number between 1 and 100.");
        System.out.println("2. After each guess, you will receive a hint whether your guess is too low or too high.");
        System.out.println("3. If your guess is very far from the correct number(at least 25 integers), you'll be told it's 'very low' or 'very high'.");
        System.out.println("4. The game will continue until you guess the correct number.");
        System.out.println();
    }

    //AI Assisted: This function displays past scores from the CSV file
    public static void displayPastScores() {
        System.out.println("Past Scores:");
        try (BufferedReader br = new BufferedReader(new FileReader("game_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading game_data.csv: " + e.getMessage());
        }
        System.out.println();
    }

    //This function checks the user guess against the answer
    public static void calcGuess(int guess, int randomNumber) {
        if (guess <= 0 || guess > 100) {
            System.out.println("Your guess is outside the bounds of the game.");
        } else if (guess < randomNumber) {
            if (randomNumber - guess >= 25) {
                System.out.println("Your guess is very low! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }
        } else if (guess > randomNumber) {
            if (guess - randomNumber >= 25) {
                System.out.println("Your guess is very high! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }
    }

    //This function is at the end of the game and shows the total guesses
    public static void endGame(int guessTotal) {
        System.out.println("Congratulations! You guessed the number!");
        System.out.println("It took you " + guessTotal + " guesses.");
    }

    //This function saves the game number and score to a CSV file
    public static void saveGameData(int gameNumber, int guessTotal) {
        try (FileWriter writer = new FileWriter("game_data.csv", true)) {
            writer.append(gameNumber + "," + guessTotal + "\n");
            System.out.println("Game data saved to game_data.csv.");
        } catch (IOException e) {
            System.err.println("Error saving game data: " + e.getMessage());
        }
    }

    //This function reads the CSV file and get the last recorded game number
    public static int getLastGameNumber() {
        int lastGameNumber = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("game_data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    lastGameNumber = Integer.parseInt(values[0]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game_data.csv: " + e.getMessage());
        }
        return lastGameNumber;
    }
}

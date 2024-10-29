import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int guessTotal = 0;
        int guess = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");

        //This is the main game loop. It cannot move to the final section of the program until the guess is correct
        while (guess != randomNumber) {
            // Take user input
            System.out.print("Enter your guess (between 1 and 100): ");
            try {
                guess = scanner.nextInt();
                guessTotal++;
                calcGuess(guess, randomNumber); 
            } 
            catch (InputMismatchException e) {
                System.err.println("Please enter a number!");
                scanner.next();
            }

        }
        endGame(guessTotal);
        scanner.close();
    }
    //This function checks the guess in relation to the answer and returns a message depending on if it is low or high. 
    //It will show a "very low/high" message if the guess is at least 25 integers away from the answer. 
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
    
    //This is the end of the game, it shows the total amount of guesses as well. 
    public static void endGame(int guessTotal) {
        System.out.println("Congratulations! You guessed the number!");
        System.out.println("It took you " + guessTotal + " guesses.");
    }
}

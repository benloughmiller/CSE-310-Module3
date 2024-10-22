import java.util.Random;

public class Program {
    public static void main(String[] args) {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int guessTotal = 0;
        int guess = 0;
        System.out.println("Welcome to the Number Guessing Game!");
        //Game Loop
        while (guess != randomNumber) {
            //Take user input
            calcGuess(guess, randomNumber);
            guessTotal++;
        }

        endGame(guessTotal);
    }
    public static void calcGuess(int guess, int randomNumber) {
        
    }
    public static void endGame(int guessTotal) {
        System.out.println("You guessed the number!");
        System.out.println("It took you " + guessTotal + " guesses.");
    }
}
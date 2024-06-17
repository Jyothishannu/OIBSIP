import java.util.Random;
import java.util.Scanner;

 class GuessTheNumber {
    // Previous code...

    private int numberToGuess;
    private int maxRange;
    private int attempts;
    private int score;
    private int maxAttempts;

    public GuessTheNumber(int maxRange) {
        this.maxRange = maxRange;
        Random random = new Random();
        this.numberToGuess = random.nextInt(maxRange) + 1;
        this.attempts = 0;
        this.score=0;
        this.maxAttempts=10;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean hasGuessedCorrectly = false;

        while (!hasGuessedCorrectly && attempts<maxAttempts) {
            System.out.print("Enter your guess (between 1 and " + maxRange + "): ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You've guessed the number in " + attempts + " attempts.");
                hasGuessedCorrectly = true;
            }

               else if (userGuess < numberToGuess) {
                System.out.println("The number is higher.");
            } else {
                System.out.println("The number is lower.");
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry, you didn't guess the number. The correct number was: " + numberToGuess);
        } else {
            score += calculateScore();
            System.out.println("Your current score is: " + score);
        }

        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next();
        if (playAgain.equalsIgnoreCase("yes")) {
            resetGame();
            playGame();
        } else {
            System.out.println("Thank you for playing!");
        }


        scanner.close();
    }
    public int calculateScore() {
        // Example scoring system: more points for fewer attempts
        return maxRange - (attempts*10); // Adjust as needed
    }

    public void resetGame() {
        Random random = new Random();
        this.numberToGuess = random.nextInt(maxRange) + 1;
        this.attempts = 0;
    }


}

  class game
{

    public static void main(String args[]) {
        GuessTheNumber game = new GuessTheNumber(100); // Change the range as needed
        game.playGame();
    }
}

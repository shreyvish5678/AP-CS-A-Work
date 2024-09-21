/**
 * PigGame class
 * Implements a dice game where a human competes against the computer.
 * The goal is to reach 100 points first by either rolling or holding.
 * The computer holds when it reaches at least 20 points.
 *
 * @author Shrey Vishen
 * @since September 2024
 */
public class PigGame {
   // Field for rolling dice
   private Dice die = new Dice();
   // Scores for human and computer players
   private int humanScore;
   private int computerScore;

   /**
    * Constructor initializes scores to zero for both players.
    * @param none
    * @return none
    */
   public PigGame() {
       this.humanScore = 0;
       this.computerScore = 0;
   }

   /**
    * Main method to launch the game.
    *
    * @param none
    * @return none
    */
   public static void main(String[] args) {
       PigGame game = new PigGame();
       game.run();
   }

   /**
    * Runs the game flow, either starting a game or displaying statistics.
    * @param none
    * @return none
    */
   public void run() {
       printIntroduction();
       String choice = Prompt.getString("Play game or Statistics (p or s)");
       if (choice.equalsIgnoreCase("s")) {
           runStatistics();
       } else {
           playGame();
       }
   }

   /**
    * Displays the introduction and rules of the Pig game.
    * @param none
    * @return none
    */
   public void printIntroduction() {
       System.out.println("\n");
       System.out.println("______ _         _____");
       System.out.println("| ___ (_)       |  __ \\");
       System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
       System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
       System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
       System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
       System.out.println("          __/ |");
       System.out.println("         |___/");
       System.out.println("\nThe Pig Game is human vs computer. Each takes a turn rolling a die and the first to score");
       System.out.println("100 points wins. A player can either ROLL or HOLD. A turn works this way:");
       System.out.println("\n\tROLL:\t2 through 6: add points to turn total, player's turn continues");
       System.out.println("\t\t1: player loses turn");
       System.out.println("\tHOLD:\tturn total is added to player's score, turn goes to other player");
       System.out.println("\n");
   }

   /**
    * Handles the flow of the entire game, alternating turns between human and computer.
    * The game continues until one player reaches 100 points.
    * @param none
    * @return none
    */
   public void playGame() {
       boolean gameOver = false;

       while (!gameOver) {
           humanTurn();
           if (humanScore >= 100) {
               gameOver = true;
           } else {
               computerTurn();
               if (computerScore >= 100) {
                   gameOver = true;
               }
           }
       }

       if (humanScore >= 100) {
           System.out.println("\nCongratulations!!! YOU WON!!!");
       } else {
           System.out.println("\nToo bad. COMPUTER WON.");
       }

       System.out.println("\nThanks for playing the Pig Game!!!\n");
   }

   /**
    * Executes the logic for the human player's turn.
    * The player can choose to roll the dice or hold and add points to their total score.
    * @param none
    * @return none
    */
   public void humanTurn() {
       boolean turnOver = false;
       int turnScore = 0;

       while (!turnOver) {
           System.out.println("\nYour turn score:  " + turnScore);
           System.out.println("Your total score: " + humanScore);
           String action = Prompt.getString("(r)oll or (h)old");

           if (action.equalsIgnoreCase("h")) {
               humanScore += turnScore;
               System.out.println("\nYou HOLD");
               System.out.println("Your total score: " + humanScore);
               turnOver = true;
           } else if (action.equalsIgnoreCase("r")) {
               System.out.println("\nYou ROLL");
               die.roll();
               die.printDice();
               if (die.getValue() == 1) {
                   System.out.println("\nYou LOSE your turn.");
                   System.out.println("Your total score: " + humanScore);
                   turnOver = true;
               } else {
                   turnScore += die.getValue();
               }
           }
       }
   }

   /**
    * Executes the logic for the computer's turn.
    * The computer rolls until it has accumulated at least 20 points, then it holds.
    * @param none
    * @return none
    */
   public void computerTurn() {
       boolean turnOver = false;
       int turnScore = 0;

       while (!turnOver) {
           System.out.println("\nComputer turn score:  " + turnScore);
           System.out.println("Computer total score: " + computerScore);
           Prompt.getString("Press enter for computer turn");

           if (turnScore < 20 && turnScore + computerScore < 100) {
               System.out.println("\nComputer will ROLL");
               die.roll();
               die.printDice();
               if (die.getValue() == 1) {
                   System.out.println("\nComputer loses turn.");
                   System.out.println("Computer total score: " + computerScore);
                   turnOver = true;
               } else {
                   turnScore += die.getValue();
               }
           } else {
               computerScore += turnScore;
               System.out.println("\nComputer will HOLD");
               System.out.println("Computer total score: " + computerScore);
               turnOver = true;
           }
       }
   }

   /**
    * Runs a statistical analysis on the game, simulating a number of turns
    * to determine estimated probabilities for various scores.
    * @param none
    * @return none
    */
   public void runStatistics() {
       System.out.println("\nRun statistical analysis - \"Hold at 20\"\n");
       int numTurns = Prompt.getInt("Number of turns", 1000, 1000000);
       int[] scoreCounts = new int[30];

       // Initialize score counts array
       for (int i = 0; i < scoreCounts.length; i++) {
           scoreCounts[i] = 0;
       }

       // Simulate each turn
       for (int i = 0; i < numTurns; i++) {
           int turnScore = 0;
           boolean turnOver = false;

           while (!turnOver) {
               if (turnScore < 20) {
                   die.roll();
                   if (die.getValue() == 1) {
                       turnOver = true;
                   } else {
                       turnScore += die.getValue();
                   }
               } else {
                   turnOver = true;
               }
           }

           // Record score in appropriate bucket
           if (die.getValue() == 1) {
               scoreCounts[0]++;
           } else {
               scoreCounts[turnScore]++;
           }
       }

       // Output statistical results
       System.out.println("\nScore\tEstimated Probability");
       System.out.printf("0\t%8.6f\n", (double) scoreCounts[0] / numTurns);

       for (int i = 20; i < 26; i++) {
           System.out.printf("%d\t%8.6f\n", i, (double) scoreCounts[i] / numTurns);
       }

       System.out.println();
   }
}

import java.util.ArrayList;

/*
/    Author:
/        Hollister Ream
*/

public class TestCardGame {
    public static void main (String args[]) {
        playGames(4, true);
        System.out.println();
        playGames(2000, true);
        // for verification of acuracy
        /*System.out.println();
        playGames(1000000, false);*/
    }

    public static void playGames (int games, boolean logEnabled) {
        int totalScore = 0;
        System.out.println(">>> Printed in Constructor");
        CardGame game = new CardGame();
        for (int i = 0; i < games; i++) {
            game.initGame();
            game.playGame();
            // Prints all player's score after each round.!WARNING! may cause excessive clutter in output dialog if enabled
            if (logEnabled) {
                System.out.println(">>> playGame() " + game.cleanPlayerNames());
            }
        }
        System.out.println(">>> Printing Total Score and Player % in TestCardGame");
        ArrayList<CardPlayer> players = game.getPlayers();
        for (CardPlayer p : players) {
            totalScore += p.getScore();
        }
        System.out.println("Total score = " + totalScore);
        for (CardPlayer p : players) {
            System.out.println(p.getName() + " " + (((double)(p.getScore()) / (double)(totalScore)) * 100) + "%");
        }
    }
}
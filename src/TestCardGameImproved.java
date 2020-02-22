/*
/    Author:
/        Hollister Ream
*/

import java.util.ArrayList;

public class TestCardGameImproved {
    public static void main (String args[]) {
        ArrayList<CardPlayer> players = new ArrayList<CardPlayer>();
        /*
        /
        /       This is where you decide what players to have. !WARNING! must be at least 4 players. For safety, do not remove these 4 initial players
        /
        */
        players.add(new CardPlayer("CardPlayer1", 0, new ArrayList<Card>()));
        players.add(new CardPlayer("CardPlayer2", 0, new ArrayList<Card>()));
        players.add(new CardPlayer("CardPlayer3", 0, new ArrayList<Card>()));
        players.add(new CardPlayer("CardPlayer4", 0, new ArrayList<Card>()));

        playGames(4, false, players);
        //System.out.println();
        //playGames(2000, false, players);
        // for verification of acuracy
        /*System.out.println();
        playGames(1000000, false);*/
        System.out.println();
    }

    public static void playGames (int games, boolean logEnabled, ArrayList<CardPlayer> players) {
        int totalScore = 0;

        System.out.println(">>> Printed in Constructor");

        // instantiate game and print players
        CardGameImproved game = new CardGameImproved("Tournament", players, 0);

        // actually play the games
        for (int i = 0; i < games; i++) {
            game.playGameImproved();
            // Prints all player's score after each round of games. !WARNING! may cause excessive clutter in output dialog if enabled
            if (logEnabled) {
                System.out.println(">>> playGame() " + game.cleanPlayerNames());
            }
        }

        System.out.println(">>> Printing Total Score and Player % in TestCardGame");

        // get total score
        ArrayList<CardPlayer> allPlayers = game.getPlayers();
        for (CardPlayer p : allPlayers) {
            totalScore += p.getScore();
        }

        // print all scores
        System.out.println("Total score = " + totalScore);
        for (CardPlayer p : allPlayers) {
            System.out.println(p.getName() + " took " + (((double)(p.getScore()) / (double)(totalScore)) * 100) + "% of points");
        }
    }
}
/*
/    Author:
/        Hollister Ream
*/

import java.io.IOException;
import java.util.ArrayList;

public class TestCardGameImproved {
    public static void main(String args[]) {
        try {
            //this line of code from https://stackoverflow.com/questions/2979383/java-clear-the-console
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        ArrayList<CardPlayer> players = new ArrayList<CardPlayer>();
        /*
        /
        /       This is where you decide what players to have. !WARNING! must be at least 4 players.
        /
        */
        players.add(new CardPlayer("Player1", 0, new ArrayList<Card>()));
        players.add(new CardPlayer("Player2", 0, new ArrayList<Card>()));
        players.add(new CardPlayerLevel1("Player3", 0, new ArrayList<Card>()));
        players.add(new CardPlayerAI("Player4", 0, new ArrayList<Card>()));
        
        playGames(200, 200, false, players);
        System.out.println();
    }
    
    public static void playGames (int games, int progressSegments, boolean logEnabled, ArrayList<CardPlayer> players) {
        int totalScore = 0;
        int segementsCompleted = 0;
        
        System.out.println(">>> Printed in Constructor");
        
        // instantiate game and print players
        CardGameImproved game = new CardGameImproved("Tournament", players, 0);
        System.out.println();
        
        // actually play the games
        for (int i = 0; i < games; i++) {
            // prints the current progress of the operation if enabled
            if (progressSegments != 0) {
                if (i%(games/progressSegments) == 0) {
                    segementsCompleted++;
                    
                    // erases previous progress print
                    System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                    System.out.print("                              ");
                    System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");

                    String str = "Computed " + (((double)((int)(((double)segementsCompleted / (double)progressSegments) * 10000)))/100) + "%";
                    System.out.print(str);

                    if (segementsCompleted == progressSegments) {
                        System.out.println();
                    }
                }
            }
            
            game.playGameImproved();
            
            // Prints all player's score after each round of games. !WARNING! may cause excessive clutter in output dialog if enabled
            if (logEnabled) {
                System.out.println(">>> playGameImproved() " + game.cleanPlayerNames());
            }
        }
        
        System.out.println("\n>>> Printing Total Score and Player % in TestCardGame");
        
        // get total score
        ArrayList<CardPlayer> allPlayers = game.getPlayers();
        for (CardPlayer p : allPlayers) {
            totalScore += p.getScore();
        }
        
        // print all scores
        System.out.println("Total score = " + totalScore);
        for (CardPlayer p : allPlayers) {
            String percent = (((double)(p.getScore()) / (double)(totalScore)) * 100) + "";
            System.out.println(p.getName() + " took " + percent.substring(0, 6) + "% of points");
        }
    }
}
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
        players.add(new CardPlayerLevel1("CardPlayerAdvanced", 0, new ArrayList<Card>()));
        players.add(new CardPlayerLevel1("CardPlayerAdvanced", 0, new ArrayList<Card>()));
        
        playGames(10000, 1000, false, players);
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
                    
                    if (segementsCompleted != 1) {
                        // code segment from https://stackoverflow.com/questions/7522022/how-to-delete-stuff-printed-to-console-by-system-out-println, for erasing lines in consle
                        System.out.print(String.format("\033[%dA",1));
                        System.out.print("\033[2K");
                    }
                    
                    String str = "Computed " + (((double)((int)(((double)segementsCompleted / (double)progressSegments) * 10000)))/100) + "%";
                    System.out.println(str);
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
            System.out.println(p.getName() + " took " + (((double)(p.getScore()) / (double)(totalScore)) * 100) + "% of points");
        }
    }
}
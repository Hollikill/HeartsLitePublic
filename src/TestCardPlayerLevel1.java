/*
/    Code Forked from TestCardPlayer.java
/
/    TestCardPlayer Authors:
/        Hollister Ream
/        Gabriel Keller
/
/    TestCardPlayerLevel1 Authors:
/        Hollister Ream
*/

import java.util.ArrayList;

public class TestCardPlayerLevel1 {
    public static void main(String[] args) {
        ArrayList<Card> round = new ArrayList<>();
        ArrayList<Card> game = new ArrayList<>();
        CardPlayerLevel1 cardPlayer = new CardPlayerLevel1("Kara Jones", 0, new ArrayList<Card>());

        //Card Player
        System.out.println("\n --- Testing inherited CardPlayer functionality --- \n");
        System.out.println("New CardPlayer -> " + cardPlayer);
        cardPlayer.addCard(new Card("3", "diamonds", 3));
        cardPlayer.addCard(new Card("7", "hearts", 7));
        cardPlayer.addCard(new Card("6", "spades", 6));
        cardPlayer.addCard(new Card("7", "spades", 7));
        cardPlayer.addCard(new Card("2", "clubs", 2));
        cardPlayer.addCard(new Card("3", "clubs", 3));
        cardPlayer.addCard(new Card("9", "hearts", 9));
        System.out.println("After adding 7 specific cards. Player -> " + cardPlayer);
        Card one = cardPlayer.playCard(5);
        cardPlayer.updateScore(27);
        System.out.println("Played " + one + " at index 5 and updated score to 27 points. Player -> " + cardPlayer);
        System.out.println("Cards played in round -> " + round);
        System.out.println("Cards played in game -> " + game);

        //Card Player Level 1
        System.out.println("\n --- Testing new CardPlayerLevel1 functionality --- \n");
        System.out.println("1 :\nIf the player has a card that matches the suit that was led, the player plays a random card that matches that suit\n2 :\nIf the player does not have a card matching the suit that was led and the player has the Queen of Spades, the player plays it\n3 :\nIf the player does not have a card matching the suit that was led and the player has at least one heart, the player plays their highest heart.\n4 :\nIf the player does not have a card matching the suit that was led or any hearts, the player plays any other random card in their hand.\n");

        // part 1
        System.out.println("Demonstrating Part 1 with two spades in hand and the led suit spades");
        Card spadesEight = new Card("spades", 8);
        System.out.println("Adding -> " + spadesEight.getCleanName() + " to round and game");
        round.clear();
        round.add(spadesEight);
        game.add(spadesEight);
        System.out.println("Player state -> " + cardPlayer);
        Card chosenOne = cardPlayer.chooseCard(round, game);
        System.out.println("Player state -> " + cardPlayer + ", played the " + chosenOne.getCleanName());

        // part 2
        System.out.println("\nDemonstrating Part 2 with no diamonds and a queen of spades in hand and the led suit diamond");
        System.out.println("Cleared round");
        System.out.println("Cards played in round -> " + round);
        round.clear();
        System.out.println("Added a diamond to round and game");
        round.add(new Card("4", "diamonds", 4));
        game.add(new Card("4", "diamonds", 4));
        System.out.println("Cards played in round -> " + round);
        System.out.println("Removed the " + cardPlayer.getHand().get(0).getCleanName() + " from player hand, no diamonds in hand");
        cardPlayer.getHand().remove(0);
        Card queenSpades = new Card("Q", "spades", 12);
        cardPlayer.getHand().add(queenSpades);
        System.out.println("Added the " + queenSpades.getCleanName() + " to player hand");
        System.out.println("Player state -> " + cardPlayer);
        Card chosenTwo = cardPlayer.chooseCard(round, game);
        System.out.println("Player state -> " + cardPlayer + ", played the " + chosenTwo.getCleanName());

        // part 3
        System.out.println("\nDemonstrating Part 3 with the led suit diamonds and no diamonds and multiple hearts in hand");
        System.out.println("Cards played in round -> " + round);
        System.out.println("Player state -> " + cardPlayer);
        Card chosenThree = cardPlayer.chooseCard(round, game);
        System.out.println("Player state -> " + cardPlayer + ", played the " + chosenThree.getCleanName());

        // part 4
        System.out.println("\nDemonstrating Part 4 with the led suit diamond and no diamonds or hearts in hand");
        System.out.println("Player state -> " + cardPlayer);
        System.out.println("Removing all hearts from hand");
        cardPlayer.getHand().remove(0);
        System.out.println("Player state -> " + cardPlayer);
        Card chosenFour = cardPlayer.chooseCard(round, game);
        System.out.println("Player state -> " + cardPlayer + ", played the " + chosenFour.getCleanName());
    }
}
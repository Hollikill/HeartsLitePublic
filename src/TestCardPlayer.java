/*
/    Authors:
/        Hollister Ream
/        Gabriel Keller
*/

import java.util.ArrayList;

public class TestCardPlayer {
    public static void main(String[] args) {
        ArrayList<Card> round = new ArrayList<>();
        ArrayList<Card> game = new ArrayList<>();
        CardPlayer cardPlayer = new CardPlayer("Kara Jones", 0, new ArrayList<Card>());
        System.out.println("New CardPlayer -> " + cardPlayer);
        cardPlayer.addCard(new Card("3", "diamonds", 3));
        cardPlayer.addCard(new Card("A", "hearts", 14));
        cardPlayer.addCard(new Card("6", "spades", 6));
        cardPlayer.addCard(new Card("7", "spades", 7));
        cardPlayer.addCard(new Card("2", "clubs", 2));
        cardPlayer.addCard(new Card("3", "clubs", 3));
        System.out.println("After adding 6 specific cards. Player -> " + cardPlayer);
        Card one = cardPlayer.playCard(5);
        cardPlayer.updateScore(27);
        System.out.println("Played " + one + " at index 5 and updated score to 27 points. Player -> " + cardPlayer);
        System.out.println("Cards played in round -> " + round);
        System.out.println("Cards played in game -> " + game);
        Card two = cardPlayer.chooseCard(round, game);
        System.out.println("Chose 2 of clubs -> " + two + " Player -> " + cardPlayer);
        round.clear();
        round.add(new Card("10", "spades", 10));
        System.out.println("Cards played in round -> " + round);
        Card c = cardPlayer.chooseCard(round, game);
        System.out.println("RANDOMLY chosen spade -> " + c + " Player -> " + cardPlayer);
        round.clear();
        round.add(new Card("9", "clubs", 9));
        System.out.println("Cards played in round -> " + round);

        Card heart = cardPlayer.chooseCard(round, game);
        System.out.println("heart chosen -> " + heart + " Player -> " + cardPlayer);

        round.clear();
        System.out.println("Cards played in round -> " + round);
        System.out.println("RANDOMLY chosen card -> " + cardPlayer.chooseCard(round, game));
    }
}

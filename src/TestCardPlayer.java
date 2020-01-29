import java.util.ArrayList;

public class TestCardPlayer {
    public static void main(String[] args) {
        ArrayList<Card> round = new ArrayList<Card>();
        ArrayList<Card> game = new ArrayList<Card>();
        CardPlayer cardPlayer = new CardPlayer("Kara Jones", 0, new ArrayList<Card>());
        System.out.println("New CardPlayer -> " + cardPlayer);
        cardPlayer.addCard(new Card("d3", "diamonds", 3));
        cardPlayer.addCard(new Card("hA", "hearts", 14));
        cardPlayer.addCard(new Card("s6", "spades", 6));
        cardPlayer.addCard(new Card("s7", "spades", 7));
        cardPlayer.addCard(new Card("c2", "clubs", 2));
        cardPlayer.addCard(new Card("c3", "clubs", 3));
        System.out.println("after adding 6 cards. Player -> " + cardPlayer);
        Card one = cardPlayer.playCard(5);
        cardPlayer.updateScore(27);
        System.out.println("Played " + one + " at index 5 and scored 27 points. Player -> " + cardPlayer);
        System.out.println("Cards played in round -> " + round);
        System.out.println("Cards played in game -> " + game);
        Card two = cardPlayer.chooseCard(round, game);
        System.out.println("Chose 2 of clubs -> " + two + " Player -> " + cardPlayer);
        round.add(new Card("s10", "spades", 10));
        System.out.println("Cards played in round -> " + round);
        Card c = cardPlayer.chooseCard(round, game);
        System.out.println("RANDOMLY chosen spade -> " + c + " Player -> " + cardPlayer);
        round.remove(0);
        round.add(new Card("c9", "clubs", 9));
        System.out.println("Cards played in round -> " + round);
    }
}

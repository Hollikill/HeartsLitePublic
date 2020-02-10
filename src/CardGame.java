import java.util.*;

public class CardGame {
    Deck deckOfCards;
    String nameOfGame;
    ArrayList<CardPlayer> players;
    int numberOfPlayers;
    int currentPlayer;
    
    public void CardGame (String name, ArrayList<CardPlayer> c, int num, int whichnum) {
        deckOfCards = new Deck();
        nameOfGame = name;
        players = c;
        numberOfPlayers = num;
        currentPlayer = whichnum;
    }

    public void CardGame () {
        this.CardGame("", new ArrayList<CardPlayer>(), 0, 0);
    }
}
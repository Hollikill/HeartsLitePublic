/*
/    Author:
/        Hollister Ream
*/

import java.util.*;

public class CardGame {
    Deck deckOfCards;
    String nameOfGame;
    ArrayList<CardPlayer> players;
    int numberOfPlayers;
    int currentPlayer;
    
    public CardGame (String name, String names[], int num, int whichnum) {
        deckOfCards = new Deck();
        nameOfGame = name;
        numberOfPlayers = num;
        currentPlayer = whichnum;
        players = new ArrayList<CardPlayer>();
        for (String str : names) {
            players.add(new CardPlayer(str, 0, new ArrayList<Card>()));
        }
    }

    public CardGame () {
        deckOfCards = new Deck();
        nameOfGame = "";
        players = new ArrayList<CardPlayer>();
        for (int i = 0; i < 4; i++) {
            players.add(new CardPlayer("", 0, new ArrayList<Card>()));
        }
        numberOfPlayers = 4;
        currentPlayer = 0;
    }

    public void deal (int toWho) {
        for (int i = 0; i < 13; i++) {
            Card c = deckOfCards.dealTopCard();
            players.get(toWho).addCard(c);
        }
    }

    public void playGame () {
        int rounds = 13;

        int firstP = -1;

        for (CardPlayer c : players) {
            if (c.has2Club()) {
                firstP = players.indexOf(c);
            }
        }

        if (firstP == -1) {
            System.out.println("No 2 of clubs");
        }
        else {
        }
    }
}
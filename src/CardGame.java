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
    ArrayList<Card> roundCards;
    ArrayList<Card> gameCards;
    
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
            currentPlayer = firstP;
        }

        for (int i = 0; i < rounds ; i++) {
            String leadSuit = "";

            //play cards
            for (int p = 0; p < 4; p++) {
                Card c = players.get((firstP + p)%4).chooseCard(roundCards, gameCards);
                System.out.println(players.get((firstP + p)%4).getName() + " played the " + c.getCleanName());
                if (p == 0) {
                    leadSuit = c.getSuit();
                }
            }

            //analyze score
            int mostValue = 0;
            int curIndex = -1;
            int bigIndex = 1;
            for (Card c : roundCards) {
                if (c.getSuit() == leadSuit && c.getValue() > mostValue) {
                    mostValue = c.getValue();
                    bigIndex = curIndex;
                }
                curIndex++;
            }

            //give cards to winner
            for (Card c : roundCards) {
                players.get((firstP + bigIndex)%4).addTakenCard(c);
            }

            //prepare for new round
            while (!roundCards.isEmpty()) {
                roundCards.remove(0);
            }
        }

        //score
        /*if (c.getSuit() == "hearts") {
            players.get((firstP + bigIndex)%4).updateScore(1);
        }
        else if (c.getSuit() == "spades" && c.getValue() == 12) {
            players.get((firstP + bigIndex)%4).updateScore(13);
        }*/
    }
}
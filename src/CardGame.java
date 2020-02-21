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
        printPlayersAtStart();
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
        printPlayersAtStart();
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
            firstP = (firstP + bigIndex) % 4;

            while (!roundCards.isEmpty()) {
                roundCards.remove(0);
            }
        }

        //score
        for (CardPlayer player : players) {
            for (Card c : player.getTakenCards()) {
                if (c.getSuit() == "hearts") {
                    player.updateScore(1);
                    System.out.println(player.getName() + " scored with a " + c.getCleanName());
                }
                else if (c.getSuit() == "spades" && c.getValue() == 12) {
                    player.updateScore(13);
                    System.out.println(player.getName() + " scored 13 with a " + c.getCleanName());
                }
            }
        }
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "***Hearts***\n" + players.get(0) + "\n" + players.get(1) + "\n" + players.get(2) + "\n" + players.get(3) + "\ndeck ->\n" + deckOfCards;
        return ret;
    }

    public void printPlayersAtStart() {
        System.out.println("Player" + players.get(0).getName() + " is a CardPlayer" + "Player" + players.get(1).getName() + " is a CardPlayer" + "Player" + players.get(2).getName() + " is a CardPlayer" + "Player" + players.get(3).getName() + " is a CardPlayer");
    }

    public void initGame() {
        deckOfCards = new Deck();
        for (CardPlayer c : players) {
            ArrayList<Card> taken = c.getTakenCards();
            ArrayList<Card> hand = c.getHand();
            for (int d = taken.size() - 1; d >= 0; d--) {
                taken.remove(d);
            }
            for (int d = hand.size() - 1; d >= 0; d--) {
                hand.remove(d);
            }
        }
        deckOfCards.shuffle();
        deal(0);
        deal(1);
        deal(2);
        deal(3);
    }

    public String cleanPlayerNames() {
        return players.get(0).cleanName() + " " + players.get(1).cleanName() + " " + players.get(2).cleanName() + " " + players.get(3).cleanName();
    }
}
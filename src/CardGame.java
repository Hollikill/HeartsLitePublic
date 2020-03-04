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
        roundCards = new ArrayList<Card>();
        gameCards = new ArrayList<Card>();
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
        roundCards = new ArrayList<Card>();
        gameCards = new ArrayList<Card>();
        nameOfGame = "";
        players = new ArrayList<CardPlayer>();
        for (int i = 0; i < 4; i++) {
            players.add(new CardPlayer("Player" + i, 0, new ArrayList<Card>()));
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

        Card c2 = new Card("c", 2);
        for (CardPlayer c : players) {
            ArrayList<Card> cHand = c.getHand();
            boolean cHas = false;
            for (Card card : cHand) {
                if (card.equals(c2)) {
                    cHas = true;
                }
            }
            if (cHas) {
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
                System.out.println(players.get((firstP + p)%4).getHand());
                Card c = players.get((firstP + p)%4).chooseCard(roundCards, gameCards);
                if (p == 0) {
                    leadSuit = c.getSuit();
                }
            }

            //analyze score
            int mostValue = 0;
            int curIndex = 0;
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
                }
                else if (c.getSuit() == "spades" && c.getValue() == 12) {
                    player.updateScore(13);
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
        System.out.println("Player " + players.get(0).getName() + " is a " + players.get(0).getClass() + "\nPlayer " + players.get(1).getName() + " is a " + players.get(1).getClass() + "\nPlayer " + players.get(2).getName() + " is a " + players.get(2).getClass() + "\nPlayer " + players.get(3).getName() + " is a " + players.get(3).getClass());
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
        String str = "";

        for (CardPlayer pl : players) {
            String temp = pl + "";
            str += temp.substring(0, temp.indexOf(" ")) + " (" + temp.substring(temp.indexOf(" ")) + ") ";
        }

        return str;
    }

    public ArrayList<CardPlayer> getPlayers() {
        return players;
    }
}
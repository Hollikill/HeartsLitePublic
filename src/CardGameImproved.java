/*
/    Author:
/        Hollister Ream
*/

import java.util.*;

public class CardGameImproved {
    ArrayList<CardPlayer> totPlayers;
    Deck deckOfCards;
    String nameOfGame;
    ArrayList<CardPlayer> players;
    int numberOfPlayers;
    int currentPlayer;
    ArrayList<Card> roundCards;
    ArrayList<Card> gameCards;
    
    public CardGameImproved (String name, ArrayList<CardPlayer> totPlayers, int num, int firstPlayer) {
        deckOfCards = new Deck();
        roundCards = new ArrayList<Card>();
        gameCards = new ArrayList<Card>();
        nameOfGame = name;
        numberOfPlayers = num;
        currentPlayer = firstPlayer;
        players = new ArrayList<CardPlayer>();
        this.totPlayers = totPlayers;
        printPlayersAtStart();
    }

    public CardGameImproved (String name, ArrayList<CardPlayer> totPlayers, int firstPlayer) {
        deckOfCards = new Deck();
        roundCards = new ArrayList<Card>();
        gameCards = new ArrayList<Card>();
        nameOfGame = name;
        numberOfPlayers = 4;
        currentPlayer = firstPlayer;
        players = new ArrayList<CardPlayer>();
        this.totPlayers = totPlayers;
        printPlayersAtStart();
    }
    
    public CardGameImproved () {
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
        
        for (CardPlayer c : players) {
            ArrayList<Card> cHand = c.getHand();
            boolean cHas = false;
            for (Card card : cHand) {
                if (card.getValue() == 2 && card.getSuit().charAt(0) == 'c') {
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
                Card c = players.get((firstP + p)%4).chooseCard(roundCards, gameCards);
                // Game log for actions, shows who played what. !WARNING! may cause excessive clutter in output dialog if enabled
                //System.out.println(players.get((firstP + p)%4).getName() + " played the " + c.getCleanName());
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
                    // Game log for scoring, shows who scored what. !WARNING! may cause excessive clutter in output dialog if enabled
                    //System.out.println(player.getName() + " scored with a " + c.getCleanName());
                }
                else if (c.getSuit() == "spades" && c.getValue() == 12) {
                    player.updateScore(13);
                    // Game log for scoring, shows who scored what. !WARNING! may cause excessive clutter in output dialog if enabled
                    //System.out.println(player.getName() + " scored 13 with a " + c.getCleanName());
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
        for (CardPlayer c : totPlayers) {
            System.out.println("Player " + c.getName() + " is a " + c.getClass());
        }
    }
    
    public void initGame() {
        currentPlayer = 0;
        deckOfCards = new Deck();
        deckOfCards.shuffle();
        deal(0);
        deal(1);
        deal(2);
        deal(3);
    }

    public void clearHandTaken() {
        for (CardPlayer c : totPlayers) {
            ArrayList<Card> taken = c.getTakenCards();
            ArrayList<Card> hand = c.getHand();
            for (int d = taken.size() - 1; d >= 0; d--) {
                taken.remove(d);
            }
            for (int d = hand.size() - 1; d >= 0; d--) {
                hand.remove(d);
            }
        }
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
        return totPlayers;
    }
    
    public void playGameImproved () {
        int totConfigurations = 1;
        int[] curConfig = new int[4];
        for (int num : curConfig) {
            num = 0;
        }
        for (int i = 0; i < 4; i++) {
            totConfigurations *= totPlayers.size() - i;
        }
        for (int i = 0; i < totConfigurations; i++) {
            // verify that the current configuration is correct
            verifyConfig(curConfig);

            // erase current player order
            players = new ArrayList<CardPlayer>();

            // add players in correct order
            players.add(totPlayers.get(curConfig[0]));
            players.add(totPlayers.get(curConfig[1]));
            players.add(totPlayers.get(curConfig[2]));
            players.add(totPlayers.get(curConfig[3]));

            // clear leftover cards
            clearHandTaken();

            // initialize the game
            initGame();

            // play the game with the current order
            playGame();

            // prints the current order of players. !WARNING! may cause excessive output if enabled
            /*for (int num : curConfig) {
                System.out.print(num + "");
            }
            System.out.println();*/

            // move to next configuration
            curConfig[3]++;
        }
    }
    
    public void verifyConfigSize(int[] curConfig) {
        if (curConfig[3] == totPlayers.size()) {
            curConfig[2]++;
            curConfig[3] = 0;
        }
        if (curConfig[2] == totPlayers.size()) {
            curConfig[1]++;
            curConfig[2] = 0;
        }
        if (curConfig[1] == totPlayers.size()) {
            curConfig[0]++;
            curConfig[1] = 0;
        }
    }
    
    public void verifyConfigRepeat(int[] curConfig) {
        while (curConfig[3] == curConfig[2] || curConfig[3] == curConfig[1] || curConfig[3] == curConfig[0] || curConfig[2] == curConfig[1] || curConfig[2] == curConfig[0] || curConfig[1] == curConfig[0]) {
            while (curConfig[3] == curConfig[2] || curConfig[3] == curConfig[1] || curConfig[3] == curConfig[0]) {
                curConfig[3]++;
                verifyConfigSize(curConfig);
            }
            while (curConfig[2] == curConfig[1] || curConfig[2] == curConfig[0]) {
                curConfig[2]++;
                verifyConfigSize(curConfig);
            }
            while (curConfig[1] == curConfig[0]) {
                curConfig[1]++;
                verifyConfigSize(curConfig);
            }
        }
    }
    
    public void verifyConfig(int[] curConfig) {
        // loop indexes if too big
        verifyConfigSize(curConfig);
        
        // make sure there are no repeat numbers
        verifyConfigRepeat(curConfig);
    }
}
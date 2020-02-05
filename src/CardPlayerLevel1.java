import java.util.*;

public class CardPlayerLevel1 extends CardPlayer {
    // Contructors
    public CardPlayerLevel1 () {
        super("", 0, new ArrayList<Card>());
    }
    public CardPlayerLevel1 (String name, int score, ArrayList<Card> hand) {
        super(name, score, hand);
    }
    
    
    // The important one. Card Choosing AI
    @Override
    public Card chooseCard(ArrayList<Card> playedRound, ArrayList<Card> playedGame) {
        if (playedRound.size() == 3) {
            String suit = playedRound.get(0).getSuit();
            int val = 0;
            ArrayList<Card> ofLeadSuit = new ArrayList<Card>();
            for (Card c : playedRound) {
                if (c.getSuit() == suit) {
                    if (c.getValue() > val) {
                        val = c.getValue();
                    }
                }
            }
            for (Card c : hand) {
                if (c.getSuit() == suit) {
                    ofLeadSuit.add(c);
                }
            }
            if (ofLeadSuit.isEmpty()) {
                
            }
            else {
                if (suit == "hearts") {
                    int handval = 0;
                    int highestLowOfSuit = 0;
                    int index = 0;
                    for (Card c : ofLeadSuit) {
                        if (c.getValue() < val) {
                            if (c.getValue() > handval) {
                                handval = c.getValue();
                                highestLowOfSuit = index;
                            }
                        }
                        index++;
                    }
                    playCard(highestLowOfSuit, playedRound, playedGame);
                }
                else {
                    int handval = 0;
                    int highestOfSuit = 0;
                    int index = 0;
                    for (Card c : ofLeadSuit) {
                        if (c.getValue() > handval) {
                            handval = c.getValue();
                            highestOfSuit = index;
                        }
                        index++;
                    }
                    playCard(highestOfSuit, playedRound, playedGame);
                }
            }
        }
        else if (playedRound.isEmpty()) {
            if (playedGame.isEmpty()) {
                for (int i = 0; i < hand.size(); i++) {
                    Card x = hand.get(i);
                    if (x.getSuit().startsWith("c") && x.getValue() == 2) {
                        return playCard(i, playedRound, playedGame);
                    }
                }
            }
        }
        else {
            
        }
        
        return null;
    }
}
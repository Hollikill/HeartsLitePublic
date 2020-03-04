/*
/    Author:
/        Hollister Ream
*/

import java.util.*;

public class CardPlayerLevel1 extends CardPlayer {
    // Contructors
    public CardPlayerLevel1 () {
        super("CardPlayerLevel1", 0, new ArrayList<Card>());
    }
    public CardPlayerLevel1 (String name, int score, ArrayList<Card> hand) {
        super(name, score, hand);
    }
    
    // The important one. Card Choosing AI
    @Override
    public Card chooseCard(ArrayList<Card> playedRound, ArrayList<Card> playedGame) {
        int handI = 0;
        // has anyone played a card in the round yet
        if (playedRound.size() != 0) {
            String suit = playedRound.get(0).getSuit();
            int val = 0;
            for (Card c : playedRound) {
                if (c.getSuit() == suit) {
                    if (c.getValue() > val) {
                        val = c.getValue();
                    }
                }
            }
            ArrayList<Card> ofLeadSuit = new ArrayList<Card>();
            for (Card c : hand) {
                if (c.getSuit() == suit) {
                    ofLeadSuit.add(c);
                }
            }
            // do I have any cards of the lead suit
            if (ofLeadSuit.isEmpty()) {
                int qOSIndex = -1;
                int qOSi = 0;
                for (Card c : hand) {
                    if (c.getSuit() == "spades" && c.getValue() == 12) {
                        qOSIndex = qOSi;
                    }
                    qOSi++;
                }
                // play queen of spades if possible
                if (qOSIndex != -1) {
                    return playCard(qOSIndex, playedRound, playedGame);
                }
                if (suit == "hearts") {
                    int handval = 0;
                    int highest = 0;
                    int index = 0;
                    for (Card c : hand) {
                        if (c.getValue() > handval) {
                            handval = c.getValue();
                            highest = index;
                        }
                        index++;
                    }
                    return playCard(highest, playedRound, playedGame);
                }
                else {
                    ArrayList<Card> hearts = new ArrayList<Card>();
                    for (Card c : hand) {
                        if (c.getSuit() == "hearts") {
                            hearts.add(c);
                        }
                    }
                    if (hearts.isEmpty()) {
                        int handval = 0;
                        int highest = 0;
                        int index = 0;
                        for (Card c : hand) {
                            if (c.getValue() > handval) {
                                handval = c.getValue();
                                highest = index;
                            }
                            index++;
                        }
                        return playCard(highest, playedRound, playedGame);
                    }
                    else {
                        int handval = 0;
                        int highestHeart = 0;
                        int index = 0;
                        for (Card c : hearts) {
                            if (c.getValue() > handval) {
                                handval = c.getValue();
                                highestHeart = index;
                            }
                            index++;
                        }
                        handI = getHandIndex(hearts.get(highestHeart));
                        return playCard(handI, playedRound, playedGame);
                    }
                }
            }
            else {
                if (suit == "hearts") {
                    int handval = 0;
                    int highestLowOfSuit = -1;
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
                    if (highestLowOfSuit == -1) {
                        index = 0;
                        for (Card c : ofLeadSuit) {
                            if (c.getValue() > handval) {
                                handval = c.getValue();
                                highestLowOfSuit = index;
                            }
                            index++;
                        }
                    }
                    handI = getHandIndex(ofLeadSuit.get(highestLowOfSuit));
                    return playCard(handI, playedRound, playedGame);
                }
                else {
                    if (playedRound.size() > 1) {
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
                        handI = getHandIndex(ofLeadSuit.get(highestOfSuit));
                        return playCard(handI, playedRound, playedGame);
                    }
                    else {
                        int handval = 0;
                        int highestLowOfSuit = -1;
                        int index = 0;
                        for (Card c : ofLeadSuit) {
                            if (c.getValue() < val && c.getValue() > handval) {
                                handval = c.getValue();
                                highestLowOfSuit = index;
                            }
                            index++;
                        }
                        if (highestLowOfSuit == -1) {
                            index = 0;
                            for (Card c : ofLeadSuit) {
                                if (c.getValue() > handval) {
                                    handval = c.getValue();
                                    highestLowOfSuit = index;
                                }
                                index++;
                            }
                        }
                        handI = getHandIndex(ofLeadSuit.get(highestLowOfSuit));
                        return playCard(handI, playedRound, playedGame);
                    }
                }
            }
        }
        else {
            if (playedGame.isEmpty()) {
                for (int i = 0; i < hand.size(); i++) {
                    Card x = hand.get(i);
                    if (x.getSuit().startsWith("c") && x.getValue() == 2) {
                        return playCard(i, playedRound, playedGame);
                    }
                }
            }
            else {
                if (hand.size() > 3) {
                    int handval = 0;
                    int highest = 0;
                    int index = 0;
                    for (Card c : hand) {
                        if (c.getValue() > handval) {
                            handval = c.getValue();
                            highest = index;
                        }
                        index++;
                    }
                    return playCard(highest, playedRound, playedGame);
                }
                else {
                    int handval = 0;
                    int highestLow = 0;
                    int index = 0;
                    for (Card c : hand) {
                        if (c.getValue() < handval) {
                            handval = c.getValue();
                            highestLow = index;
                        }
                        else if (c.getSuit() != "hearts" && c.getValue() <= handval) {
                            handval = c.getValue();
                            highestLow = index;
                        }
                        index++;
                    }
                    return playCard(highestLow, playedRound, playedGame);
                }
            }
        }
        
        System.out.println("CardPlayerLevel1 Error: no options found");
        return null;
    }
    
    public int getHandIndex(Card i) {
        int index = 0;
        int desiredI = 0;
        for (Card c : hand) {
            if (c.getValue() == i.getValue() && c.getSuit() == i.getSuit()) {
                desiredI = index;
            }
            index++;
        }
        return desiredI;
    }
}
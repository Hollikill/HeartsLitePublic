/*
/    Authors:
/        Hollister Ream
/        Gabriel (I don't know his last name)
*/

import java.util.ArrayList;
import java.util.Collections;

public class CardPlayer extends Player {
    protected ArrayList<Card> hand, takenCards;

    public CardPlayer(String name, int score, ArrayList<Card> hand) {
        super(name, score);
        this.hand = hand;
        takenCards = new ArrayList<Card>();
    }

    public void addCard(Card c){
        hand.add(c);
    }

    public boolean playCard(Card c){
        return hand.remove(c);
    }

    public Card playCard(int index){
        return hand.remove(index);
    }

    public Card chooseCard(ArrayList<Card> playedRound, ArrayList<Card> playedGame){
        if(playedGame.isEmpty()&&playedRound.isEmpty()){
            for (int i = 0; i < hand.size(); i++) {
                Card x = hand.get(i);
                if (x.getSuit().startsWith("c") && x.getValue() == 2) {
                    return playCard(i, playedRound, playedGame);
                }
            }
        }
        else if (playedRound.isEmpty()) {
            return playCard((int)(Math.random() * hand.size()), playedRound, playedGame);
        }
        else {
            String leadingSuit = playedRound.get(0).getSuit();
            ArrayList<Card> matchesSuit = new ArrayList<>();
            for(Card x : hand){
                if(x.getSuit().equals(leadingSuit)) matchesSuit.add(x);
            }

            if(!matchesSuit.isEmpty()){
                //Not empty: Play random from matchesSuit
                return playCard(matchesSuit.get((int)(Math.random() * matchesSuit.size())), playedRound, playedGame);
            }
            else{
                ArrayList<Card> hearts = new ArrayList<>();
                for(Card x : hand){
                    if(x.getSuit().startsWith("h")) hearts.add(x);
                }

                if(!hearts.isEmpty()){
                    //Not empty: Play random from HEARTS
                    return playCard(hearts.get((int)(Math.random() * hearts.size())), playedRound, playedGame);
                }
                else{
                    return playCard((int)(Math.random() * hand.size()), playedRound, playedGame);
                }
            }
        }
        return null;
    }

    public Card playCard(Card c, ArrayList<Card> round, ArrayList<Card> game){
        hand.remove(c);
        round.add(c);
        game.add(c);
        return c;
    }

    public Card playCard(int c, ArrayList<Card> round, ArrayList<Card> game){
        return playCard(hand.get(c), round, game);
    }

    public String toString() {
        Collections.sort(hand);
        return name + " (" + score + ") " + hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public ArrayList<Card> getTakenCards() {
        return takenCards;
    }

    public void setTakenCards(ArrayList<Card> takenCards) {
        this.takenCards = takenCards;
    }

    public void addTakenCard(Card c) {
        this.takenCards.add(c);
    }

    public boolean has2Club () {
        boolean b = false;
        for (Card c : hand) {
            if (c.getValue() == 2 && c.getSuit() == "clubs") {
                b = true;
            }
        }
        return b;
    }
}

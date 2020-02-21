/*
/    Authors:
/        Hollister Ream
/        Gabriel Keller
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Deck {

    public static final String[] NAMES = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};
    public static final String[] SUITS = {"clubs", "spades", "hearts", "diamonds"};
    public static final int[] VALUES = {2,3,4,5,6,7,8,9,10,11,12,13,14};
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
    }

    public Deck(Collection<Card> cards){
        cards = new ArrayList<>(cards);
    }

    public void initializeDeck(){
        cards.clear();
        for(int i = 0; i < VALUES.length; i++){
            int value = VALUES[i];
            String name = NAMES[i];
            //public Card(String name, String suit, int value) {
            for(int x = 0; x < SUITS.length; x++){
                cards.add(new Card(name, SUITS[x], value));
            }
        }
    }

    public Card dealTopCard(){
        return cards.remove(0);
    }

    public void shuffleAlt(){
        shuffleAlt(20);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void shuffleAlt(int numOfTimes){
        for(int i = 0; i < numOfTimes; i++){
            int cardsToTake = (int)(Math.random()*9D+2D);
            ArrayList<Card> takeOut = new ArrayList<>();
            for(int x = cardsToTake-1; x > 0; x--){
                takeOut.add(cards.remove(x));
            }
            int newIndex = (int)(Math.random()*(double)cards.size());
            cards.addAll(newIndex, takeOut);
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Card c : cards){
            sb.append(c.toString());
        }

        return sb.toString();
    }
}

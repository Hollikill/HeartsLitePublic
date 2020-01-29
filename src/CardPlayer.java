import java.util.ArrayList;
import java.util.Collections;

public class CardPlayer extends Player {
    private ArrayList<Card> hand, takenCards;

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
                if (x.getSuit().equals("clubs") && x.getValue() == 2) {
                    playCard(i);
                    playedRound.add(x);
                    playedGame.add(x);
                    return x;
                }
            }
        }
        else if (playedRound.isEmpty()) {
            Card card = playCard((int)(Math.random() * hand.size()));
            playedRound.add(card);
            playedGame.add(card);
            return card;
        }
        else {
            String suit = playedRound.get(0).getSuit();
            ArrayList<Card> matchesSuit = new ArrayList<>();
            for(Card x : hand){
                if(x.getSuit().equals(suit)) matchesSuit.add(x);
            }

            if(!matchesSuit.isEmpty()){
                //Not empty: Play random from matchesSuit
                Card card = matchesSuit.get((int)(Math.random() * matchesSuit.size()));
                playCard(card);
                playedRound.add(card);
                playedGame.add(card);
                return card;
            }
            else{
                ArrayList<Card> hearts = new ArrayList<>();
                for(Card x : hand){
                    if(x.getSuit().equals("hearts")) hearts.add(x);
                }

                if(!hearts.isEmpty()){
                    //Not empty: Play random from HEARTS
                    Card card = hearts.get((int)(Math.random() * hearts.size()));
                    playCard(card);
                    playedRound.add(card);
                    playedGame.add(card);
                    return card;
                }
                else{
                    Card card = playCard((int)(Math.random() * hand.size()));
                    playedRound.add(card);
                    playedGame.add(card);
                    return card;
                }
            }
        }
        return null;
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
}

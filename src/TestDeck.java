/*
/    Authors:
/        Hollister Ream
/        Gabriel Keller
*/

import java.util.ArrayList;

public class TestDeck {
    public static void main(String[] args) {
        Deck d = new Deck();
        System.out.println("new deck " + d);
        d.shuffle();
        System.out.println("shuffled " + d + "\n");
        for(int i = 0; i < 4; i++){
            ArrayList<Card> cards = new ArrayList<>();
            for(int x = 0; x < 13; x++){
                cards.add(d.dealTopCard());
            }

            System.out.print("13 cards ");
            for(Card c : cards){
                System.out.print(c);
            }
            System.out.println("\nremaining " + d);
        }

        Deck d2 = new Deck();
        int location = (int)(Math.random()*52D);
        int times = 0;
        Card selected = d2.getCards().get(location);
        System.out.println("\nrandom card from new deck " + selected);
        for(int i = 0; i < 52000; i++){
            d2.shuffle();
            if(d2.getCards().get(location).equals(selected)) times++;
        }

        System.out.println("Found " + selected + "at location " + location + " in the deck " + times + " times.");
    }
}

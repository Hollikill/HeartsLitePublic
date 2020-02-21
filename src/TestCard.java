/*
/    Authors:
/        Hollister Ream
/        Gabriel Keller
*/

public class TestCard {
    public static void main(String[] args) {
        Card c3 = new Card("3", "clubs", 3);
        Card d3 = new Card("3", "diamonds", 3);
        Card hA = new Card("A", "hearts", 14);
        Card s7 = new Card("7", "spades", 7);
        Card s6 = new Card("6", "spades", 6);

        System.out.println(c3 + ".equals(" + c3 + ") is " + c3.equals(c3));
        System.out.println(c3 + ".equals(" + d3 + ") is " + c3.equals(d3));
        System.out.println(c3 + ".compareTo(" + c3 + ") is " +c3.compareTo(c3));
        System.out.println(c3 + ".compareTo(" + d3 + ") is " + c3.compareTo(d3));
        System.out.println(d3 + ".compareTo(" + c3 + ") is " +d3.compareTo(c3));
        System.out.println(hA + ".compareTo(" + s7 + ") is " + hA.compareTo(s7));
        System.out.println(s7 + ".compareTo(" + hA + ") is " + s7.compareTo(hA));
        System.out.println(s7 + ".compareTo(" + s6 + ") is " +s7.compareTo(s6));
    }
}

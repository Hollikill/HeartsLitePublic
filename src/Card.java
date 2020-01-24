public class Card {

    private String name, suit;
    private int value;

    public Card(){
        name = "default";
        suit = "default";
        value = 0;
    }

    public Card(String name, String suit, int value) {
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    public int compareTo(Object o){
        if(o instanceof Card){
            Card other = (Card)o;

            if(other.equals(this)) return 0;

            int t = 0;
            int y = 0;
            switch (other.getSuit().charAt(0)) {
                case 'c':
                    t = 4;
                    break;
                case 's':
                    t = 3;
                    break;
                case 'h':
                    t = 2;
                    break;
                case 'd':
                    t = 1;
                    break;
            }
            switch (suit.charAt(0)) {
                case 'c':
                    y = 4;
                    break;
                case 's':
                    y = 3;
                    break;
                case 'h':
                    y = 2;
                    break;
                case 'd':
                    y = 1;
                    break;
            }

            if (t > y) {
                return -1;
            }
            else if (y > t) {
                return 1;
            }
            else {
                if (other.getValue() > value) {
                    return -1;
                }
                else {
                    return 1;
                }
            }

        }
        else throw new IllegalArgumentException("Cannot supply object type " + o.getClass() + " to Card#compareTo");
    }

    @Override
    public String toString(){
        return suit.charAt(0) + name + "(" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Card){
            Card other = (Card)o;
            return value == other.getValue() && name.equals(other.getName()) && suit.equals(other.getSuit());
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

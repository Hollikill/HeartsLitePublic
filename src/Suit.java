public enum Suit {
    CLUBS(4), SPADES(3), HEARTS(2), DIAMONDS(1);

    private int pos;

    Suit(int pos){
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }
}

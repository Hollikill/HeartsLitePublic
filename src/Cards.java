public enum Cards {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

    private String name;
    private int pos;

    Cards(int pos){
        if(pos>0&&pos<11){
            name = "" + pos;
        }
        else{
            switch(pos){
                case 11:
                    name = "j";
                    break;
                case 12:
                    name = "q";
                    break;
                case 13:
                    name = "k";
                    break;
                case 14:
                    name = "a";
            }
        }

        this.pos = pos;
    }

    Cards(String name, int pos){
        this.name = name;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public int getPos() {
        return pos;
    }
}

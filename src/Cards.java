public enum Cards {
    ONE("1");

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
                case 12:
                    name = "q";
                    break;
                case 13:
                    name =
            }
            if(pos==11) name = "k";
        }
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

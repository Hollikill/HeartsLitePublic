/*
/    Authors:
/        Hollister Ream
/        Gabriel Keller
*/

public class Player {
    protected String name;
    protected int score;

    public Player() {
        name = "";
        score = 0;
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int score){
        setScore(this.score+score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString(){
        return name + " " + score;
    }

    public String cleanName() {
        return name + " (" + score + ")";
    }
}

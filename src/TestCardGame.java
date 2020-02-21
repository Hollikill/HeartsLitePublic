/*
/    Author:
/        Hollister Ream
*/

public class TestCardGame {
    public static void main (String args[]) {
        int totalScore = 0;
        System.out.println(">>> Printed in Constructor");
        CardGame game = new CardGame();
        for (int i = 0; i < 4; i++) {
            game.initGame();
            game.playGame();
            System.out.println(">>> playGame() " + game.cleanPlayerNames());
            System.out.println(">>> Printing Total Score and Player % in TestCardGame");
            System.out.println("Total score = " + totalScore + "\n");
        }
    }
}
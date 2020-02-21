/*
/    Authors:
/        Hollister Ream
/        Gabriel Keller
*/

public class TestPlayer {
    public static void main(String[] args) {
        Player player = new Player("Kara Jones", 0);
        System.out.println("Created Player -> " + player);

        player.updateScore(6);
        System.out.println("After scoring 6 points -> " + player);

        Player player2 = new Player("Bob Smith", 27);
        System.out.println("Created Player -> " + player2);

        player2.updateScore(65);
        System.out.println("After scoring 65 points -> " + player2);

    }
}

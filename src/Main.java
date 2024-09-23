import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        MancalaBoard mancalaBoard = new MancalaBoard();
        MancalaPlayer mancalaPlayer1 = new MancalaPlayer(mancalaBoard);
        MancalaPlayer mancalaPlayer2 = new Player(mancalaBoard);
        MancalaMatch mancalaMatch = new MancalaMatch(mancalaPlayer1, mancalaPlayer2);
        System.out.println("Process completed successfully");



    }
}
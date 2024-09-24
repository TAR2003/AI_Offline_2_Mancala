import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        MancalaBoard mancalaBoard = new MancalaBoard();
        ArrayList<Integer> weights = new ArrayList<>();
        weights.add(1);
        weights.add(0);
        weights.add(0);
        weights.add(0);
        MancalaPlayer mancalaPlayer1 = new MancalaPlayer(mancalaBoard, weights,2);
        MancalaPlayer mancalaPlayer2 = new Player(mancalaBoard, weights,2 );
        MancalaMatch mancalaMatch = new MancalaMatch(mancalaPlayer1, mancalaPlayer2);
        System.out.println("Process completed successfully");



    }
}
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        MancalaBoard mancalaBoard = new MancalaBoard();
        ArrayList<Integer> weights = new ArrayList<>();
        weights.add(3);
        weights.add(1);
        weights.add(0);
        weights.add(4);
        ArrayList<Integer> version2weights = new ArrayList<>();
        version2weights.add(3);
        version2weights.add(0);
        version2weights.add(0);
        version2weights.add(0);
        MancalaPlayer mancalaPlayer1 = new MancalaPlayer(mancalaBoard, weights, 5);
        MancalaPlayer mancalaPlayer2 = new MancalaPlayer(mancalaBoard, version2weights, 5);
        System.out.println("Printing");
        //System.out.println(mancalaPlayer1.getMove());
        MancalaMatch mancalaMatch = new MancalaMatch(mancalaPlayer1, mancalaPlayer2);
        System.out.println("StATUs " + mancalaMatch.status);
        System.out.println("Process completed successfully");
        System.out.println("If you want to play click y, else press any key");
        MancalaBoard mancalaBoard1 = new MancalaBoard();
        MancalaPlayer player = new Player(mancalaBoard1, weights, 5);
        MancalaPlayer pc = new MancalaPlayer(mancalaBoard1, getArrayList(1,0,0,0), 5);
        MancalaMatch mancalaMatch1 = new MancalaMatch(player, pc);


    }
    public static ArrayList<Integer> getArrayList(int W1, int W2, int W3, int W4) {
        ArrayList<Integer> weights = new ArrayList<>(4);
        weights.add(W1);
        weights.add(W2);
        weights.add(W3);
        weights.add(W4);
        return weights;
    }
}
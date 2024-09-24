import java.util.ArrayList;
import java.util.PriorityQueue;

public class Tree {
    public int alpha, beta, bestChoice;
    public MancalaBoard mancalaBoard;
    public MancalaPlayer mancalaPlayer;
    public ArrayList<Integer> weights;
    int depth;

    public Tree(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int depth, ArrayList<Integer> weights) {
        this.mancalaBoard = mancalaBoard;
        this.mancalaPlayer = mancalaPlayer;
        this.weights = weights;
        this.depth = depth;
    }

    public int findMostImportantNode() {
        Node node = new MaxNode(mancalaBoard, mancalaPlayer, 0, this.weights);
        node.setAlpha(Integer.MIN_VALUE);
        node.setBeta(Integer.MAX_VALUE);
        node.expandNode(this.depth);
        return node.getBestChoice().getOrder();
    }


}


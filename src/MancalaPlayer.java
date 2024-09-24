import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MancalaPlayer {
    public MancalaBoard mancalaBoard;
    public Random random;
    public ArrayList<Integer> weights;
    public int depth;

    public int getPlayerno() {
        return playerno;
    }

    public void setPlayerno(int playerno) {
        this.playerno = playerno;
    }

    public int playerno;

    public MancalaPlayer(MancalaBoard mancalaBoard, ArrayList<Integer> weights, int depth) {
        this.mancalaBoard = mancalaBoard;
        random = new Random();
        this.weights = weights;
        this.depth = depth;

    }
    public int getMove() {

        int move = random.nextInt(6);
        Tree tree = new Tree(mancalaBoard, this, this.depth, weights);
        return tree.bestChoice;
    }
}

class Player extends MancalaPlayer {
    public Scanner scanner;
    public Player(MancalaBoard mancalaBoard, ArrayList<Integer> weights, int depth) {
        super(mancalaBoard, weights, depth);
        scanner = new Scanner(System.in);
    }
    public int getMove() {
        int move = scanner.nextInt();
        scanner.nextLine();
        return move;
    }
}

/*
    Mancala Player is the AI that will play mancala after calculating its best move
    Mancala Player class will get the board state for any situation of a mancala match from the class
    Then it will make 6 calculations first
    All of them are selecting a node and then calculating it
    Then it will set them in a priority queue
    It will expand the element with the most promise first, then the others
    The same will go with them too, they will enqueue them in their queue, and expand the most important ones first
    After reaching the depth we need, we will calculate the important informations about the heuristics
    Then we will send the alpha and beta values back to the parent node
    We will prune every node when alpha >= beta
    we will continue till the end for the parent node
    Then we will give our turn
    If our turn results in another bonus turn, we will calculate it in the same node
    Only after our turns are over and it is the turn for the opposite player, we will start another node for minNode
    when returning,we will send the minValue for maxNodes and the maxValue for the minNodes


 */

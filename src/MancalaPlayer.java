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
        Tree tree = new Tree(mancalaBoard, this, this.depth, weights); // building the tree for the current situation
        int move =  tree.findMostImportantNode(); // geting waht will be the most imporant move from the tree using heuristics
        System.out.println("The Automatic player with player number " + this.getPlayerno() + " has selected the move  " + move);
        return move; // returning the move pc just made
    }
}

// this class is for User, it takes move from console input
class Player extends MancalaPlayer {
    public Scanner scanner;
    public Player(MancalaBoard mancalaBoard, ArrayList<Integer> weights, int depth) {
        super(mancalaBoard, weights, depth);
        scanner = new Scanner(System.in);
    }
    public int getMove() {
        mancalaBoard.printFromPerspective(this.getPlayerno()); // show the mancala board state in the console
        System.out.println("This is your turn:=");
        int move;
        while(true) {
            System.out.print("Please enter a number between 1 - 6:=  ");
            move = scanner.nextInt();
            if(move > 0 && move < 7) {
                break; // validating the move
            }
            else {
                System.out.println("Invalid input, " + move + " is not valid");
            }
        }

        return move;
    }
}


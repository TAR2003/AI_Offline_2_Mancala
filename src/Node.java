import java.util.ArrayList;
import java.util.PriorityQueue;

public class Node {

    public int getAdditionalMoves() {
        return additionalMoves;
    }

    public int getBestChoice() {
        return bestChoice;
    }

    public void setBestChoice(int bestChoice) {
        this.bestChoice = bestChoice;
    }

    public int getStonesCaptured() {
        return stonesCaptured;
    }

    public void setStonesCaptured(int stonesCaptured) {
        this.stonesCaptured = stonesCaptured;
    }

    public int getValue() {
        return this.value;
    }

    public int getValueOfBoard() {
        return this.valueOfBoard;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getBeta() {
        return beta;
    }

    public void setBeta(int beta) {
        this.beta = beta;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    int additionalMoves;
    int bestChoice;
    int depth;
    int value;
    ArrayList<Integer> weights;
    ArrayList<Node> children;
    public MancalaBoard mancalaBoard;
    public MancalaPlayer mancalaPlayer;
    public int stonesCaptured;
    int alpha;
    int beta;
    // setting the value of the board based on the heuristics
    public void setValueOfBoard() {
        int myStoneNo = mancalaBoard.getStones(mancalaPlayer.getPlayerno());
        int opponentStoneNo = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
        int stoneDifference = myStoneNo - opponentStoneNo;
        int stoneInMySide = mancalaBoard.totalStonesInSide(mancalaPlayer.getPlayerno());
        int stonesInOpponentSide = mancalaBoard.totalStonesInSide(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
        int sideStoneDifference = stoneInMySide - stonesInOpponentSide;
        int valueOfBoard = (this.weights.get(0) * stoneDifference + this.weights.get(1) * sideStoneDifference + this.weights.get(2) * this.getAdditionalMoves() + this.weights.get(3) * this.getStonesCaptured());

        this.valueOfBoard = valueOfBoard;
    }

    int valueOfBoard;


    public Node(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights, int depth) {
        this.additionalMoves = additionalMoves;
        this.mancalaPlayer = mancalaPlayer;
        this.mancalaBoard = mancalaBoard;
        this.depth = depth;
        this.weights = weights; // passing the weights
        this.setValue(Integer.MIN_VALUE); // initializing the value with the lowest value possible
        this.setValueOfBoard(); // setting the value of the node using the heuristics arraylist weights

    }

    public int performMove(int position) {
        return 0;
    } // performing move in any position, child node overrides this method

    public int expandNode() {
        return 0; // it will be overridden by its child classes
    }

    public void handleValue(int value) {
    } // overridden by its child classes


}


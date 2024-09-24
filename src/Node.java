import java.util.ArrayList;
import java.util.PriorityQueue;

public class Node implements Comparable {
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order; // setting the order the child appears in the parent node
    }

    public int order;
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

    int additionalMoves;
    int bestChoice;

    public int getValue() {
        int myStoneNo = mancalaBoard.getStones(mancalaPlayer.getPlayerno());
        int opponentStoneNo = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
        int stoneDifference = myStoneNo - opponentStoneNo;
        int stoneInMySide = mancalaBoard.totalStonesInSide(mancalaPlayer.getPlayerno());
        int stonesInOpponentSide = mancalaBoard.totalStonesInSide(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
        int sideStoneDifference = stoneInMySide - stonesInOpponentSide;
        setValue(this.weights.get(0) * stoneDifference + this.weights.get(1) * sideStoneDifference + this.weights.get(2) * this.getAdditionalMoves() + this.weights.get(3) * this.getStonesCaptured());

        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    int value;
    ArrayList<Integer> weights;
    public MancalaBoard mancalaBoard;
    public MancalaPlayer mancalaPlayer;
    public int stonesCaptured;
    PriorityQueue<Node> priorityQueue;
    int alpha;

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

    int beta;

    public Node(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights) {
        this.additionalMoves = additionalMoves;
        this.mancalaPlayer = mancalaPlayer;
        this.mancalaBoard = mancalaBoard;
        this.weights = weights; // passing the weights
        this.setValue(Integer.MIN_VALUE); // initializing the value with the lowest value possible
    }

    public void performMove(int position) {
    } // performing move in any position, child node overrides this method

    public int expandNode(int depth) {
        if (depth == 0) {
            return this.getValue(); // if depth is 0, then we return the current values
        }
        for (int i = 1; i <= 6; i++) {
            this.performMove(i); // expanding and adding the new nodes into the priority queue
        }

        for (int i = 1; i <= 6; i++) {
            Node node = priorityQueue.remove(); // we are using a priority queue for performing a iterative deepening search
            node.setAlpha(this.getAlpha()); // setting the alpha values
            node.setBeta(this.getBeta()); // setting the beta values
            node.expandNode(depth - 1); // expanding the node 1 less than the current depth limit
            int temp = this.getValue();
            this.handleValue(node); // the child classes are handling the value change for alpha or beta
            if(this.getValue() > temp) {
                this.setBestChoice(node.getOrder());
            }
            if (this.getAlpha() >= this.getBeta()) {
                return this.getValue(); // pruning the other nodes, where it is unnecessary to expand
            }
        }
        return 1;
    }

    public void handleValue(Node node) {
    }

    // not important, child classes' compareTo functions do the important jobs
    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        return Integer.compare(this.getValue(), n.getValue());
    }
}

class MaxNode extends Node {

    public MaxNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights);
    }

    public void handleValue(Node node) {
        setAlpha(Integer.max(this.getAlpha(), node.getValue())); // setting the most value from the child nodes in alpha for maxnode
        setValue(this.getAlpha()); // setting the best value as alpha value
    }

    public void performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;
        while (true) {
            addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno());
            b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), position);
            addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno()) - addCoins;
            if (b) {
                this.additionalMoves++;
                
            } else {
                break;
            }
        }
        if (addCoins > 1) {
            setStonesCaptured(addCoins - 1);
        }
        Node node = new MinNode(mancalaBoard, mancalaPlayer, additionalMoves, weights);
        node.setOrder(position);
        priorityQueue.add(node);

    }

    @Override
    public int compareTo(Object o) {
        return super.compareTo(o); // returning wahtever the base parent has
    }
}

class MinNode extends Node {

    public MinNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights);
    }

    public void handleValue(Node node) {
        this.setBeta(Integer.min(this.getBeta(), node.getValue()));
        this.setValue(this.getBeta()); // setting beta value as the best value
    }

    public void performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;
        while (true) {
            addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
            b = mancalaBoard.moveBoard(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()), position);
            addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno())) - addCoins;
            if (b) {
                this.additionalMoves++;
            } else {
                break;
            }
        }
        if (addCoins > 1) {
            setStonesCaptured(addCoins - 1);
        }
        Node node = new MaxNode(mancalaBoard, mancalaPlayer, additionalMoves, weights);
        priorityQueue.add(node);

    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        return Integer.compare(n.getValue(), this.getValue());
    }
}
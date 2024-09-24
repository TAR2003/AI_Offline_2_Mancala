import java.lang.reflect.Array;
import java.util.ArrayList;

class MaxNode extends Node {

    public MaxNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights);
    }

    public void handleValue(Node node) {
        setAlpha(Integer.max(this.getAlpha(), node.getValue())); // setting the most value from the child nodes in alpha for maxnode
        setValue(this.getAlpha()); // setting the best value as alpha value
    }
    public int repetativeNode() {
        ArrayList <Node> arr = new ArrayList<>(6);
        for(int i = 0 ; i < 6 ; i++) {
            MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
            boolean b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), i);

        }
    }

    public void performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno());
        b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), position);
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno()) - addCoins;
        int additionalMoves = this.additionalMoves;
        Node node1 = this;
        if (b) {
            node1 = new MaxNode(mancalaBoard, mancalaPlayer, this.additionalMoves+1, weights);
            node1 = node1.expandNode(this.depth);
        }
        if (addCoins > 1) {
            setStonesCaptured(addCoins - 1);
        }
        Node node = new MinNode(node1.mancalaBoard, mancalaPlayer, node1.additionalMoves, weights);
        node.setOrder(position);
        priorityQueue.add(node);

    }

    @Override
    public int compareTo(Object o) {
        return super.compareTo(o); // returning wahtever the base parent has
    }
}
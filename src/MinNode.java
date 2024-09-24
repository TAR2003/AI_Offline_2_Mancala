import java.util.ArrayList;

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
        addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
        b = mancalaBoard.moveBoard(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()), position);
        addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno())) - addCoins;
        Node node1 = this;
        if (b) {
            node1 = new MinNode(mancalaBoard, mancalaPlayer, additionalMoves + 1, weights);
            if(node1 == null) {
                System.out.println("Node 1 is null before assigning");
            }
            node1 = node1.expandNode(this.depth);
            if(node1 == null) {
                System.out.println("Node 1 is null after assigning");
            }
        }

        if (addCoins > 1) {
            setStonesCaptured(addCoins - 1);
        }
        if(node1 == null) {
            System.out.println("Node 1 is null wrror");
        }
        Node node = new MaxNode(node1.mancalaBoard, mancalaPlayer, node1.additionalMoves, weights);
        node.setOrder(position);
        priorityQueue.add(node);

    }

    @Override
    public int compareTo(Object o) {
        Node n = (Node) o;
        return Integer.compare(n.getValue(), this.getValue());
    }
}
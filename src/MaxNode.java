import java.lang.reflect.Array;
import java.util.ArrayList;

class MaxNode extends Node {

    public MaxNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights, int depth) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights, depth);
       // System.out.println("new Max Node added");
    }

    public void handleValue(int value) {
        setAlpha(Integer.max(this.getAlpha(), value)); // setting the most value from the child nodes in alpha for maxnode
        setValue(this.getAlpha()); // setting the best value as alpha value
    }

    public int expandNode() {
        if (this.depth == 0) {
            return this.getValueOfBoard();
        }
        if(mancalaBoard.isEmpty(mancalaPlayer.getPlayerno())) {
            return this.getValueOfBoard();
        }
        for (int i = 1; i <= 6; i++) {
          //  System.out.println("Expanding max node at position " + i);
            int getPitNo = mancalaBoard.getValueBoard(mancalaPlayer.getPlayerno(), i - 1);
            if(getPitNo == 0) continue;
            int temp = this.performMove(i);
            int prevBestValue = this.getValue();
            this.handleValue(temp);

            if (prevBestValue < this.getValue()) {
                this.setBestChoice(i);
            }
            if (this.getAlpha() >= this.getBeta()) {
                //System.out.println("Other msx nodes are pruned");
                return this.getValue();
            }
        }
        return this.getValue();
    }


    public int performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno());
       // System.out.println("Chaging from this");
       // mancalaBoard.printBoard();
        b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), position);
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno()) - addCoins;
        if (b) {
            Node node1 = new MaxNode(mancalaBoard, mancalaPlayer, this.additionalMoves + 1, weights, this.depth);
            node1.setAlpha(this.alpha);
            node1.setBeta(this.beta);
           // System.out.println("after move");
         //   mancalaBoard.printBoard();
            return node1.expandNode();
        } else {
            Node node = new MinNode(mancalaBoard, mancalaPlayer, this.additionalMoves, weights, this.depth - 1);
            if (addCoins > 1) {
                node.setStonesCaptured(addCoins - 1);
            }
            node.setAlpha(this.alpha);
            node.setBeta(this.beta);
           // System.out.println("after move");
        //    mancalaBoard.printBoard();
            return node.expandNode();
        }


    }

}
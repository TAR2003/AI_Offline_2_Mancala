import java.lang.reflect.Array;
import java.util.ArrayList;

class MaxNode extends Node {

    public MaxNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights, int depth) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights, depth);

    }

    public void handleValue(int value) {
        setAlpha(Integer.max(this.getAlpha(), value)); // setting the most value from the child nodes in alpha for maxnode
        setValue(this.getAlpha()); // setting the best value as alpha value
    }

    public int expandNode() {
        if (this.depth == 0) {
            return this.getValueOfBoard(); // if the depth is 0, then return the current value of the board based on the heuristics
        }
        if (mancalaBoard.isEmpty(mancalaPlayer.getPlayerno())) {
            return this.getValueOfBoard(); // if there is no stones in the player's side, then there is no valid move possible after that
        }
        for (int i = 1; i <= 6; i++) {

            int getPitNo = mancalaBoard.getValueBoard(mancalaPlayer.getPlayerno(), i - 1);
            if (getPitNo == 0)
                continue; // if the selected board position does not contain any stone, skip this position
            int temp = this.performMove(i); // finding the best value from the child nodes from selecting i position could give
            int prevBestValue = this.getValue(); // saving the previous best value
            this.handleValue(temp); // handle the values, for max nodes, alpha values will be changed, and for min node, beta value is changed

            if (prevBestValue < this.getValue()) {
                this.setBestChoice(i); // if the value has changed in this step, then currently this i position is the most logical choice
            }
            if (this.getAlpha() >= this.getBeta()) {
                // if alpha value is equal or more than beta, we prune further child nodes as the rule of alpha beta pruning
                return this.getValue(); // returning the current best position we have
            }
        }
        return this.getValue(); // if there is no moves possible, then whatever move we select, the most expecting value will be the same
    }

    // performing the move based on the position provided from the parent node
    public int performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno()); // saving the total stones number for the player

        b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), position); // boolean b storing if the player will have a bonus turn
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno()) - addCoins; // getting how much stones has increased in this step
        if (b) {
            // the player will get a bonus turn, so build another max node with the same depth and calculate its most suitable value
            Node node1 = new MaxNode(mancalaBoard, mancalaPlayer, this.additionalMoves + 1, weights, this.depth);
            node1.setAlpha(this.alpha);
            node1.setBeta(this.beta); // passing the alpha beta values

            return node1.expandNode(); // returning the most suitable value that could provide
        } else {
            // the turn will be now given to the opponent, so a min node is necessary with depth of one less than the current node
            Node node = new MinNode(mancalaBoard, mancalaPlayer, this.additionalMoves, weights, this.depth - 1);
            if (addCoins > 1) {
                node.setStonesCaptured(addCoins - 1); // if the stones has increased more than 1, then we have captured stones from the opponent's side as well
            }
            node.setAlpha(this.alpha);
            node.setBeta(this.beta); // passing the alpha beta values

            return node.expandNode(); // return the most suitable value that node could provide
        }


    }

}
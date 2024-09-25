import java.util.ArrayList;

class MinNode extends Node {

    public MinNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights, int depth) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights, depth);

    }

    public void handleValue(int value) {
        this.setBeta(Integer.min(this.getBeta(), value)); // setting the minimum value from its children as the beta value
        this.setValue(this.getBeta()); // setting beta value as the best value
    }

    public int expandNode() {

        if (this.depth == 0) {
            return this.getValueOfBoard(); // if the depth is 0, then  return the value of the current mancala board position gives
        }
        if (mancalaBoard.isEmpty(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()))) {
            return this.getValueOfBoard(); // if the the side of opponent's mancala is empty, then no further moves are possible right now
        }
        for (int i = 1; i <= 6; i++) {

            int getPitNo = mancalaBoard.getValueBoard(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()), i - 1);
            if (getPitNo == 0) continue; // opponent's mancala board at position i is 0, so we skip it as it is not possible
            int temp = this.performMove(i); // getting the most suitable value from performing in i position
            int prevBestValue = this.getValue(); // storing the previous best
            this.handleValue(temp); // changing the beta value according to child nodes output values
            if (prevBestValue > this.getValue()) {
                this.setBestChoice(i); // if the new value is more suitable than the previous one, save it as the best choice till now
            }
            if (this.getAlpha() >= this.getBeta()) {
                // as aplha is more than or equal to beta, we are pruning further child nodes expanding
                return this.getValue(); // returning the most suitable value we have till now
            }
        }
        return this.getValue(); // returning the most suitable value after expanding all the possible nodes
    }
    // perform a move on the mancala board and expand
    public int performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;

        addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno())); // save the current stone number of opponent
        b = mancalaBoard.moveBoard(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()), position); // boolean b is saving if opponent gets a bonus turn
        addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno())) - addCoins; // finding how much stone number is increased for the opponent

        if (b) { // opponent will get a bonus move for that choice
            Node node1 = new MinNode(mancalaBoard, mancalaPlayer, this.additionalMoves, weights, this.depth); // keeps the same depth
            node1.setAlpha(this.alpha);
            node1.setBeta(this.beta); // passing the alpha beta values

            return node1.expandNode(); // returning the most suitable value possible
        } else { // opponent will not get a bonnus move
            Node node = new MaxNode(mancalaBoard, mancalaPlayer, this.additionalMoves, weights, this.depth - 1); // next move will be a max node as it will be the player's move
            if (addCoins > 1) { // if the stone increase is more than 1, then opponent has captured some of the stones at my side as well
                node.setStonesCaptured(-(addCoins - 1)); // set the captured stones values, minus because it is for the opponent
            }
            node.setAlpha(this.alpha);
            node.setBeta(this.beta); // passing the alpha beta values

            return node.expandNode(); // returning the most suitable value possible from its child nodes
        }
    }

}
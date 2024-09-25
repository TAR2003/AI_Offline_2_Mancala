import java.util.ArrayList;

class MinNode extends Node {

    public MinNode(MancalaBoard mancalaBoard, MancalaPlayer mancalaPlayer, int additionalMoves, ArrayList<Integer> weights, int depth) {
        super(mancalaBoard, mancalaPlayer, additionalMoves, weights, depth);
        // System.out.println("New Min Node added");
    }

    public void handleValue(int value) {
        this.setBeta(Integer.min(this.getBeta(), value));
        this.setValue(this.getBeta()); // setting beta value as the best value
    }

    public int expandNode() {

        if (this.depth == 0) {
            return this.getValueOfBoard();
        }
        if (mancalaBoard.isEmpty(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()))) {
            return this.getValueOfBoard();
        }
        for (int i = 1; i <= 6; i++) {

            int getPitNo = mancalaBoard.getValueBoard(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()), i - 1);
            if (getPitNo == 0) continue;
            int temp = this.performMove(i);
            int prevBestValue = this.getValue();
            this.handleValue(temp);
            if (prevBestValue > this.getValue()) {
                this.setBestChoice(i);
            }
            if (this.getAlpha() >= this.getBeta()) {

                return this.getValue();
            }
        }
        return this.getValue();
    }

    public int performMove(int position) {
        MancalaBoard mancalaBoard = this.mancalaBoard.newMancalaBoard();
        boolean b;
        int addCoins;

        addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()));
        b = mancalaBoard.moveBoard(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno()), position);
        addCoins = mancalaBoard.getStones(mancalaBoard.otherPlayer(mancalaPlayer.getPlayerno())) - addCoins;
        addCoins = mancalaBoard.getStones(mancalaPlayer.getPlayerno()) - addCoins;
        if (b) {
            Node node1 = new MinNode(mancalaBoard, mancalaPlayer, this.additionalMoves, weights, this.depth);
            node1.setAlpha(this.alpha);
            node1.setBeta(this.beta);

            return node1.expandNode();
        } else {
            Node node = new MaxNode(mancalaBoard, mancalaPlayer, this.additionalMoves, weights, this.depth - 1);
            if (addCoins > 1) {
                node.setStonesCaptured(addCoins - 1);
            }
            node.setAlpha(this.alpha);
            node.setBeta(this.beta);

            return node.expandNode();
        }
    }

}
import java.util.Random;

public class MancalaMatch {
    public MancalaPlayer mancalaPlayer1, mancalaPlayer2;
    public MancalaBoard mancalaBoard;
    int pos1, pos2;

    public MancalaMatch(MancalaPlayer mancalaPlayer1, MancalaPlayer mancalaPlayer2) {
        this.mancalaPlayer1 = mancalaPlayer1;
        this.mancalaPlayer2 = mancalaPlayer2;
        Random random = new Random();
        pos1 = random.nextInt(2);
        pos2 = (pos1 + 1) % 2;
        this.mancalaPlayer2.setPlayerno(pos2);
        this.mancalaPlayer1.setPlayerno(pos1);
        this.mancalaBoard = this.mancalaPlayer1.mancalaBoard;
        this.startPlay();
        System.out.println("Mancala player 1 got:= " + mancalaBoard.getStones(mancalaPlayer1.getPlayerno()));
        System.out.println("Mancala player 2 got:= " + mancalaBoard.getStones(mancalaPlayer2.getPlayerno()));
    }

    public void startPlay() {
        if (this.mancalaPlayer1.getPlayerno() == 0) {
            makeMove(this.mancalaPlayer1);
        } else {
            makeMove(this.mancalaPlayer2);
        }
    }

    public int checkWinning() {
        if (this.mancalaBoard.getStones(this.mancalaPlayer1.getPlayerno()) > 24) {
            System.out.println("Mancala Player 1 has won the game");
            return 1;
        } else if (this.mancalaBoard.getStones(this.mancalaPlayer2.getPlayerno()) > 24) {
            System.out.println("Mancala Player 2 has won the game");
            return -1;
        } else if ((this.mancalaBoard.getStones(this.mancalaPlayer1.getPlayerno()) == 24) && (this.mancalaBoard.getStones(this.mancalaPlayer2.getMove()) == 24)) {
            return 2;
        }
        return 0;
    }

    public int makeMove(MancalaPlayer mancalaPlayer) {
        int move = mancalaPlayer.getMove();
        boolean b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), move);
        // System.out.println("B value " + b + " mancalaplayerno" + mancalaPlayer.getPlayerno() + " move=" + move);
        // mancalaBoard.printBoard();
        int status = checkWinning();
        if (status == 2) {
            System.out.println("Match tied");
            return status;
        } else if (status != 0) {
            return status;
        } else {
            if (b) {
                return makeMove(mancalaPlayer);
            } else {
                return makeMove((mancalaPlayer == mancalaPlayer1) ? mancalaPlayer2 : mancalaPlayer1);
            }

        }
    }
}

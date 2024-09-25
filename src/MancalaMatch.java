import java.io.BufferedWriter;
import java.util.Random;

public class MancalaMatch {
    public MancalaPlayer mancalaPlayer1, mancalaPlayer2;
    public MancalaBoard mancalaBoard;
    int pos1, pos2;
    int status;

    public BufferedWriter getBw() {
        return bw;
    }

    public void setBw(BufferedWriter bw) {
        this.bw = bw;
    }

    BufferedWriter bw;

    public MancalaMatch(MancalaPlayer mancalaPlayer1, MancalaPlayer mancalaPlayer2) {
        this.mancalaPlayer1 = mancalaPlayer1;
        this.mancalaPlayer2 = mancalaPlayer2;
        Random random = new Random();
        pos1 = random.nextInt(2);
        pos2 = (pos1 + 1) % 2;
        this.mancalaPlayer2.setPlayerno(pos2);
        this.mancalaPlayer1.setPlayerno(pos1);
        this.mancalaBoard = this.mancalaPlayer1.mancalaBoard;


    }

    public void startPlay() {
        System.out.println();
        System.out.println("--------------------- The game is starting right now ---------------------");
        if (this.mancalaPlayer1.getPlayerno() == 0) {
           status = makeMove(this.mancalaPlayer1);
        } else {
            status = makeMove(this.mancalaPlayer2);
        }
        if (status == 2) {
            System.out.println("The match is tied, sorry");
        }
        System.out.println("Mancala player " + mancalaPlayer1.getPlayerno() + " got:= " + mancalaBoard.getStones(mancalaPlayer1.getPlayerno()));
        System.out.println("Mancala player " + mancalaPlayer2.getPlayerno() + " got:= " + mancalaBoard.getStones(mancalaPlayer2.getPlayerno()));
        System.out.println("The situation of the mancala board after finishing:=");
        mancalaBoard.printBoard();
        System.out.println();
    }

    public int checkWinning() {
        if (this.mancalaBoard.getStones(this.mancalaPlayer1.getPlayerno()) > 24) {
            System.out.println("Mancala Player 1 has won the game");
            return 1;
        } else if (this.mancalaBoard.getStones(this.mancalaPlayer2.getPlayerno()) > 24) {
            System.out.println("Mancala Player 2 has won the game");
            return -1;
        } else if ((this.mancalaBoard.getStones(this.mancalaPlayer1.getPlayerno()) == 24) && (this.mancalaBoard.getStones(this.mancalaPlayer2.getPlayerno()) == 24)) {
            mancalaBoard.printBoard();
            System.out.println("The match is tied");
            return 2;
        }
        return 0;
    }

    public int makeMove(MancalaPlayer mancalaPlayer) {

        int move = mancalaPlayer.getMove();
        if (move == 0) {
            return makeMove((mancalaPlayer == mancalaPlayer1) ? mancalaPlayer2 : mancalaPlayer1);
        }

        boolean b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), move);
        mancalaBoard.printBoard();
        int status = checkWinning();
        if (status != 0) {
            return status;
        } else {
            if (b) {
                return makeMove(mancalaPlayer);
            } else {
                return makeMove((mancalaPlayer == mancalaPlayer1) ? mancalaPlayer2 : mancalaPlayer1);
            }

        }
    }
    public void randomMove() {
        System.out.println("Giving one random move before starting pc vs pc");
        Random random = new Random();
        int player = random.nextInt(2);
        MancalaPlayer mancalaPlayer;
        if(player == 0) mancalaPlayer = mancalaPlayer1;
        else mancalaPlayer = mancalaPlayer2;
        int move = random.nextInt(6);
        mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), move + 1);
    }
}

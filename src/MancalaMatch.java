import java.io.BufferedWriter;
import java.util.Random;

public class MancalaMatch {
    public MancalaPlayer mancalaPlayer1, mancalaPlayer2;
    public MancalaBoard mancalaBoard;
    int pos1, pos2;
    int status;

    // a new mancala match between two players
    public MancalaMatch(MancalaPlayer mancalaPlayer1, MancalaPlayer mancalaPlayer2) {
        this.mancalaPlayer1 = mancalaPlayer1;
        this.mancalaPlayer2 = mancalaPlayer2;
        Random random = new Random();
        pos1 = random.nextInt(2);
        pos2 = (pos1 + 1) % 2;
        this.mancalaPlayer2.setPlayerno(pos2);
        this.mancalaPlayer1.setPlayerno(pos1); // the player who will give the first move can be anyone of the two, it is completely random
        this.mancalaBoard = this.mancalaPlayer1.mancalaBoard;


    }
    // starting the game of mancala
    public void startPlay() {
        System.out.println();
        System.out.println("--------------------- The game is starting right now ---------------------");
        if (this.mancalaPlayer1.getPlayerno() == 0) { // giving the first move to the player which has a player number of 0
           status = makeMove(this.mancalaPlayer1);
        } else {
            status = makeMove(this.mancalaPlayer2);
        }
        if (status == 2) {
            System.out.println("The match is tied, sorry"); // status 2 convenes the message that the match has been tied
        }
        System.out.println("Mancala player " + mancalaPlayer1.getPlayerno() + " got:= " + mancalaBoard.getStones(mancalaPlayer1.getPlayerno()));
        System.out.println("Mancala player " + mancalaPlayer2.getPlayerno() + " got:= " + mancalaBoard.getStones(mancalaPlayer2.getPlayerno()));
        // showing the result of the game
        System.out.println("The situation of the mancala board after finishing:=");
        mancalaBoard.printBoard(); // showing the final situation of the board after the game has ended
        System.out.println();
    }
    // to check if one of the players have already won the game
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
            return 2; // giving the tie message
        }
        return 0;
    }
    // mancalaPlayer will give a move now
    public int makeMove(MancalaPlayer mancalaPlayer) {

        int move = mancalaPlayer.getMove(); // getting the move mancala player will give at this current situation
        if (move == 0) { // player has failed to perform a move
            return makeMove((mancalaPlayer == mancalaPlayer1) ? mancalaPlayer2 : mancalaPlayer1); // the turn will now go to the other player
        }

        boolean b = mancalaBoard.moveBoard(mancalaPlayer.getPlayerno(), move); // checking if the player can have a bonus move
        mancalaBoard.printBoard();
        int status = checkWinning();
        if (status != 0) {
            return status; // the game has ended, status convenes message
        } else {
            if (b) {
                return makeMove(mancalaPlayer); // player has earned an additional move, he will continue
            } else {
                return makeMove((mancalaPlayer == mancalaPlayer1) ? mancalaPlayer2 : mancalaPlayer1); // Now, the other player will play the game
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

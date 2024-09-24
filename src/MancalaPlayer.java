import java.util.Random;
import java.util.Scanner;

public class MancalaPlayer {
    public MancalaBoard mancalaBoard;
    public Random random;

    public int getPlayerno() {
        return playerno;
    }

    public void setPlayerno(int playerno) {
        this.playerno = playerno;
    }

    public int playerno;

    public MancalaPlayer(MancalaBoard mancalaBoard) {
        this.mancalaBoard = mancalaBoard;
        random = new Random();

    }
    public int getMove() {

        int move = random.nextInt(6);

        return move + 1;
    }
}

class Player extends MancalaPlayer {
    public Scanner scanner;
    public Player(MancalaBoard mancalaBoard) {
        super(mancalaBoard);
        scanner = new Scanner(System.in);
    }
    public int getMove() {
        int move = scanner.nextInt();
        scanner.nextLine();
        return move;
    }
}

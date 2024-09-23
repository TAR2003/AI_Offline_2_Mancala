public class MancalaBoard {
    int playerOneStones, playerTwoStones;
    int[] playerOneBoard, playerTwoBoard;

    public MancalaBoard() {
        playerOneBoard = new int[6];
        playerTwoBoard = new int[6];
        for (int i = 0; i < 6; i++) {
            playerOneBoard[i] = 4;
            playerTwoBoard[i] = 4;
            playerOneStones = 0;
            playerTwoStones = 0;
        }
    }

    MancalaBoard newMancalaBoard() {
        MancalaBoard mn = new MancalaBoard();
        mn.playerTwoStones = this.playerTwoStones;
        mn.playerOneStones = this.playerOneStones;
        for (int i = 0; i < 6; i++) {
            mn.playerOneBoard[i] = this.playerOneBoard[i];
            mn.playerTwoBoard[i] = this.playerTwoBoard[i];
        }
        return mn;
    }

    void setValueBoard(int player, int position, int value) {
        if (player == 0) {
            playerOneBoard[position] = value;
        } else {
            playerTwoBoard[position] = value;
        }
    }

    int getValueBoard(int player, int position) {
        if (player == 0) {
            return playerOneBoard[position];
        } else {
            return playerTwoBoard[position];
        }
    }

    void setStones(int player, int value) {
        if (player == 0) {
            playerOneStones = value;
        } else {
            playerTwoStones = value;
        }
    }

    int getStones(int player) {
        if (player == 0) {
            return playerOneStones;
        } else {
            return playerTwoStones;
        }
    }

    int otherPlayer(int player) {
        return (player + 1) % 2;
    }


    // player one will have a player number 0, and the second one will have number 1
    boolean moveBoard(int player, int position) {

        int numberofstones = getValueBoard(player, position - 1);
        setValueBoard(player, position - 1, 0);
        int difference = 7 - position;
        int initialposition = position;
        for (int i = initialposition; i < 6; i++) {
            if (numberofstones > 0) {
                // if remaining stones are more than 0, then add a stone in every remaining boards
                setValueBoard(player, i, getValueBoard(player, i) + 1);
                numberofstones--;
            }
            if (numberofstones == 0 && getValueBoard(player, i) == 1 && getValueBoard(otherPlayer(player), 5-i) > 0) {
                setStones(player, getStones(player) + 1 + getValueBoard(otherPlayer(player), 5-i));
                setValueBoard(otherPlayer(player), 5-i, 0);
                setValueBoard(player, i, 0);
                return false; // if the last stone is not in the player's place, then return false
                // if the last stone is in the blank position in players own board and there is
                // stones in the other player's side, then all of the stones from both of the boards will be added to players collection
            }
        }
        if (numberofstones > 0) {
            setStones(player, getStones(player) + 1);
            numberofstones--;
            if (numberofstones == 0) {
                return true; // the last stone is in the players collection, thus player will get another turn
            }
        }
        for (int i = 0;i<6;i++) {
            // remaining stones now will be given to the opposite side
            if (numberofstones > 0) {
                setValueBoard(otherPlayer(player), i, getValueBoard(otherPlayer(player), i) + 1);
                numberofstones--;
            }
        }

        // one step done, now still if it has coins then we will distribute them in a circular way

        for (int i = 0; i < 6; i++) {
            if (numberofstones > 0) {
                // if remaining stones are more than 0, then add a stone in every remaining boards
                setValueBoard(player, i, getValueBoard(player, i) + 1);
                numberofstones--;
            }
            if (numberofstones == 0 && getValueBoard(player, i) == 1 && getValueBoard(otherPlayer(player), 5-i) > 0) {
                setStones(player, getStones(player) + 1 + getValueBoard(otherPlayer(player), 5-i));
                setValueBoard(otherPlayer(player), 5-i, 0);
                setValueBoard(player, i, 0);
                return false; // if the last stone is not in the player's place, then return false
                // if the last stone is in the blank position in players own board and there is
                // stones in the other player's side, then all of the stones from both of the boards will be added to players collection
            }
        }
        if (numberofstones > 0) {
            setStones(player, getStones(player) + 1);
            numberofstones--;
            if (numberofstones == 0) {
                return true; // the last stone is in the players collection, thus player will get another turn
            }
        }
        for (int i = 0;i<6;i++) {
            // remaining stones now will be given to the opposite side
            if (numberofstones > 0) {
                setValueBoard(otherPlayer(player), i, getValueBoard(otherPlayer(player), i) + 1);
                numberofstones--;
            }
        }

        return false;
    }

    // for printing the mancala board
    void printBoard() {
        System.out.println("Player two:= " + playerTwoStones);
        for (int i = 5; i >= 0; i--) {
            System.out.print(playerTwoBoard[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            System.out.print(playerOneBoard[i] + " ");
        }
        System.out.println();
        System.out.println("Player one:= " + playerOneStones);
    }

    int totalStonesInSide(int player) {
        int sum = 0;
        for(int i = 0 ; i < 6; i++) {
            sum += getValueBoard(player, i);
        }
        return sum;
    }
}

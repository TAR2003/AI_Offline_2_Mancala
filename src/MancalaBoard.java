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
        for (int i = position; i < 6; i++) {
            if (numberofstones > 0) {
                // if remaining stones are more than 0, then add a stone in every remaining boards
                setValueBoard(player, i, getValueBoard(player, i) + 1);
                numberofstones--;
                if (numberofstones == 0 && getValueBoard(player, i) == 1 && getValueBoard(otherPlayer(player), 5 - i) > 0) {
                    setStones(player, getStones(player) + 1 + getValueBoard(otherPlayer(player), 5 - i));
                    setValueBoard(otherPlayer(player), 5 - i, 0);
                    setValueBoard(player, i, 0);
                    return false; // if the last stone is not in the player's place, then return false
                    // if the last stone is in the blank position in players own board and there is
                    // stones in the other player's side, then all the stones from both of the boards will be added to players collection
                }
            }

        }
        if (numberofstones > 0) {
            setStones(player, getStones(player) + 1);
            numberofstones--;
            if (numberofstones == 0) {
                return true; // the last stone is in the players collection, thus player will get another turn
            }
        }
        for (int i = 0; i < 6; i++) {
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
                if (numberofstones == 0 && getValueBoard(player, i) == 1 && getValueBoard(otherPlayer(player), 5 - i) > 0) {
                    setStones(player, getStones(player) + 1 + getValueBoard(otherPlayer(player), 5 - i));
                    setValueBoard(otherPlayer(player), 5 - i, 0);
                    setValueBoard(player, i, 0);
                    return false; // if the last stone is not in the player's place, then return false
                    // if the last stone is in the blank position in players own board and there is
                    // stones in the other player's side, then all the stones from both of the boards will be added to players collection
                }
            }

        }
        if (numberofstones > 0) {
            setStones(player, getStones(player) + 1);
            numberofstones--;
            if (numberofstones == 0) {
                return true; // the last stone is in the players collection, thus player will get another turn
            }
        }
        for (int i = 0; i < 6; i++) {
            // remaining stones now will be given to the opposite side
            if (numberofstones > 0) {
                setValueBoard(otherPlayer(player), i, getValueBoard(otherPlayer(player), i) + 1);
                numberofstones--;
            }
        }

        return false;
    }

    void printFromPerspective(int player) {
        String dash = "----------------------------------------------------------------------------------";
        System.out.println(dash);
        System.out.println(dash);
        System.out.print(this.getPaddedString("Opponents Stones", 20));
        System.out.print(" || ");
        for(int i = 5 ; i >= 0 ; i--) {
            int v = this.getValueBoard(this.otherPlayer(player), i);

            System.out.print(this.getPaddedString(String.valueOf(v), 4));

        }
        System.out.print(" || ");
        System.out.println(this.getPaddedString("Your Stones", 14));

        System.out.println(dash);
        System.out.print(this.getPaddedString(String.valueOf(this.getStones(this.otherPlayer(player))), 20));
        System.out.print(" || ");
        for(int i = 0 ; i < 6 ; i++) {
            int v = this.getValueBoard(player, i);
            System.out.print(this.getPaddedString(String.valueOf(v), 4));

        }
        System.out.print(" || ");
        System.out.println(this.getPaddedString(String.valueOf(this.getStones(player)), 14));
        System.out.println(dash);
        System.out.println(dash);
    }

    // for printing the mancala board
    void printBoard() {
        int player = 0;
        String dash = "----------------------------------------------------------------------------------";
        System.out.println(dash);
        System.out.println(dash);
        System.out.print(this.getPaddedString("Player 1 Stones", 20));
        System.out.print(" || ");
        for(int i = 5 ; i >= 0 ; i--) {
            int v = this.getValueBoard(this.otherPlayer(player), i);

            System.out.print(this.getPaddedString(String.valueOf(v), 4));

        }
        System.out.print(" || ");
        System.out.println(this.getPaddedString("Player 0 Stones", 14));

        System.out.println(dash);
        System.out.print(this.getPaddedString(String.valueOf(this.getStones(this.otherPlayer(player))), 30));
        System.out.print(" || ");
        for(int i = 0 ; i < 6 ; i++) {
            int v = this.getValueBoard(player, i);
            System.out.print(this.getPaddedString(String.valueOf(v), 4));

        }
        System.out.print(" || ");
        System.out.println(this.getPaddedString(String.valueOf(this.getStones(player)), 14));
        System.out.println(dash);
        System.out.println(dash);
    }

    int totalStonesInSide(int player) {
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            sum += getValueBoard(player, i);
        }
        return sum;
    }

    boolean isEmpty(int player) {
        int temp = newMancalaBoard().totalStonesInSide(player);
        return temp == 0;

    }
    private String getPaddedString(String str, int size) {
        int length = str.length();
        int totalpadding = size - length;
        int padding1 = totalpadding / 2;
        int padding2 = totalpadding - padding1;
        return " ".repeat(Math.max(0, padding1)) +
                str +
                " ".repeat(Math.max(0, padding2));
    }
}

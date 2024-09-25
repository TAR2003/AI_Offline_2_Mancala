import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        int depth = 5;
        ArrayList<Integer> version1weights = getArrayList(1, 0, 0, 0);
        ArrayList<Integer> version2weights = getArrayList(2, 1, 0, 0);
        ArrayList<Integer> version3weights = getArrayList(3, 2, 1, 0);
        ArrayList<Integer> version4weights = getArrayList(4, 3, 2, 1);
        ArrayList<ArrayList<Integer>> allweights = new ArrayList<>(4);
        allweights.add(version1weights);
        allweights.add(version2weights);
        allweights.add(version3weights);
        allweights.add(version4weights);
        System.out.println("\n\n................... Calculating which is the best heuristics by playing them one another ..................");
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("Processings.txt"));
        PrintStream original = System.out;
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                bw2.write(b);
            }
        });
        System.setOut(printStream);
        StringBuilder report = new StringBuilder("");
        int[] winnings = new int[4];
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int one = 0, two = 0, ties = 0;
                for (int k = 0; k < 100; k++) {
                    MancalaBoard mancalaBoard = new MancalaBoard();
                    MancalaPlayer mancalaPlayer1 = new MancalaPlayer(mancalaBoard, allweights.get(i), depth);
                    MancalaPlayer mancalaPlayer2 = new MancalaPlayer(mancalaBoard, allweights.get(j), depth);
                    MancalaMatch mancalaMatch = new MancalaMatch(mancalaPlayer1, mancalaPlayer2);
                    mancalaMatch.randomMove();
                    mancalaMatch.startPlay();
                    if (mancalaMatch.status == 1) one++;
                    else if (mancalaMatch.status == -1) two++;
                    else if (mancalaMatch.status == 2) ties++;
                }
                report.append("Winnings for the ").append(i + 1).append("th heuristics is = ").append(one).append('\n');
                report.append("Winnings for the ").append(j + 1).append("th heuristics is = ").append(two).append('\n');
                report.append("Match tied between ").append(i + 1).append("th and ").append(j + 1).append("th heuristics is = ").append(ties).append('\n');
                report.append('\n');
                winnings[i] += one;
                winnings[j] += two;
            }
        }
        bw2.close();
        System.setOut(original);
        System.out.println("Printing the Report:=");
        System.out.println(report);
        //System.out.println(mancalaPlayer1.getMove());

        System.out.println("Total winnings of all the heuristics:=");
        System.out.println("Heuristics 1 := " + winnings[0]);
        System.out.println("Heuristics 2 := " + winnings[1]);
        System.out.println("Heuristics 3 := " + winnings[2]);
        System.out.println("Heuristics 4 := " + winnings[3]);


        System.out.println("Process completed successfully");
        System.out.println("If you want to play click y, else press any key");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        if (str.equalsIgnoreCase("Y")) {
            int choice;
            while (true) {
                System.out.println("In what mode you want to play?");
                System.out.println("Press 1 => heuristic-1: The evaluation function is (stones_in_my_storage – stones_in_opponents_storage)");
                System.out.println("Press 2 => heuristic-2: The evaluation function is W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side – stones_on_opponents_side) ");
                System.out.println("Press 3 => heuristic-3: The evaluation function is W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side – stones_on_opponents_side) + W3 * (additional_move_earned) ");
                System.out.println("Press 4 => heuristic-4: The evaluation function is W1 * (stones_in_my_storage – stones_in_opponents_storage) + W2 * (stones_on_my_side – stones_on_opponents_side) + W3 * (additional_move_earned) + W4 * (stones_captured)");
                choice = scanner.nextInt();
                if (choice > 0 && choice < 5) {
                    choice--;
                    break;
                } else {
                    System.out.println("Please enter a valid choice between 1-4");
                }
            }
            MancalaBoard mancalaBoard1 = new MancalaBoard();
            MancalaPlayer player = new Player(mancalaBoard1, version1weights, depth);
            MancalaPlayer pc = new MancalaPlayer(mancalaBoard1, allweights.get(choice), depth);
            MancalaMatch mancalaMatch1 = new MancalaMatch(player, pc);
            mancalaMatch1.startPlay();
        } else {
            System.out.println("--------------- Program ended successfully ---------------------");
        }


    }

    public static ArrayList<Integer> getArrayList(int W1, int W2, int W3, int W4) {
        ArrayList<Integer> weights = new ArrayList<>(4);
        weights.add(W1);
        weights.add(W2);
        weights.add(W3);
        weights.add(W4);
        return weights;
    }
}
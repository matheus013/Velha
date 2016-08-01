import Game.Node;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by matheus on 30/07/16.
 */
public class Main {


    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        ArrayList<ArrayList<Character>> table = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            table.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                table.get(i).add(' ');
            }
        }
        int row, col;
        System.out.println("Want start?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
        System.out.println("Game format [row] [col]");

        int init = cin.nextInt();
        print(table);
        if (init == 1) {
            System.out.println("You turn: ");
            row = cin.nextInt();
            col = cin.nextInt();
            table.get(row).set(col, 'O');
        }
        while (true) {
            Node root = new Node(table, null, 0);
            root.max();
            table = root.best();
            print(table);
            System.out.printf(String.valueOf(root.end()));
            if (!root.end().equals(' ')) break;
            System.out.println("You turn: ");
            row = cin.nextInt();
            col = cin.nextInt();
            table.get(row).set(col, 'O');
        }
    }

    public static void print(ArrayList<ArrayList<Character>> table) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf(String.valueOf(table.get(i).get(j)));
                System.out.printf((j < 2) ? " | " : "\n");
            }
            if (i < 2) {
                System.out.println("----------");
            }
        }
        System.out.println("\n\n");
    }
}

package Game;

import java.util.ArrayList;

/**
 * Created by matheus on 30/07/16.
 */
public class Node {
    public ArrayList<ArrayList<Character>> table;
    public ArrayList<Node> children;
    public Node parent;
    public Integer level;
    public Integer score;

    public Node(ArrayList<ArrayList<Character>> table, Node parent, Integer level) {
        this.table = table;
        this.children = new ArrayList<>();
        this.level = level;
        this.parent = parent;
        this.score = 0;
    }

    public ArrayList<ArrayList<Character>> best() {
        int best = 0;
        if(this.children.isEmpty()) return table;
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i).score > this.children.get(best).score) {
                best = i;
            }
        }
        return this.children.get(best).copy();
    }

    public Boolean possible(int x, int y) {
        return this.get(x, y).equals(' ');
    }

    public void max() {
        Character finale = this.end();
        if (!finale.equals(' ')) {
            Node aux = this;
            while (aux != null) {
                if (finale == 'X')
                    aux.score++;
                else if (finale == 'O')
                    aux.score--;
                aux = aux.parent;
            }
            return;
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (possible(i, j)) {
                    ArrayList<ArrayList<Character>> aux = this.copy();
                    aux.get(i).set(j, (this.level % 2 == 0) ? 'X' : 'O');
                    this.children.add(new Node(aux, this, this.level + 1));
                }
            }
        for (int i = 0; i < this.children.size(); i++) {
            this.children.get(i).max();

        }
    }

    private Character get(int x, int y) {
        return this.table.get(x).get(y);
    }

    public Character end() {
        for (int i = 0; i < 3; i++) {
            Character aux = get(i, 0);
            Integer count = 0;
            for (int j = 0; j < 3; j++) {
                if (get(i, j) == aux) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == 3) {
                return aux;
            }
        }
        for (int i = 0; i < 3; i++) {
            Character aux = get(0, i);
            Integer count = 0;
            for (int j = 0; j < 3; j++) {
                if (get(j, i) == aux) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == 3) {
                return aux;
            }
        }
        Character aux = get(0, 0);
        for (int i = 0; i < 3; i++) {
            if (!get(i, i).equals(aux)) {
                break;
            } else if (i == 2) {
                return aux;
            }
        }
        aux = get(0, 2);
        for (int i = 0; i < 3; i++) {
            if (!get(i, 2 - i).equals(aux)) {
                break;
            }
            if (i == 2) {
                return aux;
            }
        }
        int count = 9;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                if (!get(i, j).equals(' ')) {
                    count--;
                }
            }
        if (count == 0)
            return 'E';
        return ' ';
    }

    public ArrayList<ArrayList<Character>> copy() {
        ArrayList<ArrayList<Character>> replica = new ArrayList<>();
        for (int i = 0; i < table.size(); i++) {
            replica.add(new ArrayList<>(table.get(i)));
        }
        return replica;
    }
}
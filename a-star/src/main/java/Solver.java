import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

public class Solver {
    private final static Comparator<SearchNode> MANHATTAN_COMPARATOR = Comparator.comparingInt(
            n -> (n.movesToHere + n.board.manhattan()));

    private final static Comparator<SearchNode> HAMMING_COMPARATOR = Comparator.comparingInt(
            n -> (n.movesToHere + n.board.hamming()));

    private final MinPQ<SearchNode> mainPQ = new MinPQ<>();
    private final MinPQ<SearchNode> twinPQ = new MinPQ<>();
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return null;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }



    private class SearchNode{
        private final int movesToHere;
        private final SearchNode prev;
        private final Board board;

        SearchNode(Board board, SearchNode prev, int movesToHere) {
            this.board = board;
            this.prev = prev;
            this.movesToHere = movesToHere;
        }
    }
}

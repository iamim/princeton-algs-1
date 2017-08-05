import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.LinkedList;

public class Solver {
    private final static Comparator<SearchNode> MANHATTAN_COMPARATOR = Comparator.comparingInt(
            n -> (n.movesToHere + n.board.manhattan()));

    private final boolean initialIsSolvable;
    private final LinkedList<Board> solution = new LinkedList<>();

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        boolean mainTurn = true;
        SearchNode solved = null;

        MinPQ<SearchNode> mainPQ = new MinPQ<>(MANHATTAN_COMPARATOR);
        mainPQ.insert(new SearchNode(initial, null, 0));

        MinPQ<SearchNode> twinPQ = new MinPQ<>(MANHATTAN_COMPARATOR);
        twinPQ.insert(new SearchNode(initial.twin(), null, 0));

        while (solved == null) {
            if (mainTurn)
                solved = tryToSolve(mainPQ);
            else
                solved = tryToSolve(twinPQ);

            mainTurn = !mainTurn;
        }

        initialIsSolvable = !mainTurn;

        if (initialIsSolvable) {
            solution.add(solved.board);

            while (solved.prev != null) {
                solution.addFirst(solved.prev.board);

                solved = solved.prev;
            }
        }

    }

    private SearchNode tryToSolve(MinPQ<SearchNode> PQ) {
        SearchNode min = PQ.delMin();

        if (min.board.isGoal()) return min;

        for (Board neighbor : min.board.neighbors()) {
            if (neighbor.equals(min.prev.board)) continue;

            PQ.insert(new SearchNode(neighbor, min, min.movesToHere + 1));
        }

        return null;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return initialIsSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (initialIsSolvable) return solution.size() - 1;
        return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (initialIsSolvable) return solution;
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

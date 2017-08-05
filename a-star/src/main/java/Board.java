import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int n;
    private final int[][] blocks;

    private int zeroRow = -1;
    private int zeroCol = -1;

    private final int hammingNumber;
    private final int manhattanNumber;

    private enum MOVE{FROM_UP, FROM_LEFT, FROM_DOWN, FROM_RIGHT}

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        n = blocks.length;

        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }

                this.blocks[i][j] = blocks[i][j];
            }
        }

        assert zeroRow != -1 && zeroCol != -1;

        hammingNumber = getHammingNumber(this.blocks);
        manhattanNumber = getManhattanNumber(this.blocks);
    }

    // board n n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        return hammingNumber;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattanNumber;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hammingNumber == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] blocksCopy = new int[n][];
        for (int i = 0; i < blocks.length; i++) {
            blocksCopy[i] = Arrays.copyOf(blocks[i], blocks[i].length);
        }

        int valA;
        int colA = 0;
        if (blocks[0][colA] == 0)
            valA = blocks[0][++colA];
        else
            valA = blocks[0][colA];

        int valB;
        int colB = 0;
        if (blocks[1][colB] == 0)
            valB = blocks[1][++colB];
        else
            valB = blocks[1][colB];

        blocksCopy[0][colA] = valB;
        blocksCopy[1][colB] = valA;

        return new Board(blocksCopy);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new LinkedList<>();

        if (zeroCol > 0) neighbors.add(makeMove(MOVE.FROM_LEFT));
        if (zeroCol < n - 1) neighbors.add(makeMove(MOVE.FROM_RIGHT));
        if (zeroRow > 0) neighbors.add(makeMove(MOVE.FROM_UP));
        if (zeroRow < n - 1) neighbors.add(makeMove(MOVE.FROM_DOWN));

        return neighbors;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n);
        s.append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int getHammingNumber(int[][] blocks) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) continue;
                if (blocks[i][j] != (i * n + j + 1) % (n * n)) count++;
            }
        }

        return count;
    }

    private int getManhattanNumber(int[][] blocks) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) continue;

                int val = blocks[i][j];
                int shouldRow = (val - 1) / n;
                int shouldCol = (val - shouldRow * n) - 1;

                count += Math.abs(shouldRow - i);
                count += Math.abs(shouldCol - j);
            }
        }

        return count;
    }

    private Board makeMove(MOVE fromDir) {
        int[][] newBlocks = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBlocks[i][j] = blocks[i][j];
            }
        }

        switch (fromDir) {
            case FROM_UP:
                newBlocks[zeroRow][zeroCol] = newBlocks[zeroRow - 1][zeroCol];
                newBlocks[zeroRow - 1][zeroCol] = 0;
                break;

            case FROM_LEFT:
                newBlocks[zeroRow][zeroCol] = newBlocks[zeroRow][zeroCol - 1];
                newBlocks[zeroRow][zeroCol - 1] = 0;
                break;

            case FROM_DOWN:
                newBlocks[zeroRow][zeroCol] = newBlocks[zeroRow + 1][zeroCol];
                newBlocks[zeroRow + 1][zeroCol] = 0;
                break;

            case FROM_RIGHT:
                newBlocks[zeroRow][zeroCol] = newBlocks[zeroRow][zeroCol + 1];
                newBlocks[zeroRow][zeroCol + 1] = 0;
                break;
        }

        return new Board(newBlocks);
    }
}

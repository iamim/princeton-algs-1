import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int MAX_NEIGHBORS = 4;

    private final WeightedQuickUnionUF uf;

    private final int topRootIdx;
    private final int bottomRootIdx;
    private final int dimension;

    private final boolean[] opened;
    private int nOfOpen;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Should have non-negative dimensions");

        topRootIdx = 0;
        bottomRootIdx = n * n + 1;
        dimension = n;

        uf = new WeightedQuickUnionUF(n * n + 2); // O(n2)

        opened = new boolean[n * n + 1]; // to account for 1-based indexes
        nOfOpen = 0;

        for (int i = 1; i <= n; i++) {
            uf.union(topRootIdx, i);
        }

        for (int i = n * n; i >= n * n - n + 1; i--) {
            uf.union(bottomRootIdx, i);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        int toOpen = idxFromRowCol(row, col);

        if (isOpen(toOpen)) return;

        opened[toOpen] = true;
        nOfOpen++;

        int[] idxAround = getIdxAround(row, col);
        for (int idx : idxAround) {
            if (validIdx(idx) && isOpen(idx)) {
                uf.union(toOpen, idx);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int toFind = idxFromRowCol(row, col);

        return isOpen(toFind);
    }

    private boolean isOpen(int idx) {
        return opened[idx];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int checkFullIdx = idxFromRowCol(row, col);

        return uf.connected(0, checkFullIdx) && isOpen(checkFullIdx);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return nOfOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (dimension == 1 && !isOpen(1)) return false;

        return uf.connected(topRootIdx, bottomRootIdx);
    }

    private int idxFromRowCol(int row, int col) {
        if (row > dimension || row < 1 || col > dimension || col < 1)
            throw new IllegalArgumentException();

        return (row - 1) * dimension + col;
    }

    private int[] getIdxAround(int row, int col) {
        int[] nearby = new int[MAX_NEIGHBORS];

        if (row > 1) nearby[0] = idxFromRowCol(row - 1, col);
        if (row < dimension) nearby[1] = idxFromRowCol(row + 1, col);
        if (col > 1) nearby[2] = idxFromRowCol(row, col - 1);
        if (col < dimension) nearby[3] = idxFromRowCol(row, col + 1);

        return nearby;
    }

    private boolean validIdx(int idx) {
        return idx >= 1 && idx <= dimension * dimension;
    }
}

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.

    public WeightedQuickUnionUF wquUF;
    public boolean[] wquArray;
    public int length;
    public int size;

    public int virtualTop;
    public int virtualBottom;

    public Percolation(int N) {
        wquUF = new WeightedQuickUnionUF(N * N + 2);
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        wquArray = new boolean[N * N];
        length = N;
        size = 0;

        connectTop();
    }

    public void open(int row, int col) {
        if (row * col > wquArray.length) {
            return;
        } else if (wquArray[calIndex(row, col)]) {
            return;
        } else {
            wquArray[calIndex(row, col)] = true;
            size++;
            if (row > 0) {
                if (wquArray[calIndex(row - 1, col)]) {
                    wquUF.union(calIndex(row, col), calIndex(row - 1, col));
                }
            }
            if (row < length - 1) {
                if (wquArray[calIndex(row + 1, col)]) {
                    wquUF.union(calIndex(row, col), calIndex(row + 1, col));
                }
            }
            if (col > 0) {
                if (wquArray[calIndex(row, col - 1)]) {
                    wquUF.union(calIndex(row, col), calIndex(row, col - 1));
                }
            }
            if (col < length - 1) {
                if (wquArray[calIndex(row, col + 1)]) {
                    wquUF.union(calIndex(row, col), calIndex(row, col + 1));
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row * col > wquArray.length) {
            return false;
        } else {
            return wquArray[calIndex(row, col)];
        }
    }

    public boolean isFull(int row, int col) {
        if (!wquArray[calIndex(row, col)]) {
            return false;
        }
        return wquUF.connected(calIndex(row, col), virtualTop);
    }

    public int numberOfOpenSites() {
        return size;
    }

    public boolean percolates() {
        connectBottom();
        return wquUF.connected(virtualTop, virtualBottom);
    }

    private int calIndex(int row, int col) {
        return row * length + col;
    }

    private void connectTop() {
        for (int i = 0; i < length; i++) {
            wquUF.union(calIndex(0, i), virtualTop);

        }
    }

    private void connectBottom() {
        for (int i = 0; i < length; i++) {
            wquUF.union(calIndex(length - 1, i), virtualBottom);
        }
    }

// TODO: Add any useful helper methods (we highly recommend this!).
// TODO: Remove all TODO comments before submitting.

}

public class UnionFind {
    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     * You can assume that we are only working with non-negative integers as the items
     * in our disjoint sets.
     */
    private final int[] data;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        data = new int[N];
        for (int i = 0; i < N; i++) {
            data[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        return Math.abs(data[find(v)]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        return data[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v > data.length - 1 || v < 0) {
            throw new IllegalArgumentException();
        }
        if (data[v] < 0) {
            return v;
        } else {
            int root = this.find(data[v]);
            data[v] = root;
            return root;
        }

    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if (v1 == v2) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if (sizeOf(root1) > sizeOf(root2)) {
            int size1 = sizeOf(root1);
            int size2 = sizeOf(root2);
            data[root2] = root1;
            data[root1] = -(size1 + size2);
        } else {
            int size1 = sizeOf(root1);
            int size2 = sizeOf(root2);
            data[root1] = root2;
            data[root2] = -(size1 + size2);
        }
    }

    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     */
    public int[] returnData() {
        return data;
    }
}

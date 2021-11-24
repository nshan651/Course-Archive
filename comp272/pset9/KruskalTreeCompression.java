package pset9;

import java.util.*;

public class KruskalTreeCompression {
    ArrayList<MyLinkedList<Integer>> vertexSet;
    int[] parent, height;
    static int numVertex;

    public KruskalTreeCompression(int vertices) {
        numVertex = vertices;
        parent = new int[numVertex];
        height = new int[numVertex];
        vertexSet = new ArrayList<>(numVertex);

        // Init parent set, height set, and vertex set
        for (int i =0; i < numVertex; i++) {
            parent[i] = i;
            height[i] = 0;
            vertexSet.add(new MyLinkedList<Integer>());
            vertexSet.get(i).addFirst(i);
        }
    }

    /** Finds the root of the set label containing x */
    public int find(int x) {
        int r =x, i =x;
        while (parent[r] != r)
            r = parent[r];
        // r is the root of the tree
        while (i != r) {
            int j = parent[i];
            parent[i] = r;
            i = j;
        }
        return r;
    }

    /** Merge sets a and b; assume a != b */
    public void merge(int a, int b) {
        if (height[a] == height[b]) { 
            height[a]++;
            parent[b] = a;
        }
        else {
            if (height[a] > height[b])
                parent[b] = a;
            else
                parent[a] = b;
        }
    }
    

}

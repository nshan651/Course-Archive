package pset9;

import java.util.*;

public class ConnectedComponents {
    ArrayList<MyLinkedList<Integer>> vertexSet;
    int[] parent;
    static int numVertex;

    public ConnectedComponents(int vertices) {
        numVertex = vertices;
        parent = new int[numVertex];
        vertexSet = new ArrayList<>(numVertex);

        // Init parent set and vertex set
        for (int i =0; i < numVertex; i++) {
            parent[i] = i;

            vertexSet.add(new MyLinkedList<Integer>());
            vertexSet.get(i).addFirst(i);
        }
    }

    public void merge(int v1, int v2) {
        int p = parent[v1];
        int q = parent[v2];

        int max =0, min =0;

        if (p != q) {
            int pSize = vertexSet.get(p).size();
            int qSize = vertexSet.get(q).size();
            
            if (pSize > qSize) {
                min = q;
                max = p;
            }
            else {
                min = p;
                max = q;
            }
            //int max = Math.max(pSize, qSize);
            //int min = Math.min(pSize, qSize);

            // Merge min with max
            MyLinkedList<Integer> minSet = vertexSet.get(min);
            vertexSet.get(max).appendList(vertexSet.get(min));

            // Shift components
            Node<Integer> n = minSet.getFirst();
            while (n != null) {
                parent[n.getInfo()] = max;
                n = n.getNext();
            }
            //shiftComponents(minSet, max);
        }
    }

}

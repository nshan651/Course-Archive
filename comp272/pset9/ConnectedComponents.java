package pset9;

import java.util.*;

public class ConnectedComponents {
    ArrayList<MyLinkedList<Integer>> vertexSet;
    int[] parent;
    static int numVertex;
    static int numEdge;

    public ConnectedComponents(int vertices, int edges) {
        numVertex = vertices;
        numEdge = edges;
        parent = new int[numVertex];
        vertexSet = new ArrayList<>(numVertex);

        // Init parent set and vertex set
        for (int i =0; i < numVertex; i++) {
            parent[i] = i;

            vertexSet.add(new MyLinkedList<Integer>());
            vertexSet.get(i).addFirst(i);
        }
    }

    public void shiftComponents (MyLinkedList<Integer> minSet, int max) {
        System.out.println("minSet size " + minSet.size());
        // Shift components
        if (!minSet.isEmpty()) {
            Node<Integer> n = minSet.getFirst();
            do {
                parent[n.getInfo()] = max;
                n = n.getNext();
            } while (n != null);
        } 
        else throw new NoSuchElementException();
    }

    public void merge(int v1, int v2) {
        int p = parent[v1];
        int q = parent[v2];

        if (p != q) {
            int pSize = vertexSet.get(p).size();
            int qSize = vertexSet.get(q).size();
            
            int max = Math.max(pSize, qSize);
            int min = Math.min(pSize, qSize);

            // Merge min with max
            MyLinkedList<Integer> minSet = vertexSet.get(min);
            vertexSet.get(max).appendList(minSet);
            shiftComponents(minSet, max);

            System.out.println("made it here");
        }
    }
    
}

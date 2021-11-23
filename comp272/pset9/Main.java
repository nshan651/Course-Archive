package pset9;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    
    public static void merge(ArrayList<MyLinkedList<Integer>> vertexSet, int[] parent, int v1, int v2) {
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
        }
    }

    /**
     * Krushal min spanning tree 
     * @param weights
     * @param edges
     * @return ArrayList of edges in MST, as well as double 
     * equal to sum of weights of the edges
     */
    public static ArrayList<WeightedEdge> krushal(PriorityQueue<WeightedEdge> que, int numVertex) {
        ArrayList<WeightedEdge> mst = new ArrayList<>(numVertex-1);

        //ConnectedComponents comps = new ConnectedComponents(numVertex);
        int[] parent = new int[numVertex];
        ArrayList<MyLinkedList<Integer>> vertexSet = new ArrayList<>(numVertex);

        // Init parent set and vertex set
        for (int i =0; i < numVertex; i++) {
            parent[i] = i;

            vertexSet.add(new MyLinkedList<Integer>());
            vertexSet.get(i).addFirst(i);
        }

        while (mst.size() < numVertex-1) {
            // Shortest edge not yet considered
            WeightedEdge curr = que.poll();

            int v1 = parent[curr.v1];
            int v2 = parent[curr.v2];

            if (v1 != v2) {
                mst.add(curr);
                merge(vertexSet, parent, curr.v1, curr.v2);
            }
        }
        return mst;
    }

    /**
     * Find the correct size to initialize the graph with
     * @param file The file of the graph to read
     * @return Max file size
     * @throws FileNotFoundException
     */
    private static int findMax(File file, String delim) throws FileNotFoundException {
        int max_size = 0;
        Scanner initsc = new Scanner(file);
        while (initsc.hasNextLine()) {
            String[] output = initsc.nextLine().split(delim);
            int p1 = Integer.parseInt(output[0]);
            int p2 = Integer.parseInt(output[1]);
            // Check both points for new max
            max_size = Math.max(max_size, p1);
            max_size = Math.max(max_size, p2);
        }
        initsc.close();
        return max_size;
    }

    /**
     * Get the minimum weight of the spanning tree
     * @param mst The min spanning tree
     * @return Sum of min weights
     */
    public static double minWeight(ArrayList<WeightedEdge> mst) {
        double total =0;
        for (WeightedEdge edge : mst) 
            total+=edge.getWeight();
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Read in edges and weights

        File edge_file= new File("./comp272/pset9/artist_edges.txt");
        File weight_file= new File("./comp272/pset9/Weights.txt");

        // Get the max vertex size
        int max_size = findMax(edge_file, "\t");
        //List<WeightedEdge> edgeList = new ArrayList<>(max_size+1);
        List<WeightedEdge> edgeList = new ArrayList<>();

        Scanner edgesc = new Scanner(edge_file);
        Scanner weightsc = new Scanner(weight_file);
        
        while (edgesc.hasNextLine() && weightsc.hasNextDouble()) {
            String[] output = edgesc.nextLine().split("\t");
            int p1 = Integer.parseInt(output[0]);
            int p2 = Integer.parseInt(output[1]);

            edgeList.add(new WeightedEdge(p1, p2, weightsc.nextDouble()));
        }
        edgesc.close();
        weightsc.close();

        PriorityQueue<WeightedEdge> que = new PriorityQueue<>(edgeList);
        
        ArrayList<WeightedEdge> mst = krushal(que, max_size+1);

        // Print results
        System.out.println("Max vertex label: " + (max_size+1));
        System.out.println("numer of edges: " + edgeList.size());
        System.out.println("edges considered for MST: " + (edgeList.size()-que.size()));
        System.out.println("Min weight of mst: " + minWeight(mst));
    }
}
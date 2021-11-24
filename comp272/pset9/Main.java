package pset9;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    /** Kruskal min spanning tree using union set */
    public static ArrayList<WeightedEdge> kruskalUnion(PriorityQueue<WeightedEdge> que, int numVertex) {
        ArrayList<WeightedEdge> mst = new ArrayList<>(numVertex-1);
        KruskalUnion ku = new KruskalUnion(numVertex);
        
        while (mst.size() < numVertex-1) {
            // Shortest edge not yet considered
            WeightedEdge curr = que.poll();

            int v1 = ku.parent[curr.v1];
            int v2 = ku.parent[curr.v2];

            if (v1 != v2) {
                mst.add(curr);
                ku.merge(curr.v1, curr.v2);
            }
        }
        return mst;
    }

    /** Kruskal min spanning tree using tree compression */
    public static ArrayList<WeightedEdge> kruskalCompression(PriorityQueue<WeightedEdge> que, int numVertex) {
        ArrayList<WeightedEdge> mst = new ArrayList<>(numVertex-1);
        KruskalTreeCompression ktc = new KruskalTreeCompression(numVertex);
        
        while (mst.size() < numVertex-1) {
            // Shortest edge not yet considered
            WeightedEdge curr = que.poll();

            int v1 = ktc.find(curr.v1);
            int v2 = ktc.find(curr.v2);

            if (v1 != v2) {
                mst.add(curr);
                ktc.merge(v1, v2);
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
        // Timing variables
        double start, end;

        // Read in edges and weights
        File edge_file = new File("./comp272/pset9/artist_edges.txt");
        File weight_file = new File("./comp272/pset9/Weights.txt");

        // Get the max vertex size
        int max_size = findMax(edge_file, "\t");
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
        PriorityQueue<WeightedEdge> que2 = new PriorityQueue<>(edgeList);
        // Print general results
        System.out.println("\nMax vertex label: " + (max_size+1));
        System.out.println("numer of edges: " + edgeList.size());

        // Kruskal Union
        start = System.currentTimeMillis();
        ArrayList<WeightedEdge> ku = kruskalUnion(que, max_size+1);
        end = System.currentTimeMillis();

        System.out.println("\n----- Kruskal Union ----- ");
        System.out.println("Min weight of mst: " + minWeight(ku));
        System.out.println("edges considered for MST: " + (edgeList.size()-que.size()));
        System.out.println((end-start) + " ms");

        // Kruskal Tree Compression
        start = System.currentTimeMillis();
        ArrayList<WeightedEdge> kst = kruskalCompression(que2, max_size+1);
        end = System.currentTimeMillis();

        System.out.println("\n----- Kruskal Compressed Tree ----- ");
        System.out.println("Min weight of mst: " + minWeight(kst));
        System.out.println("edges considered for MST: " + (edgeList.size()-que.size()));
        System.out.println((end-start) + " ms");
        
    }
}
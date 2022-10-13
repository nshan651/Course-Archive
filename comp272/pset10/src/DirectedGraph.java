package pset10.src;
import java.util.*;
import java.io.*;

public class DirectedGraph  {
    // Directed Graph structure
    ArrayList<DirectedNodeList> dGraph;
    // HashMap of SCCs
    HashMap<Integer, ArrayList<Integer>> comps;
    // Edge list for reduced graph initialization
    ArrayList<Edge> edges;
    int numVertex;
    boolean[] marked;
    // Finishing set
    int[] fin;
    // 
    int[] set;
    // k is the finishing number of vertex v
    int k;
    // Current leader in regular dft
    int currLeader;
    int compSize;
    
    
    public DirectedGraph() {
        dGraph = new ArrayList<>();
        numVertex = 0;  
    }
    
    public DirectedGraph(int n) {
        dGraph = new ArrayList<>(n);
        comps = new HashMap<Integer, ArrayList<Integer>>(n);
        numVertex = n;
        marked = new boolean[n];
        set = new int[n];
        fin = new int[n];
        edges = new ArrayList<>(n);
        k = 0;
        currLeader = 0;
        compSize = 0;
        for (int i=0;i<numVertex;i++)
            dGraph.add(new DirectedNodeList());
    }
    
    /**
     * Assume all vertices are created
     * Directed edge u to v will cause outdegree of u to go up and indegree of v to go up.
     * @param u
     * @param v
     */
    public void addEdge(int u, int v) {
        if (u>=0 && u<numVertex && v>=0 && v<numVertex) { 
            if (u!=v) {
                getNeighborList(u).addToOutList(v);
                getNeighborList(v).addToInList(u);
                edges.add(new Edge(u,v));
            }
        }
        else throw new IndexOutOfBoundsException();
    }
    
    /**
     * Get all neighbors of u
     * @param u
     * @return Neighbors DNL
     */
    public DirectedNodeList getNeighborList(int u) {
        return dGraph.get(u);
    }
    
    /**
     * InList and Outlist of the DNL
     * @param u
     */
    public void printAdjacency(int u) {
       DirectedNodeList dnl = getNeighborList(u);
       System.out.println ("vertices going into "+u+"  "+dnl.getInList());
       System.out.println ("vertices going out of "+u+"  "+dnl.getOutList());
       System.out.println();
    }
    
    /** Driver code for pDFT */
    public void postOrderDepthFirstTraversal() {
        this.marked = new boolean[numVertex];
        for (int i=0; i<numVertex; i++) 
            if (!marked[i]) 
                postOrderDFT(i);
    }

    /** 
     * Helper for pDFT
     * Works on the inlist instead of outlist 
     * so that there is no need to reverse
     */
    public void postOrderDFT(int v) {
        marked[v]=true;
        for (Integer u:dGraph.get(v).getInList()) 
            if (!marked[u]) 
                postOrderDFT(u);
        // k is the finishing number of vertex v
        fin[k] = v;
        k++;
    }

    /** Driver for DFT */
    public void depthFirstTraversal() {
        this.marked = new boolean[numVertex];
        this.compSize = 0;
        int leader =0, largest =0;
        
        // Start from the highest label
        for (int i=fin.length-1; i>=0; i--) {
            if (!marked[fin[i]]) {
                currLeader = fin[i];
                // Add the finishing positions to SCC list
                comps.put(currLeader, new ArrayList<Integer>());
                // Increment leader count
                leader++;
                dFT(fin[i]);
                largest = Math.max(largest, compSize);
                this.compSize=0;
            }
        }
        System.out.println("SCC: " + leader);
        System.out.println("Largest SCC: " + largest);
    }
    
    /** Helper for DFT */
    public void dFT(int v){
        marked[v]=true;
        this.compSize++;
        comps.get(currLeader).add(v);
        set[v] = currLeader;

        for (Integer u:dGraph.get(v).getOutList())
            if (!marked[u]) dFT(u);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        
        /* Kosarju */

        // Read in directed edge list
        File dirEdges = new File("/home/nick/git/Course-Archive/comp272/pset10/Slashdot0902.txt");
        int numVertex = 0;

        // Find the max size
        Scanner initsc = new Scanner(dirEdges);
        while (initsc.hasNextInt()) 
            numVertex = Math.max(initsc.nextInt(), numVertex);
        initsc.close();

        DirectedGraph dg = new DirectedGraph(numVertex+1);
        
        // Add edges
        Scanner sc = new Scanner(dirEdges);
        while (sc.hasNextInt()) 
            dg.addEdge(sc.nextInt(), sc.nextInt());
        sc.close();

        System.out.println("Post order depth first traversal");
        dg.postOrderDepthFirstTraversal();
        System.out.println("...");
        System.out.println ("Regular depth first traversal");
        dg.depthFirstTraversal();

        /* Reduced graph */ 
        ReducedGraph hdg = new ReducedGraph(dg.compSize);
        Iterator<Integer> iterEdge = dg.comps.keySet().iterator();

        // Iterate through the SCC keyset and add vertices
        while (iterEdge.hasNext())
            hdg.addVertex(iterEdge.next());

        // For each edge in set of leaders, compare to make sure they are not both leaders of the same val
        for (Edge e : dg.edges) 
            if (dg.set[e.v1] != dg.set[e.v2])
                hdg.addEdge(dg.set[e.v1], dg.set[e.v2]);
        
        System.out.println("Vertices in reduced graph: " + hdg.hash.keySet().size());
        System.out.println("Edges in reduced graph: " + hdg.numEdges);
        
    }   
}

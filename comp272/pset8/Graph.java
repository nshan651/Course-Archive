package pset8;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph {
    int numVertex;
    int numEdge;
    ArrayList<ArrayList<Integer>> graph;
    
    public Graph () {
        numVertex =0;
        numEdge =0;
        graph = new ArrayList<>();
    }
    
    public Graph(int vertexCount) {
        numVertex=vertexCount;
        numEdge=0;
        graph = new ArrayList<>(numVertex);
        for (int i=0;i<numVertex;i++)
            graph.add(new ArrayList<>());
    }
    
    public ArrayList<Integer> getNeighbors(int u) {
        return graph.get(u);
    }
    
    public boolean edgePresent(int u, int v) {
        return (graph.get(u).contains(v));
    }
    
    public void addEdge(int u, int v) {
        // let's assume that the vertices are already created
        if (u>=0 && u<numVertex && v>=0 && v<numVertex) { 
            if (!edgePresent(u,v))
                graph.get(u).add(v);
            // Change from 'if' to 'else if'
            if (!edgePresent(v,u))
                graph.get(v).add(u);
            numEdge++;
        }
        else throw new IndexOutOfBoundsException();
    }

    /**
     * Depth-First Search of CC
     * @return The number of connected components
     */
    public int dfs() {
        long start, end;
        start = System.currentTimeMillis();
        int components = 0;
        boolean[] visited = new boolean[numVertex];
        for (int i =0; i < numVertex; i++) {
            if (!visited[i]) {
                dfsHelper(i, visited); 
                components++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("DFS -- " + components + " component(s) -- " + (end-start) + " ms");
        return components;
    }

    /**
     * Depth-First helper method
     * @param i The index number of the current vertice
     * @param visited Boolean array of all visited vertices
     * @return The size of the current connected component
     */
    private int dfsHelper(int i, boolean[] visited) {
        int nodes = 0;
        // Stack representation
        LinkedList<Integer> neighborQ = new LinkedList<>();
        visited[i] = true;
        neighborQ.add(i);

        while(neighborQ.size() != 0) {
            // Pop last element off the stack
            i = neighborQ.pop();
            Iterator<Integer> iter = this.getNeighbors(i).listIterator();
            while (iter.hasNext()) {
                // Next neighbor index
                int nIndex = iter.next();
                if (!visited[nIndex]) {
                    visited[nIndex] = true;
                    neighborQ.add(nIndex);
                    nodes++;
                }
            }
        }
        return nodes;
    }

    /**
     * Breadth-First Search of CC
     * @return The number of connected components
     */
    public int bfs() {
        long start, end;
        start = System.currentTimeMillis();
        int components = 0;
        boolean[] visited = new boolean[numVertex];
        for (int i =0; i < numVertex; i++) {
            if (!visited[i]) {
                bfsHelper(i, visited); 
                components++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("BFS -- " + components + " component(s) -- " + (end-start) + " ms");
        return components;
    }

    /**
     * Breadth-First helper method
     * @param i The index number of the current vertice
     * @param visited Boolean array of all visited vertices
     * @return size of the current component
     */
    private int bfsHelper(int i, boolean[] visited) {
        int nodes =1;
        // Queue representation
        LinkedList<Integer> neighborQ = new LinkedList<>();
        visited[i] = true;
        neighborQ.add(i);

        while(neighborQ.size() != 0) {
            // Pop last element off the stack
            i = neighborQ.poll();
            Iterator<Integer> iter = this.getNeighbors(i).listIterator();
            while (iter.hasNext()) {
                // Next neighbor index
                int nIndex = iter.next();
                if (!visited[nIndex]) {
                    visited[nIndex] = true;
                    neighborQ.add(nIndex);
                    nodes++;
                }
            }
        }
        return nodes;
    }
    
    /**
     * Identify the largest connected component of the graph
     * using freadth-first search
     * @return Largest CC size
     */
    public int largestComponent() {
        int max =0;
        boolean[] visited = new boolean[numVertex];
        for (int i =0; i < numVertex; i++) {
            if (!visited[i]) {
                // Size of current component
                int compSize = bfsHelper(i, visited); 
                // Check if current size is the largest
                if (compSize > max) 
                    max=compSize;
            }
        }
        System.out.println("Largest component: " + max);
        return max;
    }

    public int findTrees() {
        int trees = 0;
        boolean visited[] = new boolean[numVertex];
        for (int i =0; i < numVertex; i++) 
            if (!visited[i])
                if (!isCycle(i, visited, -1))
                    trees++;
        
        System.out.println("Number of trees: " + trees);
        return trees;
    }

    private boolean isCycle(int i, boolean visited[], int parent) {
        visited[i] = true;
        int adj;
        Iterator<Integer> iter = this.getNeighbors(i).listIterator();
        while (iter.hasNext()) {
            adj = iter.next();
            // Recursive condition, continues checking adj nodes. Current becomes parent.
            if (!visited[adj]) {
                if (isCycle(adj, visited, i))
                    return true;
            }
            else if (adj != parent)
                return true;
        }
        return false;
    }

    
    public static int inPlace(File file, String delim) throws FileNotFoundException {
        int max_size = findMax(file, delim);
        Integer[] finder = new Integer[max_size+1];
        ArrayList<MyLinkedList<Integer>> gr = new ArrayList<MyLinkedList<Integer>>(max_size+1);
        
        for (int i =0; i < finder.length; i++) {
            finder[i] = i;
            gr.add(new MyLinkedList<Integer>());
            gr.get(i).addFirst(i);
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] output = sc.nextLine().split("\t");
            int point1 = Integer.parseInt(output[0]);
            int point2 = Integer.parseInt(output[1]);
            
            if (finder[point1] != finder[point2]) {

                int q = (gr.get(point1).size() < gr.get(point2).size()) ? finder[point1] : finder[point2];
                int p = (gr.get(point1).size() > gr.get(point2).size()) ? finder[point1] : finder[point2];
                
                // Append q to p (smaller to larger)
                MyLinkedList<Integer> Q = gr.get(q);
                MyLinkedList<Integer> P = gr.get(p);
                P.appendList(Q);
                // Go to finder, modify smaller set label to larger
                int tmp = finder[q];
                
                for (int i =0; i < Q.size()-1; i++) {
                    finder[Q.get(i)] = tmp;
                }
            }
        }
        sc.close();
        
        List<Integer> ls = Arrays.asList(finder);
        Set<Integer> hash = new HashSet<Integer>(ls);
        
        System.out.println("CC: " + hash.size());
        return hash.size(); 
        
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
            int point1 = Integer.parseInt(output[0]);
            int point2 = Integer.parseInt(output[1]);
            // Check both points for new max
            max_size = Math.max(max_size, point1);
            max_size = Math.max(max_size, point2);
        }
        initsc.close();
        return max_size;
    }
    
    private static void initGraph(Graph gr, File file, String delim) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] output = sc.nextLine().split(delim);
            int point1 = Integer.parseInt(output[0]);
            int point2 = Integer.parseInt(output[1]);
            
            gr.addEdge(point1, point2);
        }
        sc.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        File enron_file = new File("./comp272/pset8/Email-Enron.txt");
        File graph_file = new File("./comp272/pset8/Graph.txt");
        
        int max_size = findMax(enron_file, "\t");
        Graph grp = new Graph(max_size+1);
        initGraph(grp, enron_file, "\t");
    
        grp.dfs();
        grp.bfs();
        grp.largestComponent();
        grp.findTrees();

        inPlace(enron_file, "\t");
    }
    
    
}

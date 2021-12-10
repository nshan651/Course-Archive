package pset10.src;
import java.util.*;

public class HashedDirectedGraph  {
    // HashMap where key k is the list of finishing positions and value is DNodeList
    HashMap<Integer, DirectedNodeList> hash;
    int numVertex, numEdges;
    
    public HashedDirectedGraph(){
        hash = new HashMap<>();
        numVertex = 0;
        numEdges = 0;
    }

    /**
     * in hash, key is leader k and value is DirectedNodeList
     * @param n
     */
    public HashedDirectedGraph(int n){
        hash = new HashMap<>(n);
        numVertex = n;
        numEdges = 0;
    }
    
    /**
     * Add a new vertex at leader position k
     * @param k
     */
    public void addVertex(int k) {
        if (!hash.containsKey(k)) 
            hash.put(k, new DirectedNodeList());
    }

    /**
     * Check if an edge is present between u and v
     * @param u
     * @param v
     * @return Edge present
     */
    public boolean isEdgePresent(int u, int v) {
        return getNeighborList(u).inList.contains(v) ||
            getNeighborList(u).outList.contains(v);
    }

    /**
    * Assume all vertices are created
    * directed edge u to v will cause outdegree of u to go up and indegree of v to go up.
    * @param u
    * @param v
    */
    public void addEdge(int u, int v) {
       
        if (hash.containsKey(u) && hash.containsKey(v)) { 
            if ((u != v) && !isEdgePresent(u,v)) {
                getNeighborList(u).addToOutList(v);
                getNeighborList(v).addToInList(u);
            }
            numEdges++;
        }
        else throw new IndexOutOfBoundsException();
    }
    
    /**
     * Get all neighbors of u
     * @param u
     * @return Neighbors DNL
     */
    public DirectedNodeList getNeighborList(int u) {
        return hash.get(u);
    }
    
}

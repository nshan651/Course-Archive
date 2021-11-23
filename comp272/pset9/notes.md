# Notes

* Here are the results after I ran the code developed according to Kruskal's algorithm using find3, union3 using PriorityQueue on the artist_edges.txt dataset.

* max Vertex label  50515
* vertex set size 50515
* number of Edges  819,306
* time taken 1.394 seconds
* number of edges considered for finding MST  818,976
* minimum weight of spanning tree = 6.4959317E7

Notice that for this dataset, the code pretty much runs the table on the entire list of edges (which is the number of edges considered) - just about 1,500 shy of the max number.  The actual number of edges on the spanning tree is 50514, one shy of the vertex set size.  In my code, I store the (winning) edges of the spanning tree in an arraylist.  I have not fully tested the code.  If you see a different result, let me know.  thanks
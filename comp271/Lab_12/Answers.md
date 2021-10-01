* The resulting word frequencies are the same for all three data structures. However, the time complexities were different between the three
implementations. MyHasMap was the slowest at around 800 ms, then came the tree map at 624 ms, and finally the Collections HashMap, which came in 
at 452 ms. The Hash Map has an O(1) time complexity for inserting, whereas the Tree Map has an O(log(n)) runtime. The Tree Map is slower because it 
sorts its elements. I suspect that the reason that our hash map implementation is slower than the Java Collections one is that there are further optimizations
done.

* Math.floorMod gives us a positive remainder, as opposed to %, which may give a negative 
remainder. We need to use this in case our hash code is negative.

* The size method is directly proportional to the capacity, so it has O(n) running time. One way to improve this may be to iterate over the actual list
elements rather than the initial capacity.

* The Hash Map implementation has the advantage of having constant time adding and removing operations, but it does have some downsides compared to our Node
class. One of the main downsides of a Hash Table is that it can be difficult to maintain order, and items that you retrieve
 may not be in the order that they were inserted in. This is not an issue for the word frequency checker that we are using because we have a way to easily 
 sort by the word values, but it is something to consider.
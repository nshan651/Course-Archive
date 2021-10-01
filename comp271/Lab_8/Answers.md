# Reflection

* We need an explicit constructor so that we can set the capacity of our queue. Because the queue uses an array 
rather than a mutable ArrayList, the size must be pre-determined during initialization. 

* The Offer method will attempt to add an item to the end of the queue, but it will return false and not add the item because the queue is full.

* Poll attempts to return the front element of the queue. If the queue is empty, then it will return null.

* Offer, peak, poll, isEmpty, and size are all constant O(1) runtime operations, because there are no loops and each operation
is performed once. The asList method is the only O(n) runtime method, because it requires us to loop through the size of the
array while adding each element to the queue. The circular queue has an O(n) space complexity, where n is the number of elements in the queue.
The circular queue has the advantage of being able to utilize all available spaces, 
which is something that a linear queue can not do after it is full, even if there are empty spaces after removing elements.
# Reflection 

* The StringBuilder class is used instead of concatenating a String because it is easier to read
  and may be slightly faster StringBuilder creates a mutable sequence of characters, but unlike 
  its counterpart, StringBuffer, it does not ensure thread safety. 
  
* The multi-line creation of each individual node seems much easier to read. It also seems easier to refer to each node 
  as a discrete structure by giving each of them names.
  
* None of these methods are tail-recursive as a requirement of being tail-recursive is to be linearly recursive, which none of these methods are.
   
* Yes, it is possible to implement a stack-based approach for each of these methods. Recursively, each of these methods 
  take advantage of the call stack, so it will be possible to create an explicit stack.
  
* If we took a stack-based approach, we could iterate through the stack by creating a hasNext function that repeatedly checks if the stack is empty
  For the next() method, we can pop the most recent node off the stack, then we save that result and shift the advance the current node
  to the next position (if we are printing in order, we shift the node right by one).
  
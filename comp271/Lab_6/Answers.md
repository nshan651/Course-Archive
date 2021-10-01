# Reflection

* The non-recursive solve method validates the arguments and handles input,
  while the recursive method contains the actual algorithm that performs the 
  recursive search for a way out. It is important to separate these methods in order to adhere to the "DRY" principle,
  because the very same solve() method can be used to handle the inputs of our stack-based maze solver.
  
* The order that we check each direction does not make a difference in terms of being able to find an exit. The algorithm
  is effectively doing a blind search in each direction, and it prioritizes based on the order of the recursive calls. Sometimes
  it will get lucky and find a way out immediately (as is the case with maze1), and other times it will take an "inefficient" 
  route. However, if at least one exit exists, our algorithm will find a solution.
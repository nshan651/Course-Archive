# Reflection

* The methods in Maze are instance methods because the state of the maze data carries over and is shared between the methods. This is useful because the class variable mazeData will
be saved for each Maze object, which will allow us more flexibility, such as instantiating multiple mazes at a time.

* By using PrintStream as a parameter of print, we can choose the destination of where we want to print. In the case
of our main method, we are printing to System.out.

* Maze.get() is used to get the character at a specified row and column. This method is mainly for testing purposes.
In test A, the get method is used to check that the symbols at different locations in our test maze are set correctly.

* (E.C. LFIO vs FIFO): After experimenting with both a LIFO Stack and a traditional FIFO Queue, I found that the only major difference was the 
direction that each favored. They both solved the maze with minimal changes between the two, but the Stack seemed to prioritize going left,
while the FIFO Queue prioritized traveling left. 
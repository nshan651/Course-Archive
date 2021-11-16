# Email

* We have now two datasets for the assignment.  One is Graph.txt (40+Mbytes), comma separated and the other is Email-Enron.txt (4 MB), space separated.   

* On Email-Enron.txt data file, you should get 1065 connected components, 0 trees, and on Graph.txt you should get 2496 components with 2475 trees.  

* Here are the timing results:

## Email-Enron.txt:
    - CCs: 1065
    - Trees: 0
    - Max CC: 33,696

    - DFS: 0.045 seconds

    - BFS: 0.002 seconds

    - find/merge: 0.03 seconds

## Graph.txt
    - CC: 2496
    - Trees: 2475
    - Max CC: 997,485

    - DFS: 0.608 seconds

    - BFS: .004 seconds

    - find/merge: 0.536 seconds

## Final Comments

* Making dfs the worst performer among the two data sets (possibly due to recursion overhead).   

* Also, if dfs shows stack overflow error, use command line Java to run the code by using the -Xss stack size option as in 
```java -Xss50m [your java class file]```
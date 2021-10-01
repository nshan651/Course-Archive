# Answers

* The variable E represents a generic data type, which allows the Node algorithm to work with a variety of different types

* The "this" keyword is used in the second constructor to create the root node, which will have data
but does not have a next pointer. The "this" keyword in the second constructor refers to the first constructor,
and it passes in data for data and null for the next pointer.

* The purpose of the toString method is to print the hash code of each string along with the data of that string.
The hash code gives each Node object a unique identifier, which makes it easier to test. It can also be used to 
determine if we have encountered that node before if we are cycling through the list.

* I think that the multi-line statement makes the path from one node to the next and their connections more clear
 Defining each node of the linked list also has the advantage of conveying the fact that the list is a collection of 
 discrete nodes that may or may not be  contiguous in memory. Perhaps the largest drawback of the single-line approach 
 is that it cannot be set up in a cyclic manner, because only the first node is given a name.
 
* Using the printNode method will result in an infinite loop because the current node will never equal null.
 
* I would describe most non-cyclic structures created by the Node class as linear, with one node connected to another
 in succession until they reach a stopping point. With some minor changes however, it would also be possible to create
 a tree-like structure where a given node has multiple connections and there are several root nodes. 


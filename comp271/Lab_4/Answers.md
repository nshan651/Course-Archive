# Reflection Questions

* The type of Map does make a difference in performance. HashMap
is slightly faster because it has a constant time complexity O(1)
compared to the TreeMap time complexity of O(log(n)).

* If countWords is invoked multiple times using different String iterators
(assuming we use the same Map and WordCounter object), the iterator counts of each word
will be added together. For example, I created two Scanner objects that read from the same
text file (les mis), and I invoked countWords() twice using each of the scanners. The output
was twice the regular value. The most common word "the", which normally occurred 33,278
times now showed as 66,556.

* It is essential to use the Iterator Interface rather than the Scanner class for testing
 because it allows us to pass an ArrayList in to the setup method of our test. This allows our
 testing to be more flexible. Rather than reading inputs from the console or an external file each
 time we test our program, we can simply pass in an ArrayList of values to be tested. 
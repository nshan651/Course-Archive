# Reflection

 * We store the computed values in a data structure (RainfallStats) so that it is easily accessible by 
   external programs, such as our unit test. 
   
 * The user does not need to specify the item count because they are appending a mutable ArrayList. 
   The size of the list will increase to accommodate the number of inputs.
   
 * An ArrayList is better suited for this program because it is mutable and can be appended. As 
   Data is read from the console, an ArrayList is needed for storing the user input because it can be 
   appended.
   
 * The RainfallStats class acts as a data structure that stores the rainfall statistics. It
   is useful to store our data this way because it is easy to check the the values of the object instance
   once we have performed our calculations. This is what we are doing with our test class; we are performing 
   calculations and returning it as an object instance, then we are testing the values of that instance.
   
 * It is better to use List because it is a better abstraction than ArrayList. ArrayList inherits from List,
   so by using List our implementation is more abstract and thus more resilient to changes. Using List will make
   it easier to switch to different implementations in the future. For example, if we wanted to pass in a Linked 
   List in the driver program, we would not need to change our calculation class or structure class, because
   LinkedList is also inherited from List.
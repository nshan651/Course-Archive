# Response

* The two find team position searches and the first findTeamMinFunding will have an O(n) runtime because all three are a 
single, linear loop. The final method, findTeamMinFundingFast, will have an O(log n) runtime.

* If the array is not sorted, binary search may discard a part of the list that the targeted value is located in. We can
only divide the array/list in half if we are certain that the desired value is not part of it.

* The purpose of the constructor argument validity checks in the TestTeam method is to ensure that an improper team cannot be 
created. Creating a team that has a null name or a negative minimum balance would cause many of our other methods to break.

* Using the final keyword tells the programmer that the variable will not change. This is useful for making code more explicit
so that a variable is not mistakenly modified when it should not be.

* Optional provides an alternative to using null or a sentinel value when an object is not returned. Instead of returning null,
 the Optional container will be represented as having an absent value. Using Optional will achieved basically the same thing, but makes
the code easier to interpret.
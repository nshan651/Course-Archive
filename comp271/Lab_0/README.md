# Overview

A program that reads a list of numbers from the standard input (console) 
representing daily rainfall amounts as entered by a user. Produce the average 
of the non-negative values in the list up to end of file. There may be negative numbers in the list

# Nonfunctional Requirements

- Performance: For each additional input item, the program should produce a small, finite number of steps.
- Scalability: The program should handle arbitrarily large input sequences.
- Testability: Give some initial thought to the way your program could support automatic testing (without, say, the user entering any data or reading any data from an external file)

# Reflection

My implementation ended up being very similar to my pseudocode solution that 
I came up with during the design activity. The main difference, aside from correct java syntax,
was that I was able to allow the input to have multiple doubles on one line. Another difference
was that I did not print the result when there was an empty input or when there were no valid 
inputs. \
\
One way that I could allow for automatic testing is by creating a separate method that inputs a list
of values to be tested into my rainfall program. This would require me to make several modifications. First, I would 
need to add a parameter to my Lab_0 method that accepts a list of values (a double array). I would also
need to modify my program so that it sums the list of (valid) test values, and uses the sum along with the length of the array
in the final calculation. Unfortunately, it would be difficult to automatically test keyboard scanner inputs with these proposed
 changes (like checking testing multiple inputs on the same line). However, I would still be able to test whether my averaging formula is
 correct.\
\
My test inputs should include:
 * positive floating points and integers
 * negative floating points and integers 
 * characters (letters and symbols)
 * character-number mixes (i.e. "A5B5")
 
 

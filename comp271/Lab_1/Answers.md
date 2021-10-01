# Design Space Project

1. Create a suitable Java project and add Lab1.java as a source file.

2. Run the program and record the output; for example, cut and paste it into a new file output.txt in your project.

3. Classify each variable (including all method arguments) and method according to the possible characteristics given above (under design space). (Consider using a table/spreadsheet for this task.)

4. In addition, answer the following questions in a new file Answers.md in your project.
   * What is the difference between inst1, inst2, and inst3?
        
        inst1 and inst2 are initializing new Lab1() objects, whereas inst3 is pointing
        to the inst2 object (object reference). inst2 and inst3 both reference the same object, so any instance variables changed
        for one will be changed for the other.
        
   * Why is main able to access var3 and var4 even though those are private?
   
        main is able to access private variables var3 and var4 because the main method is located in the same namespace as 
        the class variables. That is, both the main method and variables are located within class Lab1.
        
   * Why is main able to access var1 and var2 without going through an instance?
   
        The main method is able to access var1 and var2 because they are both static variables, both of which are class variables and not 
        object instance variables. They are initialized only once at the start of execution.
        
   * What is happening when we modify inst2.var2 and then print inst1.var2?
   
        The variable var2 is static, which means that it is a class variable and not an object instance variable. var2 is shared between all object instances,
        so changing it for one object instance will change it for the rest.
        
   *    What is happening on line 12 (in the second Lab1 constructor)?
   
        The second Lab1 constructor is taking parameter var4 (not the same as the private var4 declared at the beginning), and it is setting this value being passed in equal to
        the object instance var4 that was declared near the beginning.
        
   * What is happening in method3?
   
        method3 is taking parameter var4 (again, not the same as the object variable var4 declared at the beginning). The method is then immediately setting the variable var4 passed
        in as a parameter to 55. Finally, it is adding the var4 parameter variable that has just been set to 55 to the object variable var4.
        
        When this method is used in the main method on line 45 ("System.out.println(inst2.method3(99);"), "99" is being passed in as an argument. The method is then immediately
        setting this value to 55, and adding it to the object instance variable var4 (which at this point is equal to 45). The final output that is returned is "100".
        
   * Why can't we print the result of method4?
        
        We cannot print the result of method4 because it does not return a value. It is a method of type void.
        
5.  Export your project as a zip file and submit it as an attachment in Sakai
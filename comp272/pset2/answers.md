# Pset-1 Answers

1. O(n) time 

    ```
    /* Problem 1 */
    public ExtLinkedList<E> secondHalfList() {
        // Initialize second half of the list
        ExtLinkedList<E> secondHalf = new ExtLinkedList<>();
        // Return empty list if empty; first item if only one exists
        if (this.size()==0) return secondHalf;
        if (this.size()==1) {
            secondHalf.add(this.get(0));
            return secondHalf;
        } 
        // Set middle based on whether size is even or odd
        // if size odd, truncate the middle
        int middle = (this.size()%2==0) ? this.size()/2 : (this.size()/2)+1;
        
        // Loop from middle to second half
        for (int i = middle; i < this.size(); i++) 
            secondHalf.add(this.get(i));
        
        return secondHalf;
    }
    ```

2. Both odd and even lists are O(n) time

    ```
    /* Problem 2 */
    public ExtLinkedList<E> oddList() {
        ExtLinkedList<E> oddList = new ExtLinkedList<>();
        // Return an empty list if size is 0 or 1
        // 0 meaning list is empty, 1 because position 0 is even
        if (this.size()==0 || this.size()==1) return oddList;

        // Iterate through odd index values
        for (int i = 1; i < this.size(); i+=2) 
            oddList.add(this.get(i));
        
        return oddList;
    }

    public ExtLinkedList<E> evenList() {
        ExtLinkedList<E> evenList = new ExtLinkedList<>();
        // Return an empty list if size is 0 or 1
        // 0 meaning list is empty, 1 because position 0 is even
        if (this.size()==0) return evenList;

        // Iterate through odd index values
        for (int i = 0; i < this.size(); i+=2) 
            evenList.add(this.get(i));
        
        return evenList;
    }
    ```

3. <br >

    ```
    /* Question 3*/
    public static String replaceChar(String p, int k, char c) {
        // Throw an exception if argument is invalid
        if (k < 0 || k > p.length() || p.length()==0) 
            throw new IllegalArgumentException("k value out of range or p is empty");
        // Concatenate substring up to k, char c, and substring after k+1
        return p.substring(0, k) + c + p.substring(k+1, p.length());
    }
    ```

4. <br >
    a. Estimate the time complexity for the following: 

        * O(n) time <br >
        ```for (int i=0; i<n;i++)```

        * O(log(n)) time <br >
        ```for (int j=0; j*j<n; j++)```

        * O(1) time <br >
        ``` System.out.println(i+j+k); ```


    b. Obtain the following run-time complexities

        * The largest term is n^3, so the time complexity is O(n^3) <br >
        F(n)=(10+2n)(n^2+nlog^(3)n) <br >
        =2n^3 + 10n^2 + 2n^(2)log^(3)n + 10log^(3)n


        * The runtime is O(n^(0.5)), because that is the most significant function. log^10(n) is log(log(log(...x10))) which is a very slow growing function. Thus, O(n^(0.5)) grows the fastest

5. <br >

    ```
    /* Question 5 */
    public static void rowSums(int[][] arr) {
        for (int i =0; i < arr.length; i++) {
            int sum =0;
            for (int j =0; j < arr[i].length-1; j++) 
                sum+=arr[i][j];
            if (i < arr.length-1)
                System.out.print(sum + ",");
            else
                System.out.print(sum);
        }
    }
    public static void columnMins(int[][] arr) {
        for (int j =0; j < arr[0].length; j++) {
            int min =arr[0][j];
            for (int i =0; i < arr.length; i++) {
                if (arr[i][j] < min)
                    min = arr[i][j];
            }
            if (j < arr[0].length-1)
                System.out.print(min + ",");
            else
                System.out.print(min);
        }
    }
    ```

6. <br >

    ```
    /* Question 6 */
    public static void prefixSums(LinkedList<Integer> n) {
        
        int curr =n.get(0), prev =n.get(0);
        // Base
        System.out.print(curr + ",");
        // Loop forward through the list
        for (int i =1; i < n.size(); i++) {
            curr = n.get(i);
            prev+=curr;
            if (i < n.size()-1)
                System.out.print(prev + ",");
            else
                System.out.print(prev); 
                // Print this at the end so 
                // there is no trailing comma
        }
    }
    ```
7. <br >

    ```
    /* Question 7 */
    public static void reversePrefix(LinkedList<Integer> n) {
        // Same thing as question 6 but in reverse order
        // starting at the end of the list
        int curr =n.get(n.size()-1), next =n.get(n.size()-1);
        System.out.print(curr + ",");
        for (int i =n.size()-2; i >=0; i--) {
            curr = n.get(i);
            next+=curr;
            if (i > 0)
                System.out.print(next + ",");
            else
                System.out.print(next);
        }
    }
    ```
8. <br >

    ```
    /* Question 8 */
    public static LinkedList<String> combine(LinkedList<String> a, LinkedList<String> b) {
        LinkedList<String> combine = new LinkedList<String>();
        
        int i =0, j =0;
        while (i < a.size() && j < b.size()) {
            // Lexicographic comparison
            int compare = a.get(i).compareTo(b.get(j));
            if (compare < 0) {
                combine.add(a.get(i));
                i++;
            }
            else {
                combine.add(b.get(j));
                j++;
            }   
        }
        // Add remaining strings 
        while (i < a.size()) {
            combine.add(a.get(i));
            i++;
        }
        while (j < b.size()) {
            combine.add(b.get(j));
            j++;
        }   
        return combine;
    }
    ```

9. <br >

    ```
    /* Question 9 */
    public static void uniquePairs(int[] arr, int k) {
        for (int i =0; i < arr.length; i++) {
            for (int j =i; j < arr.length; j++) {
                if(Math.abs(arr[i]-arr[j])==k)
                    System.out.print("("+arr[i]+","+arr[j]+")");
            }
        }
    }
    ```
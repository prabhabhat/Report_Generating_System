Generate reports based on the following queries:
1. For each customer and product, compute (1) the customer's average sale of this product, (2) the average sale of the product for the other customers and (3) the customer’s average sale of the other products.
2. For customer and product, show the average sales before and after each quarter (e.g., for Q2, show average sales of Q1 and Q3.  For “before” Q1 and “after” Q4, display <NULL>).  The “YEAR” attribute is not considered for this query – for example, both Q1 of 1997 and Q1 of 1998 are considered Q1 regardless of the year.
3. For customer and product, count for each quarter, how many sales of the previous and how many sales of the following quarter had quantities between that quarter’s average sale and minimum sale.  Again for this query, the “YEAR” attribute is not considered.

-----------------------------------------
Instructions to run the program:
-------------------------------
Step 1: Copy the Report1Test.java file to your local system.
Step 2: Edit the username, password and url of the local system data base and save it.
Step 3: if you are running in eclipse then place the programs under the required project and click on Run As Java Application.
if you are running it using command prompt compile the program using "javac Report1Test.java"
then run it using "java Report1Test"


Reason for using an ArrayList as data structure in the program:
--------------------------------------------------------------
1) We can define ArrayList as re-sizable array. Size of the ArrayList is not fixed. 
ArrayList size we can increase or decrease dynamically based on the program requirement.
2) We can insert or delete elements from particular position. this will help us while inserting or replacing the min or max quantities from a particular
position in an arraylist.
3) In ArrayList class we have many methods to manipulate the stored elements like add(), remove(), addAll(), removeAll(), retainAll(), indexOf(), iterator() etc.
we can make use these above methods to manipulate the elements in an ArrayList.
4) The manipulation operations like insertion and removal of elements will not affect the performance of the program.
5) We can traverse an ArrayList in both the directions, forward and backward direction using ListIterator. 
6) ArrayList can hold multiple null elements and duplicate elements as well.

With all these above mentioned advantages of ArrayList, it will be easy and efficient to use an ArrayList for storing, removing and replacing the elements to and from the list. By making use of all the available methods in an arraylist class we can achieve our objective in this program.


Logic of the program:
-------------------------------------------
 * Step 1: Add the first entry of the result set into an ArrayList by considering the current quantity as minimum, maximum and average quantity. 
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then compare the quantity of current iteration and quantity of the ArrayList. 
   If the current quantity is greater than maximum element then swap it with current quantity and replace all 
   the corresponding elements in an ArrayList with the current iteration values. else continue.    
 * Step 4: Iterate through the ArrayList and Check for all the customer and their all products  
 * Step 5: subtract sum and occurrences of step 2 with the step 4 then swap the sum and occurrences of ArrayList one. 
 * Step 6: Iterate through the ArrayList and Check for all the products and their all customers. 
 * Step 7: subtract sum and occurrences of step 2 with the step 6 then swap the sum and occurrences of ArrayList one.
 * Step 8: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and  right indenting
 * the integer values and displaying the date in expected format. 
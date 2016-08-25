Generate reports based on the following queries:
1. For each customer and product, compute (1) the customer's average sale of this product, (2) the average sale of the product for the other customers and (3) the customer’s average sale of the other products.
2. For customer and product, show the average sales before and after each quarter (e.g., for Q2, show average sales of Q1 and Q3.  For “before” Q1 and “after” Q4, display <NULL>).  The “YEAR” attribute is not considered for this query – for example, both Q1 of 1997 and Q1 of 1998 are considered Q1 regardless of the year.
3. For customer and product, count for each quarter, how many sales of the previous and how many sales of the following quarter had quantities between that quarter’s average sale and minimum sale.  Again for this query, the “YEAR” attribute is not considered.
-----------------------------------------
Instructions to run the program:
-------------------------------
Step 1: Copy the Report2Test.java file to your local system.
Step 2: Edit the username, password and url of the local system data base and save it.
Step 3: if you are running in eclipse then place the program under required project and click on Run As Java Application.
if you aere running it using command prompt compile the program using "javac Report2Test.java"
then run it using "java Report2Test"


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
----------------------------------------
 * Step 1: Add the first entry of the result set into an ArrayList by considering the sum, occurrence and quarter for all the 4 quaeters. 
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then compare the month of current iteration. 
   If the current month is between 1 to 3 then Q1, between 4 to 6 then Q2, between 7 to 9 then Q3 and between 10 to 12 then Q4. 
   replace all the corresponding elements in an ArrayList with the current iteration values. else continue.    
 * Step 4: If the current iteration customer and product combination does not match with the already existing ArrayList elements then insert 
   the elements into an ArrayList.
 * Step 5: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and right indenting
 * the integer values and displaying the date in expected format. 
Generate 2 separate reports based on the following queries (one report for query #1 and
another for query #2):
1. For each combination of customer and product, compute the maximum and minimumsales quantities along with the corresponding dates (i.e., dates of those maximum and minimum sales quantities) and the state in which the sale transaction took place.  If there are >1 occurrences of the max or min, choose one – do not display all.
For the same combination of product and customer, compute the average sales quantity.
2. For each combination of customer and product, output the maximum sales quantities for NY and NJ and minimum sales quantities for CT in 3 separate columns.  Like the first report, display the corresponding dates (i.e., dates of those maximum and minimum sales quantities).  Furthermore, for NY and NJ, include only the sales that occurred between 2000 and 2005; for CT, include all sales.
------------------------------------
Instructions to run the program:
-------------------------------
Step 1: Copy the myQuery2.java fiel to your local system.
Step 2: Edit the username, password and url of the local system data base and save it.
Step 3: if you are running in eclipse then place the program under required project and click on Run As Java Application.
if you aere running it using command prompt compile the program using "javac myQuery2.java"
then run it using "java myQuery2"


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


Pseudo code:
--------------

while (result set having next element){
     while(first iteration){
      if (check for the state in which sales has occurred){
	    if (check for the year in which sales has occured){
	     add the entries to an ArrayList	
		}
	  }
	 else{
	  add a default values to an ArrayList
	  }
      increase the itearstion count	 
    }
	for (every entry in an ArrayList){
	  if (check for customer, product combination){
         if(check for the state NY){
		   if(check for the year between 2000 and 2005){
	         if (check if the current quantity is greater than the quantity in an ArrayList){
	      replce the quantity and it's corresponding date and state in an ArrayList	     
			}		   
		  }	
		}
		if(check for the state NJ){
		   if(check for the year between 2000 and 2005){
	         if (check if the current quantity is greater than the quantity in an ArrayList){
	      replce the quantity and it's corresponding date and state in an ArrayList	     
			}		   
		  }	
		}
		if(check for the state CT){
		  if (check if the current quantity is Less than the quantity in an ArrayList){
	      replce the quantity and it's corresponding date and state in an ArrayList
		  }
		}
		come out of for loop if any of the maximum or minimum condition satisfies.
	  }
	}
	
	if (if the current entry does not match with any of the customer and product combination in an ArrayList){
	   if(check for the state NY){
		   if(check for the year between 2000 and 2005){
	          insert current elements into an ArrayList
	       }
        }
		if(check for the state NJ){
		   if(check for the year between 2000 and 2005){
	          insert current elements into an ArrayList
	       }
        }
		if(check for the state CT){
		      insert current elements into an ArrayList
	       }
        }

	
 for (every 14 elements in an ArrayList){
   if (check for the default values for quantity){
     then print enpty indicates element is not present
	 }
	 else{
	   then print all the required elements in console as expected format.
	 }  
  }  
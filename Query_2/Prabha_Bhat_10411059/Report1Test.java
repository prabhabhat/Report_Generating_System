import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.text.DecimalFormat;


/* For each combination of customer and product, this program calculates the
 * 1. The customers average sale of this product.
 * 2. The average sale of the product for the other customers.
 * 3. The customers average sale of the other products.
 */

/* Logic of the program:
 * Step 1: Add the first entry of the result set into an ArrayList by considering the current quantity as minimum, maximum and average quantity. 
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then compare the quantity of current iteration and quantity of the ArrayList. 
 * If the current quantity is greater than maximum element then swap it with current quantity and replace all 
 * the corresponding elements in an ArrayList with the current iteration values. else continue.    
 * Step 4: Iterate through the ArrayList and Check for all the customer and their all products  
 * Step 5: subtract sum and occurrences of step 2 with the step 4 then swap the sum and occurrences of ArrayList one. 
 * Step 6: Iterate through the ArrayList and Check for all the products and their all customers. 
 * Step 7: subtract sum and occurrences of step 2 with the step 6 then swap the sum and occurrences of ArrayList one.
 * Step 8: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and right indenting
 * the integer values and displaying the date in expected format.    
 */

public class Report1Test {

		public static void main(String[] args)
		{
			String usr ="postgres";
			String pwd ="believegod@1";
			String url ="jdbc:postgresql://localhost:5432/postgres";
			ArrayList<String> listOne = new ArrayList<String>(); //declaring the ArrayList to store elements
			ArrayList<String> listTwo = new ArrayList<String>();
			ArrayList<String> listThree = new ArrayList<String>();
			System.out.println("For each combination of customer and product, this program calculates the"+
					"\n1. The customers average sale of the particular product."+
					"\n2. The average sale of the product for the other customers."+
					"\n3. The customers average sale of the other products.\n"+
					"The average decimal values are rounded to their nearest whole numbers");
			int count = 1,count1 = 1,count2 = 1,sum1=0,sum2=0,sum3=0,occurrence1=0,occurrence2=0,occurrence3=0;
			double avg1=0,avg2=0,avg3=0;
			try
			{
				Class.forName("org.postgresql.Driver");
				System.out.println("Success loading Driver!");
			}

			catch(Exception e)
			{
				System.out.println("Fail loading Driver!");
				e.printStackTrace();
			}

			try
			{
				Connection conn = DriverManager.getConnection(url, usr, pwd);
				System.out.println("Success connecting server!");

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Sales");

				while (rs.next())
				{
					 while(count == 1)
						{
							listOne.add(rs.getString("cust"));   // add the elements into an ArrayList
							listOne.add(rs.getString("prod"));
							listOne.add(String.valueOf(rs.getInt("quant")));
							listOne.add("0");
							listOne.add("0");
							listOne.add("0");
							listOne.add("0");
							listOne.add("0");
							listOne.add("0");
							count++;
						 }

					int i;
					title:	
					for(i = 0; i < listOne.size(); i=i+9)   // iterate through an ArrayList using size() method in for loop. 
					{
					  // check for the customer and product combination
					  if ((rs.getString("cust").equals(listOne.get(i))) && ((rs.getString("prod")).equals(listOne.get(i+1))))
						{
						    occurrence1=Integer.parseInt(listOne.get(i+4))+1;    //Calculate the occurrence of the customer, product combination 
							sum1=Integer.parseInt(listOne.get(i+3)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							listOne.set(i,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(i+1,rs.getString("prod"));
							listOne.set(i+3, String.valueOf(sum1));                        // Add the sum into the ArrayList
							listOne.set(i+4, String.valueOf(occurrence1));                 // Add the occurrence into the ArrayList
					        break title;
						}
					}
					
					if(i == listOne.size())   // check if the values of i is equal to size of the ArrayList
					{	
						listOne.add(rs.getString("cust"));    // add the elements into the ArrayList.
						listOne.add(rs.getString("prod"));
						listOne.add(String.valueOf(rs.getInt("quant")));
						listOne.add(String.valueOf(rs.getInt("quant")));
						listOne.add("1");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
					}
					
					while(count1 == 1)
					{
						listTwo.add(rs.getString("cust"));   // add the elements into an ArrayList
						listTwo.add(rs.getString("prod"));
						listTwo.add(String.valueOf(rs.getInt("quant")));
						listTwo.add("0");
						listTwo.add("0");
						count1++;
					 }

					int j;
					titlej:	
					for(j = 0; j < listTwo.size(); j=j+5)   // iterate through an ArrayList using size() method in for loop. 
					{
					if (rs.getString("cust").equals(listTwo.get(j)))
							{
								occurrence2=Integer.parseInt(listTwo.get(j+4))+1;    //Calculate the occurrence of the customer, product combination 
								sum2=Integer.parseInt(listTwo.get(j+3)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
								listTwo.set(j,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
								listTwo.set(j+1,rs.getString("prod"));
								listTwo.set(j+3, String.valueOf(sum2));                        // Add the sum into the ArrayList
								listTwo.set(j+4, String.valueOf(occurrence2));                 // Add the occurrence into the ArrayList
						        break titlej;
							}
					 }
					
					if(j == listTwo.size())   // check if the values of i is equal to size of the ArrayList
					{	
						listTwo.add(rs.getString("cust"));    // add the elements into the ArrayList.
						listTwo.add(rs.getString("prod"));
						listTwo.add(String.valueOf(rs.getInt("quant")));
						listTwo.add(String.valueOf(rs.getInt("quant")));
						listTwo.add("1");
					}
					
					
					while(count2 == 1)
					{
						listThree.add(rs.getString("cust"));   // add the elements into an ArrayList
						listThree.add(rs.getString("prod"));
						listThree.add(String.valueOf(rs.getInt("quant")));
						listThree.add("0");
						listThree.add("0");
						count2++;
					 }

					int l;
					titlel:	
					for(l = 0; l < listThree.size(); l=l+5)   // iterate through an ArrayList using size() method in for loop. 
					{
					if (rs.getString("prod").equals(listThree.get(l+1)))
							{
								occurrence3=Integer.parseInt(listThree.get(l+4))+1;    //Calculate the occurrence of the customer, product combination 
								sum3=Integer.parseInt(listThree.get(l+3)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
								listThree.set(l,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
								listThree.set(l+1,rs.getString("prod"));
								listThree.set(l+3, String.valueOf(sum3));                        // Add the sum into the ArrayList
								listThree.set(l+4, String.valueOf(occurrence3));                 // Add the occurrence into the ArrayList
						        break titlel;
							}
					 }
					
					if(l == listThree.size())   // check if the values of i is equal to size of the ArrayList
					{	
						listThree.add(rs.getString("cust"));    // add the elements into the ArrayList.
						listThree.add(rs.getString("prod"));
						listThree.add(String.valueOf(rs.getInt("quant")));
						listThree.add(String.valueOf(rs.getInt("quant")));
						listThree.add("1");
					}
				}
				
				int m,n;
				int custsum=0,custcount=0;
				for (m=0;m<listOne.size();m=m+9)
				{
					for (n=0;n<listTwo.size();n=n+5)
					{
						if (listOne.get(m).equals(listTwo.get(n)))
						{
							custsum=Integer.parseInt(listTwo.get(n+3)) - Integer.parseInt(listOne.get(m+3));  // Subtract custsum with total sum
							custcount=Integer.parseInt(listTwo.get(n+4)) - Integer.parseInt(listOne.get(m+4));// subtract custcount with total count
							listOne.set(m+7, String.valueOf(custsum));
							listOne.set(m+8, String.valueOf(custcount));
						}
					}
				}
				
				int p,q;
				int prodsum=0,prodcount=0;
				for (p=0;p<listOne.size();p=p+9)
				{
					for (q=0;q<listThree.size();q=q+5)
					{
						if (listOne.get(p+1).equals(listThree.get(q+1)))
						{
							prodsum=Integer.parseInt(listThree.get(q+3)) - Integer.parseInt(listOne.get(p+3)); //Subtract prodsum with total sum
							prodcount=Integer.parseInt(listThree.get(q+4)) - Integer.parseInt(listOne.get(p+4)); // Subtract prodcount with total count
							listOne.set(p+5, String.valueOf(prodsum));
							listOne.set(p+6, String.valueOf(prodcount));
						}
					}
				}
				
				//System.out.println(listOne);
				System.out.print("\n");
				// Printing all the required elements from an ArrayList into the console as showed below:
				System.out.println("CUSTOMER       PRODUCT     CUST_AVG    OTHER_CUST_AVG    OTHER_PROD_AVG");  // Column heading
				System.out.println("========       =======     ========    ==============    ==============");
				for (int k=0;k<listOne.size()-1;k=k+9)  // loop to iterate through the ArraList.
					{
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 avg1=Math.round((double)Integer.parseInt(listOne.get(k+3))/(double)Integer.parseInt(listOne.get(k+4)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt1=(int)avg1;
						 System.out.print(String.format("%8s",avgt1)+ "    ");
						 avg2=Math.round((double)Integer.parseInt(listOne.get(k+5))/(double)Integer.parseInt(listOne.get(k+6)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt2=(int)avg2;
						 System.out.print(String.format("%14s",avgt2)+ "    ");
						 avg3=Math.round((double)Integer.parseInt(listOne.get(k+7))/(double)Integer.parseInt(listOne.get(k+8)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt3=(int)avg3;
						 System.out.print(String.format("%14s",avgt3)+ "    ");
						 System.out.print("\n");
				     }  
						
			}

			catch(SQLException e)
			{
				System.out.println("Connection URL or username or password errors!");
				e.printStackTrace();
			}

		}

	}

	


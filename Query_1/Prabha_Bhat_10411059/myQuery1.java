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
 * maximum and minimum sales quantities along with the corresponding dates,
 * and the state in which the sales transaction took place, and displays it.
 * For the same combination customer and product, it will calculate the average sales quantity
 * and displays it as well.
 */

/* Logic of the program:
 * Step 1: Add the first entry of the result set into an ArrayList by considering the current quantity as minimum, maximum and average quantity. 
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then compare the quantity of current iteration and quantity of the ArrayList. 
 * If the current quantity is greater than maximum element then swap it with current quantity and replace all 
 * the corresponding elements in an ArrayList with the current iteration values. else continue.    
 * Step 4: if step 2 matches then compare the quantity of current iteration and quantity of the ArrayList. 
 * If the current quantity is less than the minimum element then swap it with the current quantity and replace all
 *  the corresponding elements in an ArrayList with the current iteration values. else continue
 * Step 5: If the current iteration customer and product combination does not match with the already existing ArrayList elements then insert 
 * the elements into an ArrayList.
 * Step 6: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and right indenting
 * the integer values and displaying the date in expected format.    
 */



public class myQuery1
{

	public static void main(String[] args)
	{
		//Data base credentials and url
		String usr ="postgres";
		String pwd ="believegod@1";
		String url ="jdbc:postgresql://localhost:5432/postgres";
		ArrayList<String> listOne = new ArrayList<String>(); //declaring the ArrayList to store elements
		// declaring variables to perform minimum, maximum and average calculations.
		System.out.println("For each combination of customer and product, this program calculates the"+
				"\nmaximum and minimum sales quantities along with the corresponding dates,"+
				"\nand the state in which the sales transaction took place, and displays it."+
				"\nFor the same combination customer and product, \nit will calculate the average sales quantity"+
				" and displays it as well.\n");
		int count = 1,max=0, min=0,sum=0, occurrence=0;
		double avg=0;
	
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
            
			while(rs.next())      // get the row entry from result set.
			   { 				
				//Adding elements into the ArrayList for the first iteration.
				 while(count == 1)
					{
						listOne.add(rs.getString("cust"));   // add the elements into an ArrayList
						listOne.add(rs.getString("prod"));
						listOne.add(String.valueOf(rs.getInt("quant")));
						listOne.add(String.valueOf(rs.getInt("day")));
						listOne.add(String.valueOf(rs.getInt("month")));
						listOne.add(String.valueOf(rs.getInt("year")));
						listOne.add(rs.getString("state"));
						listOne.add(String.valueOf(rs.getInt("quant")));
						listOne.add(String.valueOf(rs.getInt("day")));
						listOne.add(String.valueOf(rs.getInt("month")));
						listOne.add(String.valueOf(rs.getInt("year")));
						listOne.add(rs.getString("state"));
						listOne.add("0");
						listOne.add("0");
						count++;
					 }

				int i;
				title:	
				for(i = 0; i < listOne.size(); i=i+14)   // iterate through an ArrayList using size() method in for loop. 
				{
					
					// check for the customer and product combination
				  if ((rs.getString("cust").equals(listOne.get(i))) && ((rs.getString("prod")).equals(listOne.get(i+1))))
					{
					    occurrence=Integer.parseInt(listOne.get(i+13))+1;    //Calculate the occurrence of the customer, product combination 
						sum=Integer.parseInt(listOne.get(i+12)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
						listOne.set(i+12, String.valueOf(sum));                        // Add the sum into the ArrayList
						listOne.set(i+13, String.valueOf(occurrence));                 // Add the occurrence into the ArrayList
					   if (rs.getInt("quant")>=Integer.parseInt(listOne.get(i+2))) // checking for maximum quantity
						{
						   
							listOne.set(i,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(i+1,rs.getString("prod"));
							listOne.set(i+2,(String.valueOf(rs.getInt("quant"))));
							listOne.set(i+3,(String.valueOf(rs.getInt("day"))));
							listOne.set(i+4,(String.valueOf(rs.getInt("month"))));
							listOne.set(i+5,String.valueOf(rs.getInt("year")));
							listOne.set(i+6,rs.getString("state"));
						}
						if (rs.getInt("quant")<Integer.parseInt(listOne.get(i+7))) // checking for the minimum quantity
						{
							listOne.set(i,rs.getString("cust"));                     // Replace the elements in an ArrayList using set() method
							listOne.set(i+1,rs.getString("prod"));
							listOne.set(i+7,(String.valueOf(rs.getInt("quant"))));
							listOne.set(i+8,(String.valueOf(rs.getInt("day"))));
							listOne.set(i+9,(String.valueOf(rs.getInt("month"))));
							listOne.set(i+10,String.valueOf(rs.getInt("year")));
							listOne.set(i+11,(String.valueOf(rs.getString("state"))));
						}
						
						break title;  // break the for loop if it satisfies either maximum or minimum condition for current customer and product combination.
					}
				}
				//* if the customer and product combination does not match with existing entries in an ArrayList
				//* then insert the current entry into the ArrayList.
					if(i == listOne.size())   // check if the values of i is equal to size of the ArrayList
					{
						listOne.add(rs.getString("cust"));    // add the elements into the ArrayList.
						listOne.add(rs.getString("prod"));
						listOne.add(String.valueOf(rs.getInt("quant")));
						listOne.add(String.valueOf(rs.getInt("day")));
						listOne.add(String.valueOf(rs.getInt("month")));
						listOne.add(String.valueOf(rs.getInt("year")));
						listOne.add(rs.getString("state"));
						listOne.add(String.valueOf(rs.getInt("quant")));
						listOne.add(String.valueOf(rs.getInt("day")));
						listOne.add(String.valueOf(rs.getInt("month")));
						listOne.add(String.valueOf(rs.getInt("year")));
						listOne.add(rs.getString("state"));
						listOne.add(String.valueOf(rs.getInt("quant"))); 
						listOne.add("1");
					}
				}
			
						
			// Printing all the required elements from an ArrayList into the console as showed below:
			System.out.println("CUSTOMER       PRODUCT     MAX_Q    DATE          ST         MIN_Q    DATE          ST           AVG_Q");  // Column heading
			System.out.println("========       =======     =====    ==========    ==         =====    =========     ==           =====");
			for (int k=0;k<listOne.size()-1;k=k+14)  // loop to iterate through the ArraList.
				{
					 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
					 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
					 System.out.print(String.format("%5s",String.valueOf(listOne.get(k+2)))+ "    ");   // Right Indent the integer output
					 System.out.print(String.format("%-10s",(String.format("%02d",Integer.parseInt(listOne.get(k+4)))+
							 "/"+String.format("%02d",Integer.parseInt(listOne.get(k+3)))+
							 "/"+String.valueOf(listOne.get(k+5))))+ "    ");              // Left Pad the date with zeros if the day and month is of single digit number.
					 System.out.print(String.format("%-5s",listOne.get(k+6))+ "   ");
					 System.out.print(String.format("%8s",String.valueOf(listOne.get(k+7)))+ "    ");
					 System.out.print(String.format("%-10s",(String.format("%02d",Integer.parseInt(listOne.get(k+9)))+
							 "/"+String.format("%02d",Integer.parseInt(listOne.get(k+8)))+
							 "/"+String.valueOf(listOne.get(k+10))))+ "    ");
					 System.out.print(String.format("%-10s",listOne.get(k+11))+ "   ");
					 avg=Math.round((double)Integer.parseInt(listOne.get(k+12))/(double)Integer.parseInt(listOne.get(k+13)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
					 int avg1=(int)avg;
					 System.out.print(String.format("%5s",avg1)+ "    ");
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
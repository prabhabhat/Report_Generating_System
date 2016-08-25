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

/*For each combination of customer and product, this program calculates the
 * maximum sales quantities for NY and NJ states, minimum sales quantities for state CT in 
 * three separate columns along with the corresponding dates for all three.
 * For the NY and NJ states it will include sales that occurred between the year 2000 and 2005
 * For the CT state it will include all the sales. 
 */

/* Logic of the program:
 * Step 1: Add the first entry of the result set into an ArrayList by checking the state and year of the sales relation. 
 * else add default values to other columns.
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then check for state NY and year between 2000 and 2005.
 * If all of these matches then compare the quantity of current iteration and quantity of the ArrayList. If the current quantity is greater than  
 * maximum element then swap it with current quantity and replace all the corresponding elements in an ArrayList with the current iteration values. else continue    
 * Step 4: repeat step 3 for state NJ
 * Step 5: if step 2 matches then check for state CT, if it matches compare the quantity of current iteration and quantity of the ArrayList. 
 * If the current quantity is less than the minimum element then swap it with the current quantity and replace all the corresponding elements 
 * in an ArrayList with the current iteration values. else continue
 * Step 6: If the current iteration customer and product combination does not match with the already existing ArrayList elements then insert 
 * the elements into an ArrayList by checking the state and year for NY and NJ and for CT by checking the state.
 * Step 7: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and right indenting
 * the integer values and displaying the date in expected format.    
 */


public class myQuery2
{

	public static void main(String[] args)
	{
		//Data base credentials and url
		String usr ="postgres";
		String pwd ="believegod@1";
		String url ="jdbc:postgresql://localhost:5432/postgres";
		ArrayList<String> listOne = new ArrayList<String>();//declaring the ArrayList to store elements
		int count = 1;
		System.out.println("For each combination of customer and product, this program calculates the"+
				"\nmaximum sales quantities for NY and NJ states, minimum sales quantities for state CT in"+ 
				"\nthree separate columns along with the corresponding dates for all three."+
				"\nFor the NY and NJ states it will include sales that occurred between the year 2000 and 2005"+
				"\nFor the CT state it will include all the sales.\n ");
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
            
			while(rs.next())  // get the row entry from result set. 
			  { 
			   	while(count == 1) // Adding elements into an ArrayList for the first iteration.
			    	{
						String st_nnc=rs.getString("state"); // get the state from result set and assign it to a variable.
						listOne.add(rs.getString("cust")); 
						listOne.add(rs.getString("prod"));
						if(st_nnc.equals("NY"))  // check if the state is NY
				    		{
						    if (Integer.parseInt(rs.getString("year"))>=2000 && Integer.parseInt(rs.getString("year")) <= 2005 ) // Check if the year is in between 2000 and 2005
							  {						
							     listOne.add(String.valueOf(rs.getInt("quant")));    // add elements from result set using add() method.
							     listOne.add(String.valueOf(rs.getInt("day")));
							     listOne.add(String.valueOf(rs.getInt("month")));
							     listOne.add(String.valueOf(rs.getInt("year")));
						      }
				    		}
						 else
						   {
						      listOne.add("0");       // add the default values to an ArrayList
						      listOne.add("0");
						      listOne.add("0");
						      listOne.add("1900");
						   }
						     
						  if(st_nnc.equals("NJ"))   // check if the state is NJ
						     {
							 if (Integer.parseInt(rs.getString("year"))>=2000 && Integer.parseInt(rs.getString("year")) <= 2005 ) // Check if the year is in between 2000 and 2005
							    {
							       listOne.add(String.valueOf(rs.getInt("quant")));    // add the elements from result set.
							       listOne.add(String.valueOf(rs.getInt("day")));
							       listOne.add(String.valueOf(rs.getInt("month")));
							       listOne.add(String.valueOf(rs.getInt("year")));
						        }
						     }
						   else
						     {
							    listOne.add("0");    // add the default values to an ArrayList
							    listOne.add("0");
							    listOne.add("0");
							    listOne.add("1900");
						      }
						
						   if(st_nnc.equals("CT"))  //check if the state is CT
						     {
							    listOne.add(String.valueOf(rs.getInt("quant")));    // add the elements from result set.
							    listOne.add(String.valueOf(rs.getInt("day")));
							    listOne.add(String.valueOf(rs.getInt("month")));
							    listOne.add(String.valueOf(rs.getInt("year")));
						     }
						   else
						     {
							    listOne.add("5000");       // add the default values to an ArrayList
							    listOne.add("0");
							    listOne.add("0");
							    listOne.add("1900");
						     }
						 count++;
					  }

				int i;
				title:	
				for(i = 0; i < listOne.size(); i=i+14)   // iterate through an ArrayList using size() method in for loop.
				{
					// check for the customer and product combination
					if ((rs.getString("cust").equals(listOne.get(i))) && ((rs.getString("prod")).equals(listOne.get(i+1))))
					{
						String str=rs.getString("state"); // get the state from result set
						if(str.equals("NY"))  // check if the state is NY
						  {
						   if (Integer.parseInt(rs.getString("year"))>=2000 && Integer.parseInt(rs.getString("year")) <= 2005 )  // check if the year is between 2000 and 2005 
						     {		
						       if (rs.getInt("quant")>Integer.parseInt(listOne.get(i+2))) // check for the maximum quantity.
						           {
							          listOne.set(i,rs.getString("cust"));      // Replace the elements in an ArrayList using set() method
							          listOne.set(i+1,rs.getString("prod"));
							          listOne.set(i+2,(String.valueOf(rs.getInt("quant"))));
							          listOne.set(i+3,(String.valueOf(rs.getInt("day"))));
							          listOne.set(i+4,(String.valueOf(rs.getInt("month"))));
							          listOne.set(i+5,String.valueOf(rs.getInt("year")));
							       }
						       }
						   }
						if(str.equals("NJ"))  // check if the state is NJ
						  {
						    if (Integer.parseInt(rs.getString("year"))>=2000 && Integer.parseInt(rs.getString("year")) <= 2005 )  // check if the year is between 2000 and 2005
						       {	
						        if (rs.getInt("quant")>Integer.parseInt(listOne.get(i+6)))  // check for the maximum quantity.
						           {
							          listOne.set(i,rs.getString("cust"));            // Replace the elements in an ArrayList using set() method
							          listOne.set(i+1,rs.getString("prod"));
							          listOne.set(i+6,(String.valueOf(rs.getInt("quant"))));
							          listOne.set(i+7,(String.valueOf(rs.getInt("day"))));
							          listOne.set(i+8,(String.valueOf(rs.getInt("month"))));
							          listOne.set(i+9,String.valueOf(rs.getInt("year")));
						           }
						        }
					       }
						if(str.equals("CT"))  //check if the state is CT
						    {
						      if (rs.getInt("quant")<Integer.parseInt(listOne.get(i+10))) // Check for the minimum quantity.
						        {
							       listOne.set(i,rs.getString("cust"));                 // Replace the elements in an ArrayList using set() method
							       listOne.set(i+1,rs.getString("prod"));
							       listOne.set(i+10,(String.valueOf(rs.getInt("quant"))));
							       listOne.set(i+11,(String.valueOf(rs.getInt("day"))));
							       listOne.set(i+12,(String.valueOf(rs.getInt("month"))));
							       listOne.set(i+13,String.valueOf(rs.getInt("year")));
						        }
						    }
					     break title;	// break the for loop if it satisfies either maximum or minimum condition for current customer and product combination.
				        }
				   }
				//* if the customer and product combination does not match with existing entries in an ArrayList
				//* then insert the current entry into the ArrayList.
				if(i == listOne.size())  // check if the values of i is equal to size of the ArrayList
				  {
				   if (rs.getString("state").equals("NY"))  // check if the state is NY
				    	{
						 if (Integer.parseInt(rs.getString("year"))>=2000 && Integer.parseInt(rs.getString("year")) <= 2005 )  // check if the year is in between 2000 and 2005
							 {   
					        	listOne.add(rs.getString("cust"));   // add the elements into the ArrayList.
						        listOne.add(rs.getString("prod"));
						        listOne.add(String.valueOf(rs.getInt("quant")));
						        listOne.add(String.valueOf(rs.getInt("day")));
						        listOne.add(String.valueOf(rs.getInt("month")));
						        listOne.add(String.valueOf(rs.getInt("year")));
						        listOne.add("0");              // add the default values into the ArrayList.
							    listOne.add("0");
							    listOne.add("0");
							    listOne.add("1900");
							    listOne.add("5000");
							    listOne.add("0");
							    listOne.add("0");
							    listOne.add("1900");
						     }
					 	 }
					if (rs.getString("state").equals("NJ"))  // check if the state is NJ
					 	{
						 if (Integer.parseInt(rs.getString("year"))>=2000 && Integer.parseInt(rs.getString("year")) <= 2005 ) // check id the year is in between 2000 and 2005
							 {	
						        listOne.add(rs.getString("cust")); 
					            listOne.add(rs.getString("prod"));
					            listOne.add("0");              // add the default values into the ArrayList.
					            listOne.add("0");
					            listOne.add("0");
					            listOne.add("1900");
					            listOne.add(String.valueOf(rs.getInt("quant")));   // add the elements into the ArrayList.
					            listOne.add(String.valueOf(rs.getInt("day")));
					            listOne.add(String.valueOf(rs.getInt("month")));
					            listOne.add(String.valueOf(rs.getInt("year")));
					            listOne.add("5000");             // add the default values into the ArrayList.
					            listOne.add("0");
					            listOne.add("0");
					            listOne.add("1900");
					 	     }
					 	 }
					if (rs.getString("state").equals("CT"))   // check if the state is CT
					  {
						  listOne.add(rs.getString("cust")); 
					      listOne.add(rs.getString("prod"));
					      listOne.add("0");                  // add the default values into the ArrayList.
						  listOne.add("0");
						  listOne.add("0");
						  listOne.add("1900");
						  listOne.add("0");
						  listOne.add("0");
						  listOne.add("0");
						  listOne.add("1900");
				          listOne.add(String.valueOf(rs.getInt("quant")));    // add the elements into the ArrayList.
					      listOne.add(String.valueOf(rs.getInt("day")));
					      listOne.add(String.valueOf(rs.getInt("month")));
					      listOne.add(String.valueOf(rs.getInt("year")));
					 	}
				  }
			}
			
			// Print all the required elements from an ArrayList into the console as showed below:
			System.out.println("CUSTOMER       PRODUCT           NY_MAX    DATE              NJ_MAX    DATE              CT_MIN    DATE");  // Column heading
			System.out.println("========       =======           ======    ==========        ======    ==========        ======    ==========");
			for (int k=0;k<listOne.size()-1;k=k+14)   // loop to iterate through the ArrayList.
				{
					 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
					 System.out.print(String.format("%-10s",listOne.get(k+1))+ "    ");
					 if(listOne.get(k+2).equals("0"))  // check if the quantity is default value 0, if yes print empty. 
					   {
						 String ny="";
						 System.out.print(String.format("%10s",ny)+ "    ");
						 System.out.print(String.format("%-10s",ny)+ "    ");
					   }
					 else   // else print the NY_MAX quantity and it's corresponding dates.
					   {
			    		 System.out.print(String.format("%10s",String.valueOf(listOne.get(k+2)))+ "    ");  // Right Indent the integer output
				    	 System.out.print(String.format("%-10s",(String.format("%02d",Integer.parseInt(listOne.get(k+4)))+
							 "/"+String.format("%02d",Integer.parseInt(listOne.get(k+3)))+
							 "/"+String.valueOf(listOne.get(k+5))))+ "    ");     // Left Pad the date with zeros if the day and month is of single digit number.
					   }
					 if(listOne.get(k+6).equals("0"))  //check if the quantity is default value 0, if yes print empty.
					   {
						 String nj="";
						 System.out.print(String.format("%10s",nj)+ "    ");
						 System.out.print(String.format("%-10s",nj)+ "    ");
					   }
					 else  // else print the NJ_MAX quantity and it's corresponding dates
					   {
					     System.out.print(String.format("%10s",String.valueOf(listOne.get(k+6)))+ "    ");
					     System.out.print(String.format("%-10s",(String.format("%02d",Integer.parseInt(listOne.get(k+8)))+
							 "/"+String.format("%02d",Integer.parseInt(listOne.get(k+7)))+
							 "/"+String.valueOf(listOne.get(k+9))))+ "    ");
					   }
					 if(listOne.get(k+10).equals("5000"))  // check id the quantity is default value 5000, if yes print empty
					   {
						 String ct="";
						 System.out.print(String.format("%10s",ct)+ "    ");
						 System.out.print(String.format("%-10s",ct)+ "    ");
						 System.out.print("\n");
					   }
					 else  // else print CT_MIN quantity and it's corresponding dates.
					   {
					     System.out.print(String.format("%10s",String.valueOf(listOne.get(k+10)))+ "    ");
					     System.out.print(String.format("%-10s",(String.format("%02d",Integer.parseInt(listOne.get(k+12)))+
							 "/"+String.format("%02d",Integer.parseInt(listOne.get(k+11)))+
							 "/"+String.valueOf(listOne.get(k+13))))+ "    ");
					     System.out.print("\n");
					   }
				  }	
			}
		catch(SQLException e)
		{
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}

	}

}
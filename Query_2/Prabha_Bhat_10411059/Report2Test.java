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
 * average sales before and after each quarter (e.g. for Q2, it will show the average sales of
 * Q1 and Q3. For 'before' Q1 and 'after' Q4, it will display <NULL>). The 'YEAR' attribute is
 * not considered for this query - For example, both Q1 of 1997 and Q1 of 1998 are considered Q1
 * regardless of the year. 
 */

/* Logic of the program:
 * Step 1: Add the first entry of the result set into an ArrayList by considering the sum, occurrence and quarter for all the 4 quaeters. 
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then compare the month of current iteration. 
 * If the current month is between 1 to 3 then Q1, between 4 to 6 then Q2, between 7 to 9 then Q3 and between 10 to 12 then Q4. 
 * replace all the corresponding elements in an ArrayList with the current iteration values. else continue.    
 * Step 4: If the current iteration customer and product combination does not match with the already existing ArrayList elements then insert 
 * the elements into an ArrayList.
 * Step 5: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and right indenting
 * the integer values and displaying the date in expected format.    
 */



public class Report2Test
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
				"\naverage sales before and after each quarter (e.g. for Q2, it will show the average sales of"+
				" Q1 and Q3. \nFor 'before' Q1 and 'after' Q4, it will display <NULL>). \nThe 'YEAR' attribute is"+
				" not considered for this query - For example, both Q1 of 1997 and Q1 of 1998 \nare considered Q1 "+
				" regardless of the year.\n"+
				"The average decimal values are rounded to their nearest whole numbers");
		int sum1=0, sum2=0, sum3=0, sum4=0;
		int occurrence1=0,occurrence2=0,occurrence3=0,occurrence4=0;
		int count = 1,sum1_af=0,sum2_be=0,sum2_af=0,sum3_be=0,sum3_af=0,sum4_be=0,sum4_af=0;
		int	occurrence1_af=0,occurrence2_be=0,occurrence2_af=0,occurrence3_be=0,occurrence3_af=0,
				occurrence4_be=0,occurrence4_af=0;
		double avg1=0,avg2=0;
	
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
						listOne.add(String.valueOf(rs.getInt("month")));
						listOne.add("0");           //sum1=0
						listOne.add("0");           //occurrence1=0
						listOne.add("0");           //sum1_af=0
						listOne.add("0");           //occurrence1_af=0
						
						listOne.add("0");           //sum2=0
						listOne.add("0");           //ccurrence2=0
						listOne.add("0");           //sum2_be=0
						listOne.add("0");           //occurrence2_be=0
						listOne.add("0");           //sum2_af=0
						listOne.add("0");           //occurrence2_af=0
						
						listOne.add("0");           //sum3=0
						listOne.add("0");           //ccurrence3=0
						listOne.add("0");           //sum3_be=0
						listOne.add("0");           //occurrence3_be=0
						listOne.add("0");           //sum3_af=0
						listOne.add("0");           //occurrence3_af=0
						
						listOne.add("0");           //sum4=0
						listOne.add("0");           //ccurrence4=0
						listOne.add("0");           //sum4_be=0
						listOne.add("0");           //occurrence4_be=0
						
						listOne.add("0");           //quarter1
						listOne.add("0");           //quarter2
						listOne.add("0");           //quarter3
						listOne.add("0");           //quarter4
					    count++;
					}

				
				int i;
				title:	
				for(i = 0; i < listOne.size(); i=i+27)   // iterate through an ArrayList using size() method in for loop. 
				{
				 // check for the customer and product combination
				  if ((rs.getString("cust").equals(listOne.get(i))) && ((rs.getString("prod")).equals(listOne.get(i+1))))
					{
						int mon=Integer.parseInt(rs.getString("month"));    //Calculate the month of the customer, product combination 
						if ( mon >= 1 && mon <= 3 )
						{
						}
						else if ( mon >= 4 && mon <= 6 )
						{
						}
						else if ( mon >= 7 && mon <= 9 )
						{
							
						}
						else
						{
							
						}
						break title;	
					}
				} 
				
				//* if the customer and product combination does not match with existing entries in an ArrayList
				//* then insert the current entry into the ArrayList.
				if(i == listOne.size())   // check if the values of i is equal to size of the ArrayList
					{
						listOne.add(rs.getString("cust"));    // add the elements into the ArrayList.
						listOne.add(rs.getString("prod"));
						listOne.add(String.valueOf(rs.getInt("month")));
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0"); 
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0"); 
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");
						listOne.add("0");           
						listOne.add("0");           
						listOne.add("0");           
						listOne.add("0");           
					}
				
				
				int m;
				title1:	
				for(m = 0; m < listOne.size(); m=m+27)   // iterate through an ArrayList using size() method in for loop. 
				{
				 // check for the customer and product combination
				  if ((rs.getString("cust").equals(listOne.get(m))) && ((rs.getString("prod")).equals(listOne.get(m+1))))
					{
						int montp=Integer.parseInt(rs.getString("month"));    //Calculate the month of the customer, product combination 
						if ( montp >= 1 && montp <= 3 )
						{
							sum1=Integer.parseInt(listOne.get(m+3)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence1=Integer.parseInt(listOne.get(m+4))+1;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+23,"Q1");
							listOne.set(m+3, String.valueOf(sum1));
							listOne.set(m+4, String.valueOf(occurrence1));
							sum1_af=Integer.parseInt(listOne.get(m+7));  // Calculate the sum of the customer, product combination.
							occurrence1_af=Integer.parseInt(listOne.get(m+8));    //Calculate the occurrence of the customer, product combination 
							listOne.set(m+5, String.valueOf(sum1_af));
							listOne.set(m+6, String.valueOf(occurrence1_af));
						}
						else if ( montp >= 4 && montp <= 6 )
						{
							sum2=Integer.parseInt(listOne.get(m+7)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence2=Integer.parseInt(listOne.get(m+8))+1;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+24,"Q2");
							listOne.set(m+7, String.valueOf(sum2));
							listOne.set(m+8, String.valueOf(occurrence2));
							sum2_be=Integer.parseInt(listOne.get(m+3));  // Calculate the sum of the customer, product combination.
							occurrence2_be=Integer.parseInt(listOne.get(m+4));    //Calculate the occurrence of the customer, product combination 
							sum2_af=Integer.parseInt(listOne.get(m+13));  // Calculate the sum of the customer, product combination.
							occurrence2_af=Integer.parseInt(listOne.get(m+14));    //Calculate the occurrence of the customer, product combination 
							listOne.set(m+9, String.valueOf(sum2_be));
							listOne.set(m+10, String.valueOf(occurrence2_be));
							listOne.set(m+11, String.valueOf(sum2_af));
							listOne.set(m+12, String.valueOf(occurrence2_af));
						}
						else if ( montp >= 7 && montp <= 9 )
						{
							sum3=Integer.parseInt(listOne.get(m+13)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence3=Integer.parseInt(listOne.get(m+14))+1;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+25,"Q3");
							listOne.set(m+13, String.valueOf(sum3));
							listOne.set(m+14, String.valueOf(occurrence3));
							sum3_be=Integer.parseInt(listOne.get(m+7));  // Calculate the sum of the customer, product combination.
							occurrence3_be=Integer.parseInt(listOne.get(m+8));    //Calculate the occurrence of the customer, product combination 
							sum3_af=Integer.parseInt(listOne.get(m+19));  // Calculate the sum of the customer, product combination.
							occurrence3_af=Integer.parseInt(listOne.get(m+20));    //Calculate the occurrence of the customer, product combination 
							listOne.set(m+15, String.valueOf(sum3_be));
							listOne.set(m+16, String.valueOf(occurrence3_be));
							listOne.set(m+17, String.valueOf(sum3_af));
							listOne.set(m+18, String.valueOf(occurrence3_af));
						}
						else
						{
							sum4=Integer.parseInt(listOne.get(m+19)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence4=Integer.parseInt(listOne.get(m+20))+1;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+26,"Q4");
							listOne.set(m+19, String.valueOf(sum4));
							listOne.set(m+20, String.valueOf(occurrence4));
							sum4_be=Integer.parseInt(listOne.get(m+13));  // Calculate the sum of the customer, product combination.
							occurrence4_be=Integer.parseInt(listOne.get(m+14));    //Calculate the occurrence of the customer, product combination 
							listOne.set(m+21, String.valueOf(sum4_be));
							listOne.set(m+22, String.valueOf(occurrence4_be));
						}
						break title1;	
					}
				}
				
				
				int j;
				for (j=0;j<listOne.size();j=j+27)
				{
					int tmpmonth=Integer.parseInt(rs.getString("month"));
					if (tmpmonth>=1 && tmpmonth <=3)
					{
						sum1_af=Integer.parseInt(listOne.get(j+7));   // Calculate the sum of the customer, product combination for Q1.
						occurrence1_af=Integer.parseInt(listOne.get(j+8));  //Calculate the occurrence of the customer, product combination for Q1
						listOne.set(j+5, String.valueOf(sum1_af));
						listOne.set(j+6, String.valueOf(occurrence1_af));
					}
					else if (tmpmonth>=4 && tmpmonth <=6)
					{
						sum2_be=Integer.parseInt(listOne.get(j+3));  // Calculate the sum of the customer, product combination for Q2.
						occurrence2_be=Integer.parseInt(listOne.get(j+4));    //Calculate the occurrence of the customer, product combination for Q2 
						sum2_af=Integer.parseInt(listOne.get(j+13));  // Calculate the sum of the customer, product combination for Q2.
						occurrence2_af=Integer.parseInt(listOne.get(j+14));    //Calculate the occurrence of the customer, product combination for Q2 
						listOne.set(j+9, String.valueOf(sum2_be));
						listOne.set(j+10, String.valueOf(occurrence2_be));
						listOne.set(j+11, String.valueOf(sum2_af));
						listOne.set(j+12, String.valueOf(occurrence2_af));
					}
					else if (tmpmonth>=7 && tmpmonth <=9)
					{
						sum3_be=Integer.parseInt(listOne.get(j+7));  // Calculate the sum of the customer, product combination for Q3.
						occurrence3_be=Integer.parseInt(listOne.get(j+8));    //Calculate the occurrence of the customer, product combination for Q3 
						sum3_af=Integer.parseInt(listOne.get(j+19));  // Calculate the sum of the customer, product combination for Q3.
						occurrence3_af=Integer.parseInt(listOne.get(j+20));    //Calculate the occurrence of the customer, product combination for Q3 
						listOne.set(j+15, String.valueOf(sum3_be));
						listOne.set(j+16, String.valueOf(occurrence3_be));
						listOne.set(j+17, String.valueOf(sum3_af));
						listOne.set(j+18, String.valueOf(occurrence3_af));
					}
					else
					{
						sum4_be=Integer.parseInt(listOne.get(j+13));  // Calculate the sum of the customer, product combination for Q4.
						occurrence4_be=Integer.parseInt(listOne.get(j+14));    //Calculate the occurrence of the customer, product combination for Q4 
						listOne.set(j+21, String.valueOf(sum4_be));
						listOne.set(j+22, String.valueOf(occurrence4_be));
					}
				}
			}
		//	System.out.println(listOne);
			System.out.print("\n");
			// Printing all the required elements from an ArrayList into the console as showed below:
			System.out.println("CUSTOMER       PRODUCT     QUARTER     BEFORE_AVG     AFTER_AVG");  // Column heading
			System.out.println("========       =======     =======     ==========     =========");
			for (int k=0;k<listOne.size()-1;k=k+27)  // loop to iterate through the ArraList.
				{
				  if (listOne.get(k+23).equals("Q1"))
					 {
						 avg1=Math.round((double)Integer.parseInt(listOne.get(k+5))/(double)Integer.parseInt(listOne.get(k+6)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt1=(int)avg1;
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%-8s",listOne.get(k+23))+ "    ");  //Right indent the integer output
						 System.out.print(String.format("%14s","<NULL>    "));               // Insert NULL for Q1 before
						if (avgt1==0) //check if the average value is 0. if yes then print NULL
						{
							System.out.println(String.format("%14s","<NULL>    ")); 
						}
						else
						{
						 System.out.println(String.format("%10s",avgt1)+ "    ");
						}
					 }
					 
				 if (listOne.get(k+24).equals("Q2"))
					 {
						 avg1=Math.round((double)Integer.parseInt(listOne.get(k+9))/(double)Integer.parseInt(listOne.get(k+10)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt1=(int)avg1;
						 avg2=Math.round((double)Integer.parseInt(listOne.get(k+11))/(double)Integer.parseInt(listOne.get(k+12)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt2=(int)avg2;
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%-8s",listOne.get(k+24))+ "    ");   // Right indent the integer output
						 if(avgt1==0) //check if the average value is 0. if yes then print NULL
						 {
							 System.out.print(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
							 System.out.print(String.format("%10s",avgt1)+ "    ");
						 }
						 if(avgt2==0) //check if the average value is 0. if yes then print NULL
							 {
							 System.out.println(String.format("%14s","<NULL>    "));
							 }
						 else
						 {
							 System.out.println(String.format("%10s",avgt2)+ "    ");
						 }
					 }
					 
				 if (listOne.get(k+25).equals("Q3"))
					 {
						 avg1=Math.round((double)Integer.parseInt(listOne.get(k+15))/(double)Integer.parseInt(listOne.get(k+16)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt1=(int)avg1;
						 avg2=Math.round((double)Integer.parseInt(listOne.get(k+17))/(double)Integer.parseInt(listOne.get(k+18)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt2=(int)avg2;
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%-8s",listOne.get(k+25))+ "    ");   //Right indent the integer values
						 if(avgt1==0)//check if the average value is 0. if yes then print NULL
						 {
							 System.out.print(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
							 System.out.print(String.format("%10s",avgt1)+ "    ");
						 }
						 if(avgt2==0) //check if the average value is 0. if yes then print NULL
							 {
							 System.out.println(String.format("%14s","<NULL>    "));
							 }
						 else
						 {
							 System.out.println(String.format("%10s",avgt2)+ "    ");
						 }  
					 }
					 
				 if (listOne.get(k+26).equals("Q4"))
					 {
						 avg1=Math.round((double)Integer.parseInt(listOne.get(k+21))/(double)Integer.parseInt(listOne.get(k+22)));  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
						 int avgt1=(int)avg1;
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%-8s",listOne.get(k+26))+ "    ");   //Right indent the integer values
						 if(avgt1==0) //check if the average value is 0. if yes then print NULL
						 {
							 System.out.print(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
							 System.out.print(String.format("%10s",avgt1)+ "    ");
						 }
						 System.out.println(String.format("%14s","<NULL>    "));             // Insert NULL for Q4 after
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
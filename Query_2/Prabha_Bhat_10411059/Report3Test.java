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

/* For each combination of customer and product, this program, count for
 * each quarter , how many sales of previous and how many sales of the following quarter 
 * had quantities between that quarter's average sale and minimum sale.
 * For this query the 'YEAR' attribute is not considered.
 */

/* Logic of the program:
 * Step 1: Add the first entry of the result set into an ArrayList by considering the sum, occurrence and quarter for all the 4 quaeters. 
 * Step 2: iterate through the ArrayList and check for each customer and product combination, 
 * Step 3: if step 2 matches then compare the month of current iteration. 
 * If the current month is between 1 to 3 then Q1, between 4 to 6 then Q2, between 7 to 9 then Q3 and between 10 to 12 then Q4. 
 * replace all the corresponding elements in an ArrayList with the current iteration values. else continue.  
 * Step 4: If the current quantity is less than the quantity in the ArrayList then replace the minimum quantity with the current quantity. 
 * Step 5: If the current iteration customer and product combination does not match with the already existing ArrayList elements then insert 
 * the elements into an ArrayList.
 * Step 6: iterate through the ArrayList and check for each customer and product combination,
 * Step 7: If the step 6 matches then check for the quarter, then check if the previous and next quantity 
 * lies between minimum and average quantity of the current quarter.
 * Step 8:If Step 7 matches then increase the count by one. and insert it into ArralList.
 * Step 9: Finally iterate through the elements in an ArralList and print the entries as expected by left indenting the string values and right indenting
 * the integer values and displaying the date in expected format.    
 */



public class Report3Test
{

	public static void main(String[] args)
	{
		//Data base credentials and url
		String usr ="postgres";
		String pwd ="believegod@1";
		String url ="jdbc:postgresql://localhost:5432/postgres";
		ArrayList<String> listOne = new ArrayList<String>(); //declaring the ArrayList to store elements
		// declaring variables to perform minimum, maximum and average calculations.
		System.out.println("For each combination of customer and product, this program, count for"+
				"\neach quarter , how many sales of previous and how many sales of the following quarter "+
				"\nhad quantities between that quarter's average sale and minimum sale."+
				"\nFor this query the 'YEAR' attribute is not considered.\n");
		int sum1=0, sum2=0, sum3=0, sum4=0;
		int occurrence1=0,occurrence2=0,occurrence3=0,occurrence4=0;
		int be_numquant2=0,be_numquant3=0,be_numquant4=0;
		int af_numquant1=0,af_numquant2=0,af_numquant3=0;
		int count = 1;
		int avg1=0,avg2=0,avg3=0,avg4=0;
	
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
		                listOne.add(String.valueOf(rs.getInt("month")));  //quarter
		                listOne.add("0");                                 //sum1=0
		                listOne.add("0");                                 //occurrence1=0
		                listOne.add("0");                                 //avg1=0
		                listOne.add("5000");                              //min1 
		                listOne.add("0");                                 //af_numquant1=0
		                listOne.add("0");                                 //sum2=0
				        listOne.add("0");                                 //ccurrence2=0
				        listOne.add("0");                                 //avg2=0
				        listOne.add("5000");                              //min2
				        listOne.add("0");                                 //be_numquant2=0
				        listOne.add("0");                                 //af_numquant2=0
				        listOne.add("0");                                 //sum3=0
				        listOne.add("0");                                 //ccurrence3=0
				        listOne.add("0");                                 //avg3=0
				        listOne.add("5000");                              //min3
				        listOne.add("0");                                 //be_numquant3=0
				        listOne.add("0");                                 //af_numquant3=0
				        listOne.add("0");                                 //sum4=0
				        listOne.add("0");                                 //ccurrence4=0
				        listOne.add("0");                                 //avg4=0
				        listOne.add("5000");                              //min4
				        listOne.add("0");                                 //be_numquant4=0
				        listOne.add("0");                                 // quarter1
				        listOne.add("0");                                 // quarter2
				        listOne.add("0");                                 // quarter3
				        listOne.add("0");                                 // quarter4
				        count++;
					}

				int i;
				title:	
				for(i = 0; i < listOne.size(); i=i+29)   // iterate through an ArrayList using size() method in for loop. 
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
		                listOne.add(rs.getString("cust"));   // add the elements into an ArrayList
		                listOne.add(rs.getString("prod"));
		                listOne.add(String.valueOf(rs.getInt("month")));  //quarter
		                listOne.add("0");                                 //sum1=0
		                listOne.add("0");                                 //occurrence1=0
		                listOne.add("0");                                 //avg1=0
		                listOne.add("5000");                              //min1 
		                listOne.add("0");                                 //af_numquant1=0
		                listOne.add("0");                                 //sum2=0
				        listOne.add("0");                                 //ccurrence2=0
				        listOne.add("0");                                 //avg2=0
				        listOne.add("5000");                              //min2
				        listOne.add("0");                                 //be_numquant2=0
				        listOne.add("0");                                 //af_numquant2=0
				        listOne.add("0");                                 //sum3=0
				        listOne.add("0");                                 //ccurrence3=0
				        listOne.add("0");                                 //avg3=0
				        listOne.add("5000");                              //min3
				        listOne.add("0");                                 //be_numquant3=0
				        listOne.add("0");                                 //af_numquant3=0
				        listOne.add("0");                                 //sum4=0
				        listOne.add("0");                                 //ccurrence4=0
				        listOne.add("0");                                 //avg4=0
				        listOne.add("5000");                              //min4
				        listOne.add("0");                                 //be_numquant4=0
				        listOne.add("0");                                 // quarter1
				        listOne.add("0");                                 // quarter2
				        listOne.add("0");                                 // quarter3
				        listOne.add("0");                                 // quarter4
				     }
				
				
				int m;
				titlem:	
				for(m = 0; m < listOne.size(); m=m+29)   // iterate through an ArrayList using size() method in for loop. 
				{
					// check for the customer and product combination
					if ((rs.getString("cust").equals(listOne.get(m))) && ((rs.getString("prod")).equals(listOne.get(m+1))))
					{
		     		   int mon=Integer.parseInt(rs.getString("month"));    //Calculate the month of the customer, product combination 
			   		  if ( mon >= 1 && mon <= 3 )
						{
							sum1=Integer.parseInt(listOne.get(m+3)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence1=Integer.parseInt(listOne.get(m+4))+1;
							double tmpavg1=Math.round((double)sum1/(double)occurrence1);  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
							avg1=(int)tmpavg1;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+25,"Q1");
							listOne.set(m+3, String.valueOf(sum1));
							listOne.set(m+4, String.valueOf(occurrence1));
							listOne.set(m+5, String.valueOf(avg1));
							if (rs.getInt("quant")<Integer.parseInt(listOne.get(m+6))) // checking for the minimum quantity
							 {
								listOne.set(m+6,(String.valueOf(rs.getInt("quant"))));
							 }
						  }
				    	else if ( mon >= 4 && mon <= 6 )
						{
							sum2=Integer.parseInt(listOne.get(m+8)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence2=Integer.parseInt(listOne.get(m+9))+1;
							double tmpavg2=Math.round((double)sum2/(double)occurrence2);  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
							avg2=(int)tmpavg2;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+26,"Q2");
							listOne.set(m+8, String.valueOf(sum2));
							listOne.set(m+9, String.valueOf(occurrence2));
							listOne.set(m+10, String.valueOf(avg2));
							if (rs.getInt("quant")<Integer.parseInt(listOne.get(m+11))) // checking for the minimum quantity
							 {
								listOne.set(m+11,(String.valueOf(rs.getInt("quant"))));
							 }
						}
						else if ( mon >= 7 && mon <= 9 )
						{
							sum3=Integer.parseInt(listOne.get(m+14)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence3=Integer.parseInt(listOne.get(m+15))+1;
							double tmpavg3=Math.round((double)sum3/(double)occurrence3);  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
							avg3=(int)tmpavg3;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+27,"Q3");
							listOne.set(m+14, String.valueOf(sum3));
							listOne.set(m+15, String.valueOf(occurrence3));
							listOne.set(m+16, String.valueOf(avg3));
							if (rs.getInt("quant")<Integer.parseInt(listOne.get(m+17))) // checking for the minimum quantity
							 {
								listOne.set(m+17,(String.valueOf(rs.getInt("quant"))));
							 }
						}
						else
						{
							sum4=Integer.parseInt(listOne.get(m+20)) + rs.getInt("quant");  // Calculate the sum of the customer, product combination.
							occurrence4=Integer.parseInt(listOne.get(m+21))+1;
							double tmpavg4=Math.round((double)sum4/(double)occurrence4);  // Calculate the average quantity for the customer, product combination, by using sum and occurrence.
							avg4=(int)tmpavg4;
							listOne.set(m,rs.getString("cust"));                // Replace the elements in an ArrayList using set() method
							listOne.set(m+1,rs.getString("prod"));
							listOne.set(m+28,"Q4");
							listOne.set(m+20, String.valueOf(sum4));
							listOne.set(m+21, String.valueOf(occurrence4));
							listOne.set(m+22, String.valueOf(avg4));
							if (rs.getInt("quant")<Integer.parseInt(listOne.get(m+23))) // checking for the minimum quantity
							 {
								listOne.set(m+23,(String.valueOf(rs.getInt("quant"))));
							 }
						}
				    break titlem;
					}
				}
			}
		
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM Sales");
			while(rs1.next())      // get the row entry from result set.
			   { 			
				int j,tmpmonth=0;
				tit1:
				for(j = 0; j < listOne.size(); j=j+29)   // iterate through an ArrayList using size() method in for loop. 
				 {
				 if ((rs1.getString("cust").equals(listOne.get(j))) && ((rs1.getString("prod")).equals(listOne.get(j+1))))
				  {
					 tmpmonth=Integer.parseInt(rs1.getString("month"));
					 if (tmpmonth>=1 && tmpmonth<=3)
					 {
						 if(rs1.getInt("quant")<=Integer.parseInt(listOne.get(j+10)) && rs1.getInt("quant")>=Integer.parseInt(listOne.get(j+11)))
						    {
							  be_numquant2=Integer.parseInt(listOne.get(j+12))+1;
							  listOne.set(j+12, String.valueOf(be_numquant2));						  
						    } 
					 }
					 else if(tmpmonth>=4 && tmpmonth<=6)
					 {
						 if(rs1.getInt("quant")<=Integer.parseInt(listOne.get(j+5)) && rs1.getInt("quant")>=Integer.parseInt(listOne.get(j+6)))
						    {
							  af_numquant1=Integer.parseInt(listOne.get(j+7))+1;
							  listOne.set(j+7, String.valueOf(af_numquant1));						  
						    }
						 if(rs1.getInt("quant")<=Integer.parseInt(listOne.get(j+16)) && rs1.getInt("quant")>=Integer.parseInt(listOne.get(j+17)))
						    {
							  be_numquant3=Integer.parseInt(listOne.get(j+18))+1;
							  listOne.set(j+18, String.valueOf(be_numquant3));						  
						    }
					 }
										 
					 else if(tmpmonth>=7 && tmpmonth<=9)
					 {
						 if(rs1.getInt("quant")<=Integer.parseInt(listOne.get(j+10)) && rs1.getInt("quant")>=Integer.parseInt(listOne.get(j+11)))
						    {
							  af_numquant2=Integer.parseInt(listOne.get(j+13))+1;
							  listOne.set(j+13, String.valueOf(af_numquant2));						  
						    }
						 if(rs1.getInt("quant")<=Integer.parseInt(listOne.get(j+22)) && rs1.getInt("quant")>=Integer.parseInt(listOne.get(j+23)))
						    {
							  be_numquant4=Integer.parseInt(listOne.get(j+24))+1;
							  listOne.set(j+24, String.valueOf(be_numquant4));						  
						    }
					 }
					 
					 else
					 {
						 if(rs1.getInt("quant")<=Integer.parseInt(listOne.get(j+16)) && rs1.getInt("quant")>=Integer.parseInt(listOne.get(j+17)))
						    {
							  af_numquant3=Integer.parseInt(listOne.get(j+19))+1;
							  listOne.set(j+19, String.valueOf(af_numquant3));						  
						    }
					 }
				  break tit1;
				  }
			    }
			 }
		//	System.out.println(listOne);
			System.out.print("\n");
			// Printing all the required elements from an ArrayList into the console as showed below:
			System.out.println("CUSTOMER       PRODUCT    QUARTER    BEFORE_TOT    AFTER_TOT");  // Column heading
			System.out.println("========       =======    =======    ==========    =========");
			for (int k=0;k<listOne.size()-1;k=k+29)  // loop to iterate through the ArraList.
				{
			  	 if (listOne.get(k+25).equals("Q1"))
					 {						 
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%5s",listOne.get(k+25))+ "    ");
						 System.out.print(String.format("%14s","<NULL>    "));              // Insert NULL for Q1 before
						 if (Integer.parseInt(listOne.get(k+7))==0) //check if the average value is 0. if yes then print NULL
						 {
							 System.out.println(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
						 System.out.println(String.format("%10s",String.valueOf(listOne.get(k+7)))+ "    ");   // Right Indent the integer output
						 }
					 }
					 
				 if (listOne.get(k+26).equals("Q2"))
					 {
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%5s",listOne.get(k+26))+ "    ");
						 if (Integer.parseInt(listOne.get(k+12))==0)  //check if the average value is 0. if yes then print NULL
						 {
							 System.out.print(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
						 System.out.print(String.format("%10s",String.valueOf(listOne.get(k+12)))+ "    ");   // Right Indent the integer output
						 }
						 if (Integer.parseInt(listOne.get(k+13))==0)   //check if the average value is 0. if yes then print NULL
						 {
							 System.out.println(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
						 System.out.println(String.format("%10s",String.valueOf(listOne.get(k+13)))+ "    ");   // Right Indent the integer output
						 }
					 }
					 
				 if (listOne.get(k+27).equals("Q3"))
					 {
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%5s",listOne.get(k+27))+ "    ");
						 if (Integer.parseInt(listOne.get(k+18))==0)   //check if the average value is 0. if yes then print NULL
						 {
							 System.out.print(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
						 System.out.print(String.format("%10s",String.valueOf(listOne.get(k+18)))+ "    ");   // Right Indent the integer output
						 }
						 if (Integer.parseInt(listOne.get(k+19))==0)   //check if the average value is 0. if yes then print NULL
						 {
							 System.out.println(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
						 System.out.println(String.format("%10s",String.valueOf(listOne.get(k+19)))+ "    ");   // Right Indent the integer output
						 }
					  }
					 
				 if (listOne.get(k+28).equals("Q4"))
					 {
						 System.out.print(String.format("%-10s",listOne.get(k))+ "     ");   // Left Indent the string output
						 System.out.print(String.format("%-8s",listOne.get(k+1))+ "    ");
						 System.out.print(String.format("%5s",listOne.get(k+28))+ "    ");
						 if (Integer.parseInt(listOne.get(k+24))==0)   //check if the average value is 0. if yes then print NULL
						 {
							 System.out.print(String.format("%14s","<NULL>    "));
						 }
						 else
						 {
							 System.out.print(String.format("%10s",String.valueOf(listOne.get(k+24)))+ "    ");   // Right Indent the integer output	 
						 }
						 System.out.println(String.format("%14s","<NULL>    "));      //Insert NULL for Q4 after
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
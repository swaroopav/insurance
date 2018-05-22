package com.ts;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class InsuranceDAO {
	public UserBean isUser(String uid,String pwd)
	{
		UserBean details=new UserBean();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance1","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select role,uname from underwriter where uid='"+uid+"' and pwd='"+pwd+"'");
			while(rs.next())
			{
				details.setRole(rs.getString(1));
			details.setUsername(rs.getString(2));
			}
			rs.close();
			con.close();
			
		
					}
		catch(Exception e) {
			System.out.println(e);
		}
		finally {
		return details;
	}

	}


public ArrayList<String> AutoModelList()
{
	ArrayList<String> models=new ArrayList<String>();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance1","root","root");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select Vehicle_Model_Name from auto_rates");
		while(rs.next())
		{
			models.add(rs.getString(1));
		}
		rs.close();
		con.close();
		
	
				}
	catch(Exception e) {
		System.out.println(e);
	}
	finally {
	return models;
}

}

public int basePremium(String model)
{
	int base=0;
	try{ 
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/insurance1","root","root");  
		//here sonoo is database name, root is username and password  
		
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select Auto_base_Premium  from auto_rates where vehicle_model_name='"+model+"'");  
		
		while(rs.next())  
		{
	base=(rs.getInt(1));
		}
		rs.close();
		con.close(); 
	}
		catch(Exception e){ System.out.println(e);} 
	finally {
		return base;
	}
	
}


public double FinalPrice(String vtype,String Einsurance,String Dclass,String ASF,String ATA,String VH,String PT)
{
	double total=1000;
	try{ 
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/insurance1","root","root");  
		
		
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select Impact_type,Impact_pct  from auto_rating_factors where (RFN='vtype' and OD='"+vtype+"') or  (RFN='Einsurance' and OD='"+Einsurance+"') or(RFN='Dclass' and OD='"+Dclass+"') or (RFN='ASF' and OD='"+ASF+"') or (RFN='ATA' and OD='"+ATA+"') or (RFN='VH' and OD='"+VH+"') or (RFN='PT' and OD='"+PT+"')"); 
		
		while(rs.next())  
		{
			double s=1000*rs.getDouble(2);
if(rs.getInt(1)==0)
{
	total-=s;
}
else
{
	total+=s;
}
		}
		rs.close();
		con.close(); 
	}
		catch(Exception e){ System.out.println(e);} 
	finally {
		return total;
	}
}

}

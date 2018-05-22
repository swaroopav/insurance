package com.ts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class First {

	public static void main(String[] args) {
		First f=new First();
		f.FinalPrice("Petrol","yes","transport", "yes", "yes","yes", "long");

	}
	
	public void FinalPrice(String vtype,String Einsurance,String Dclass,String ASF,String ATA,String VH,String PT)
	{
		float total=1000;
		try{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/insurance1","root","root");  
			
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select Impact_type,Impact_pct  from auto_rating_factors where (RFN='vtype' and OD='"+vtype+"') or  (RFN='Einsurance' and OD='"+Einsurance+"') or(RFN='Dclass' and OD='"+Dclass+"') or (RFN='ASF' and OD='"+ASF+"') or (RFN='ATA' and OD='"+ATA+"') or (RFN='VH' and OD='"+VH+"') or (RFN='PT' and OD='"+PT+"')"); 
			
			while(rs.next())  
			{
				
				float s=1000*rs.getFloat(2);
				System.out.println(s);
				if(rs.getInt(1)==0)
				{
					total-=s;
				}
				else
				{
					total+=s;
				}
			
			}
			System.out.println(total);
			rs.close();
			con.close(); 
		}
			catch(Exception e){ System.out.println(e);} 
		finally {
			
		}
	}

}



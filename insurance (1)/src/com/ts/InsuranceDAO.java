package com.ts;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsuranceDAO {
	public String isUser(String uid,String pwd)
	{
		String role="";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql:/localhost:3309/insurance1","root","root");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select role from underwriter");
			while(rs.next())
				role=rs.getString(0);
			rs.close();
			con.close();
			return role;
		
					}
		catch(Exception e) {
			System.out.println(e);
		}
		return role;
	}

}

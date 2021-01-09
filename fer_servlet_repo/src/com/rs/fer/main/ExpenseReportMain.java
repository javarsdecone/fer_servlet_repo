package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseReportMain {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/fer_jdbc", "root", "root");
			

			String inputSQL = "select * from expense where userid=? and type=? and date between ? and ?";

			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, 1);
			preparedstatement.setString(2, "ghee");
			preparedstatement.setString(3, "24-5-11");
			preparedstatement.setString(4, "24-6-11");
			
			
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				//Get all the column data
				//Print them in the console area;
				int id=resultset.getInt(1);
			    String type=resultset.getString(2);
				String date=resultset.getString(3);
				float price=resultset.getFloat(4);
				int noofitems=resultset.getInt(5);
				float total=resultset.getFloat(6);
				String bywhom=resultset.getString(7);
				int userid=resultset.getInt(8);
				
				System.out.println("id=:"+id+","+"typ=e:"+type+",");
				System.out.println("date=:"+date+","+"price=:"+price+",");
				System.out.println("noofitems=:"+noofitems+","+"total=:"+total+",");
				System.out.println("bywhom=:"+bywhom+","+"userid:"+userid+",");
			}
			

		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

}

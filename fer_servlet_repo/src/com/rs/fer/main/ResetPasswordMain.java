package com.rs.fer.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetPasswordMain {
	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/fer_jdbc", "root", "root");
			

			String inputSQL = "update user set password=? where id=? and password=?";
		
			
			
			preparedstatement = connection.prepareStatement(inputSQL);
			
			preparedstatement.setString(1, "ram21y7");
			preparedstatement.setInt(2, 1);
			preparedstatement.setString(3, "ram21");
			
			
			

			int numOfRecAffected = preparedstatement.executeUpdate();
			
			
			System.out.println("numOfRecAffected:" + numOfRecAffected);
			if(numOfRecAffected > 0) {
				System.out.println("Password reset is successful...");
			}
			else {
				System.out.println("Password reset is failed...");
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

	}}

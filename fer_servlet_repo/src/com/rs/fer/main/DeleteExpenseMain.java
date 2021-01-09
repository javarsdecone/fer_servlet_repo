package com.rs.fer.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteExpenseMain {

	public static void main(String[] args) {


				Connection connection = null;
				PreparedStatement preparedstatement = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/fer_jdbc", "root", "root");
					
					

					String inputSQL = "delete from expense where id=?";
					preparedstatement = connection.prepareStatement(inputSQL);
					
					preparedstatement.setInt(1, 2);

					int numOfRecAffected = preparedstatement.executeUpdate();
					System.out.println("numOfRecAffected:" + numOfRecAffected);
					if(numOfRecAffected > 0) {
						System.out.println("Expense deleted successfully...");
					}
					else {
						System.out.println("Expenbse delete failed...");
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



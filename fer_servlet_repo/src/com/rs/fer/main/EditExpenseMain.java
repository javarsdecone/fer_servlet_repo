package com.rs.fer.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EditExpenseMain {

	public static void main(String[] args) {


				Connection connection = null;
				PreparedStatement preparedstatement = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/fer_jdbc", "root", "root");
					
					

					String inputSQL = "update expense set type=?, date=?, price=?, noofitems=?, total=?, bywhom=? where id=?";
					preparedstatement = connection.prepareStatement(inputSQL);
					
					preparedstatement.setString(1, "ghee");
					preparedstatement.setString(2, "24-5-11");
					preparedstatement.setString(3, "25");
					preparedstatement.setString(4, "5");
					preparedstatement.setString(5, "50");
					preparedstatement.setString(6, "rakes");
					preparedstatement.setInt(7, 1);

					int numOfRecAffected = preparedstatement.executeUpdate();
					System.out.println("numOfRecAffected:" + numOfRecAffected);
					if(numOfRecAffected > 0) {
						System.out.println("Expense edited successfully...");
					}
					else {
						System.out.println("Expenbse edit failed...");
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



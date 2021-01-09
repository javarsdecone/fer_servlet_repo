package com.rs.fer.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.DBUtil;

public class FERServiceImpl implements FERService {

	@Override
	public boolean registration(User user) {
		boolean isRegister = false;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "insert into user(firstname, middlename, lastname, email, username, password, mobile) values (?, ?, ?, ?, ?, ?, ?)";

			preparedstatement = connection.prepareStatement(inputSQL);

			preparedstatement.setString(1, user.getFirstName());
			preparedstatement.setString(2, user.getMiddleName());
			preparedstatement.setString(3, user.getLastName());
			preparedstatement.setString(4, user.getEmail());
			preparedstatement.setString(5, user.getUsername());
			preparedstatement.setString(6, user.getPassword());
			preparedstatement.setString(7, user.getMobile());

			int numOfRecAffected = preparedstatement.executeUpdate();

			System.out.println("numOfRecAffected:" + numOfRecAffected);

			isRegister = numOfRecAffected > 0;

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return isRegister;
	}

	@Override
	public int login(String username, String password) {

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "select * from user where username=? and password=?";

			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setString(1, username);
			preparedstatement.setString(2, password);

			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {

				return resultset.getInt(1);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return 0;
	}

	@Override
	public boolean addExpense(Expense expense) {
		boolean isAddExpense = false;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "insert into expense(type, date, price, numberofitems, total, byWhom, userid) values (?, ?, ?, ?, ?, ?, ?)";

			preparedstatement = connection.prepareStatement(inputSQL);

			preparedstatement.setString(1, expense.getType());
			preparedstatement.setString(2, expense.getDate());
			preparedstatement.setFloat(3, expense.getPrice());
			preparedstatement.setInt(4, expense.getNumberOfItems());
			preparedstatement.setFloat(5, expense.getTotal());
			preparedstatement.setString(6, expense.getBywhom());
			preparedstatement.setInt(7, expense.getUserId());

			int numOfRecAffected = preparedstatement.executeUpdate();

			System.out.println("numOfRecAffected:" + numOfRecAffected);
			isAddExpense = numOfRecAffected > 0;

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return isAddExpense;
	}

	@Override
	public boolean editExpese(Expense expense) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean resetPassword(int userId, String currentPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Expense getExpense(int expenseId) {
		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "select * from expense where id=?";

			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, expenseId);

			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				// Get all the column data
				// Print them in the console area;
				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int noofitems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String bywhom = resultset.getString(7);
				int userid = resultset.getInt(8);

				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(noofitems);
				expense.setTotal(total);
				expense.setBywhom(bywhom);
				expense.setUserId(userid);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return expense;

	}

	@Override
	public List<Expense> getExpenses(int userId) {
		List<Expense> expenses = new ArrayList<Expense>();

		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "select * from expense where userid=?";

			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, userId);

			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				// Get all the column data
				// Print them in the console area;
				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int noofitems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String bywhom = resultset.getString(7);
				int userid = resultset.getInt(8);

				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(noofitems);
				expense.setTotal(total);
				expense.setBywhom(bywhom);
				expense.setUserId(userid);

				expenses.add(expense);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return expenses;
	}

	@Override
	public List<Expense> expenseReport(int userId, String expenseType, String fromDate, String toDate) {
		List<Expense> expenses = new ArrayList<Expense>();

		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "select * from expense where userid=? and type=? and date between ? and ?";

			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, userId);
			preparedstatement.setString(2, expenseType);
			preparedstatement.setString(3, fromDate);
			preparedstatement.setString(4, toDate);

			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				// Get all the column data
				// Print them in the console area;
				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int noofitems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				String bywhom = resultset.getString(7);
				int userid = resultset.getInt(8);

				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(noofitems);
				expense.setTotal(total);
				expense.setBywhom(bywhom);
				expense.setUserId(userid);

				expenses.add(expense);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return expenses;
	}

	@Override
	public User getUser(int userId) {
		User user = null;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();

			String inputSQL = "SELECT u.*, a.* FROM USER u LEFT JOIN address a ON u.id=a.userid WHERE u.id=?";

			preparedstatement = connection.prepareStatement(inputSQL);
			preparedstatement.setInt(1, userId);

			ResultSet resultset = preparedstatement.executeQuery();
			while (resultset.next()) {
				// Get all the column data for user (1 to 8 indexes)

				int id = resultset.getInt(1);
				String firstName = resultset.getString(2);
				String middleName = resultset.getString(3);
				String lastName = resultset.getString(4);
				String email = resultset.getString(5);
				String username = resultset.getString(6);
				String password = resultset.getString(7);
				String mobile = resultset.getString(8);

				user = new User();
				user.setId(id);
				user.setFirstName(firstName);
				user.setMiddleName(middleName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				user.setMobile(mobile);

				int addressId = resultset.getInt(9);
				String lineOne = resultset.getString(10);
				String lineTwo = resultset.getString(11);
				String city = resultset.getString(12);
				String state = resultset.getString(13);
				String postalCode = resultset.getString(14);
				String country = resultset.getString(15);
				userId = resultset.getInt(16);

				Address address = new Address();
				address.setId(addressId);
				address.setLineOne(lineOne);
				address.setLineTwo(lineTwo);
				address.setCity(city);
				address.setState(state);
				address.setPostalCode(postalCode);
				address.setCountry(country);
				address.setUserId(userId);

				user.setAddress(address);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean isUpdateUser = false;

		Connection connection = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = DBUtil.getConnection();
			connection.setAutoCommit(false);
			
			String inputSQL = "UPDATE USER SET firstname=?, middlename=?, lastname=?, email=?, mobile=? WHERE  id=?";

			preparedstatement = connection.prepareStatement(inputSQL);

			preparedstatement.setString(1, user.getFirstName());
			preparedstatement.setString(2, user.getMiddleName());
			preparedstatement.setString(3, user.getLastName());
			preparedstatement.setString(4, user.getEmail());
			preparedstatement.setString(5, user.getMobile());
			preparedstatement.setInt(6, user.getId());

			int numOfRecAffected = preparedstatement.executeUpdate();
			System.out.println("numOfRecAffected:" + numOfRecAffected);

			if (numOfRecAffected < 1) {
				System.out.println("User update is failed...");
			} else {
				Address address = user.getAddress();
				if (address.getId() == 0) {
					inputSQL = "INSERT INTO address (line1, line2, city, state, pincode, country, userid) VALUES (?, ?, ?, ?, ?, ?, ?)";

					preparedstatement = connection.prepareStatement(inputSQL);

					preparedstatement.setString(1, address.getLineOne());
					preparedstatement.setString(2, address.getLineTwo());
					preparedstatement.setString(3, address.getCity());
					preparedstatement.setString(4, address.getState());
					preparedstatement.setString(5, address.getPostalCode());
					preparedstatement.setString(6, address.getCountry());
					preparedstatement.setInt(7, user.getId());

					numOfRecAffected = preparedstatement.executeUpdate();
					System.out.println("numOfRecAffected:" + numOfRecAffected);

				} else {
					inputSQL = "UPDATE address SET line1=?, line2=?, city=?, state=?, pincode=?, country=? where id=?";

					preparedstatement = connection.prepareStatement(inputSQL);

					preparedstatement.setString(1, address.getLineOne());
					preparedstatement.setString(2, address.getLineTwo());
					preparedstatement.setString(3, address.getCity());
					preparedstatement.setString(4, address.getState());
					preparedstatement.setString(5, address.getPostalCode());
					preparedstatement.setString(6, address.getCountry());
					preparedstatement.setInt(7, address.getId());

					numOfRecAffected = preparedstatement.executeUpdate();
					System.out.println("numOfRecAffected:" + numOfRecAffected);

				}
			}

			isUpdateUser = numOfRecAffected > 0;
			if(isUpdateUser) {
				connection.commit();
			} else {
				connection.rollback();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return isUpdateUser;
	}

}

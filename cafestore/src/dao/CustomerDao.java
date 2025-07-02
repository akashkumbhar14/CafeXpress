package dao;

import java.sql.*;
import java.util.*;

import entities.Customer;
import utils.DBUtils;

public class CustomerDao implements AutoCloseable {
	private Connection connection = null;

	public CustomerDao() throws SQLException {
		connection = DBUtils.getConnection();
	}

	public void insertCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO customer(name, email, password, mobile) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getEmail());
			stmt.setString(3, customer.getPassword());
			stmt.setString(4, customer.getMobile());
			stmt.executeUpdate();
		}
	}

	public Customer selectCustomer(String email, String password) throws SQLException {
		String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Customer(
					rs.getInt("cid"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("mobile")
				);
			}
		}
		return null;
	}

	public List<Customer> displayAllCustomer() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM customer";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				customers.add(new Customer(
					rs.getInt("cid"),
					rs.getString("name"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("mobile")
				));
			}
		}
		return customers;
	}

	@Override
	public void close() throws SQLException {
		if (connection != null) connection.close();
	}
}

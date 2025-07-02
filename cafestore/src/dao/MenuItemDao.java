package dao;

import entities.MenuItem;
import utils.DBUtils;
import java.sql.*;
import java.util.*;

public class MenuItemDao implements AutoCloseable {
	private final Connection connection;

	public MenuItemDao() throws SQLException {
		connection = DBUtils.getConnection();
	}

	public List<MenuItem> displayAllItems(String category) throws SQLException {
		List<MenuItem> items = new ArrayList<>();
		String sql = "SELECT * FROM menu WHERE category = ?";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				items.add(new MenuItem(
					rs.getInt("mid"),
					rs.getString("name"),
					rs.getString("description"),
					rs.getDouble("price")
				));
			}
		}
		return items;
	}

	public void insertMenuItem(MenuItem item, String category) throws SQLException {
		String sql = "INSERT INTO menu(name, description, category, price) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setString(1, item.getName());
			stmt.setString(2, item.getDescription());
			stmt.setString(3, category);
			stmt.setDouble(4, item.getPrice());
			stmt.executeUpdate();
		}
	}

	public void updatePrice(int mid, double price) throws SQLException {
		String sql = "UPDATE menu SET price = ? WHERE mid = ?";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setDouble(1, price);
			stmt.setInt(2, mid);
			stmt.executeUpdate();
		}
	}

	@SuppressWarnings("resource")
	public boolean deleteMenuItem(int mid) throws SQLException {
		boolean canDelete = new OrderDao().isInOrder(mid);
		if (canDelete) {
			String sql = "DELETE FROM menu WHERE mid = ?";
			try (PreparedStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, mid);
				stmt.executeUpdate();
				return true;
			}
		}
		return false;
	}

	@Override
	public void close() throws SQLException {
		if (connection != null) connection.close();
	}
}

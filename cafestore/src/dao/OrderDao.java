package dao;

import entities.MenuItem;
import entities.Order;
import entities.OrderDetails;
import java.sql.*;
import java.util.*;
import utils.DBUtils;

public class OrderDao implements AutoCloseable {
	private Connection connection = null;

	public OrderDao() throws SQLException {
		connection = DBUtils.getConnection();
	}

	public void insertOrder(Order order) throws SQLException {
		String sql = "INSERT INTO orders(cid, mid) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, order.getCid());
			stmt.setInt(2, order.getMid());
			stmt.executeUpdate();
		}
	}

	public List<MenuItem> getAllOrderItems(int cid) throws SQLException {
		List<MenuItem> items = new ArrayList<>();
		String sql = "SELECT m.* FROM menu m INNER JOIN orders o ON m.mid = o.mid WHERE o.cid = ?";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, cid);
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

	public List<OrderDetails> allOrders() throws SQLException {
		List<OrderDetails> orders = new ArrayList<>();
		String sql = "SELECT o.oid, c.cid, c.name, m.name, m.price FROM customer c JOIN orders o ON o.cid = c.cid JOIN menu m ON o.mid = m.mid ORDER BY o.oid";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				orders.add(new OrderDetails(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4),
					rs.getDouble(5)
				));
			}
		}
		return orders;
	}

	public double totalProfit() throws SQLException {
		String sql = "SELECT SUM(m.price) FROM orders o JOIN menu m ON o.mid = m.mid";
		try (PreparedStatement stmt = connection.prepareCall(sql)) {
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getDouble(1);
			}
		}
		return 0;
	}

	public boolean isInOrder(int mid) throws SQLException {
		String sql = "SELECT COUNT(*) FROM orders WHERE mid = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, mid);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 0;
			}
		}
		return false;
	}

	@Override
	public void close() throws SQLException {
		if (connection != null) connection.close();
	}
}

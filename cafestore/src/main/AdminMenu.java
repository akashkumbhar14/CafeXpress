package main;

import dao.CustomerDao;
import dao.MenuItemDao;
import dao.OrderDao;
import entities.MenuItem;
import entities.OrderDetails;
import java.sql.SQLException;
import java.util.*;

public class AdminMenu {
	private static int adminMenu(Scanner sc) {
		System.out.println("------------------------------");
		System.out.println("0) Exit");
		System.out.println("1) Add New Menu Item");
		System.out.println("2) Update Item Price");
		System.out.println("3) Show All Customers");
		System.out.println("4) Show All Orders");
		System.out.println("5) Show Total Revenue");
		System.out.println("6) Delete Menu Item");
		System.out.println("------------------------------");
		System.out.print("Enter Your Choice: ");
		return sc.nextInt();
	}

	private static void addMenuItem(Scanner sc) {
		MenuItem item = new MenuItem();
		String category = null;
		System.out.println("1. Coffee | 2. Tea | 3. Snacks | 4. Cold Drinks");
		System.out.print("Choose Category: ");
		int choice = sc.nextInt();

		switch(choice) {
			case 1:
				category = "Coffee";
				break;
			case 2:
				category = "Tea";
				break;
			case 3:
				category = "Snacks";
				break;
			case 4:
				category = "Cold Drinks";
				break;
			default:
				System.out.println("Invalid Category.");
				break;
		}

		item.accept(sc);
		try (MenuItemDao dao = new MenuItemDao()) {
			dao.insertMenuItem(item, category);
			System.out.println("Menu Item Added Successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updatePrice(Scanner sc) {
		System.out.print("Enter Menu Item ID: ");
		int mid = sc.nextInt();
		System.out.print("Enter New Price: ");
		double price = sc.nextDouble();
		try (MenuItemDao dao = new MenuItemDao()) {
			dao.updatePrice(mid, price);
			System.out.println("Price Updated.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void showCustomers() {
		try (CustomerDao dao = new CustomerDao()) {
			dao.displayAllCustomer().forEach(System.out::println);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void showOrders() {
		try (OrderDao dao = new OrderDao()) {
			List<OrderDetails> orders = dao.allOrders();
			orders.forEach(System.out::println);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void showRevenue() {
		try (OrderDao dao = new OrderDao()) {
			System.out.println("Total Revenue: ₹" + dao.totalProfit());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteItem(Scanner sc) {
		System.out.print("Enter Menu Item ID to delete: ");
		int mid = sc.nextInt();
		try (MenuItemDao dao = new MenuItemDao()) {
			if (dao.deleteMenuItem(mid)) {
				System.out.println("Menu Item Deleted.");
			} else {
				System.out.println("Cannot delete. Item has been ordered before.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void adminMain(Scanner sc) {
		int choice;
		while ((choice = adminMenu(sc)) != 0) {
			switch (choice) {
				case 1:
					addMenuItem(sc);
					break;
				case 2:
					updatePrice(sc);
					break;
				case 3:
					showCustomers();
					break;
				case 4:
					showOrders();
					break;
				case 5:
					showRevenue();
					break;
				case 6:
					deleteItem(sc);
					break;
				default:
					System.out.println("Invalid choice.");
					break;
			}
		}
	}
}

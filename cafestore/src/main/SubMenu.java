package main;

import dao.MenuItemDao;
import dao.OrderDao;
import entities.Customer;
import entities.MenuItem;
import entities.Order;
import java.sql.SQLException;
import java.util.*;

public class SubMenu {
	private static int subMenu(Scanner sc) {
		System.out.println("***********************");
		System.out.println("0) Logout");
		System.out.println("1) View Cafe Menu");
		System.out.println("2) Place Order");
		System.out.println("3) View Order History");
		System.out.println("***********************");
		System.out.print("Please select an option: ");
		return sc.nextInt();
	}

	private static int cafeMenuCategoryList(Scanner sc) {
		System.out.println("_______________________");
		System.out.println("0. Exit Menu");
		System.out.println("1. Coffee");
		System.out.println("2. Tea");
		System.out.println("3. Snacks");
		System.out.println("4. Cold Drinks");
		System.out.println("_______________________");
		System.out.print("Choose a Category: ");
		return sc.nextInt();
	}

	private static void displayCafeMenu(Scanner sc) {
		int choice;
		String category = null;

		if ((choice = cafeMenuCategoryList(sc)) != 0) {
			switch (choice) {
				case 1 :
					category = "Coffee";
					break;
				case 2 :
					category = "Tea";
					break;
				case 3 : 
					category = "Snacks";
					break;
				case 4 :
					category = "Cold Drinks";
					break;
				default :
					System.out.println("Invalid Category.");
					break;
			}
		}

		List<MenuItem> itemList = new ArrayList<>();
		try (MenuItemDao dao = new MenuItemDao()) {
			itemList = dao.displayAllItems(category);
			System.out.println("----------------------------------------------------");
			itemList.forEach(System.out::println);
			System.out.println("----------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void placeOrder(Scanner sc, Customer customer) {
		Order order = new Order();
		order.setCid(customer.getCid());
		System.out.print("Enter Menu Item ID to order: ");
		order.setMid(sc.nextInt());

		try (OrderDao orderDao = new OrderDao()) {
			orderDao.insertOrder(order);
			System.out.println("Your order has been placed successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void viewOrderHistory(Customer customer) {
		try (OrderDao orderDao = new OrderDao()) {
			List<MenuItem> orders = orderDao.getAllOrderItems(customer.getCid());
			if (orders.isEmpty()) {
				System.out.println("You have no previous orders.");
			} else {
				orders.forEach(System.out::println);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void sub_menu(Customer customer, Scanner sc) {
		System.out.println("\nWelcome, " + customer.getName() + "!");
		int choice;

		while ((choice = subMenu(sc)) != 0) {
			switch (choice) {
				case 1 : 
					displayCafeMenu(sc);
					break;
				case 2 : 
					placeOrder(sc, customer);
					break;
				case 3 : 
					System.out.println(customer.getName() + "'s Order History:");
					viewOrderHistory(customer);
					break;
				default : 
					System.out.println("Invalid option.");
					break;
			}
		}
		System.out.println("You've been logged out successfully.");
	}
}

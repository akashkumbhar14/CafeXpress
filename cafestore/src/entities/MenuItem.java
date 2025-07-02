package entities;

import java.util.Scanner;

public class MenuItem {
	private int mid;
	private String name;
	private String description;
	private double price;

	public MenuItem() {}

	public MenuItem(int mid, String name, String description, double price) {
		this.mid = mid;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void accept(Scanner sc) {
		sc.nextLine();
		System.out.print("Enter the name of Menu Item: ");
		this.name = sc.nextLine();
		System.out.println("Enter the Description: ");
		this.description = sc.nextLine();
		System.out.print("Enter the Price: ");
		this.price = sc.nextDouble();
	}

	@Override
	public String toString() {
		System.out.println("ID: " + mid);
		System.out.println("Name: " + name);
		System.out.println("Price: " + price);
		System.out.println("Description: " + description);
		return "";
	}
}

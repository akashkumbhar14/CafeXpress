package entities;

import java.util.Objects;
import java.util.Scanner;

public class Customer {
	private int cid;
	private String name;
	private String email;
	private String password;
	private String mobile;

	public Customer() {}

	public Customer(int cid, String name, String email, String password, String mobile) {
		this.cid = cid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void accept(Scanner sc) {
		sc.nextLine();
		System.out.print("Enter the name: ");
		this.name = sc.nextLine();
		System.out.print("Enter the Email / User ID: ");
		this.email = sc.next();
		System.out.print("Enter the Password: ");
		this.password = sc.next();
		System.out.print("Enter the Mobile Number: ");
		this.mobile = sc.next();
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Customer other = (Customer) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		System.out.println("Customer ID: " + cid);
		System.out.println("Name: " + name);
		System.out.println("Email: " + email);
		System.out.println("Password: " + password);
		System.out.println("Mobile: " + mobile);
		return "";
	}
}

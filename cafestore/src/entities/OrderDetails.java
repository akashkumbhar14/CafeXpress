package entities;

public class OrderDetails {
	private int oid;
	private int cid;
	private String cName;
	private String mName;
	private double price;

	public OrderDetails() {}

	public OrderDetails(int oid, int cid, String cName, String mName, double price) {
		this.oid = oid;
		this.cid = cid;
		this.cName = cName;
		this.mName = mName;
		this.price = price;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		System.out.println("Order ID: " + oid);
		System.out.println("Customer ID: " + cid);
		System.out.println("Customer Name: " + cName);
		System.out.println("Order Item: " + mName);
		System.out.println("Price: " + price);
		return "";
	}
}

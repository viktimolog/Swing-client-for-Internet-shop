package edu.hometask.ClientServerShop.shop;

public class Product 
{
	private String nameProduct;
	private double price;
	private String description;
	
	public Product(String nameProduct, double price, String description)
	{
		this.nameProduct = nameProduct;
		this.price = price;
		this.description = description;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString()
	{
		return "Product: " + nameProduct + ", price=" + price + "\n"
				+ "Description: " + description;
	}
	
	public String smallShow()
	{
//		return "Product [nameProduct=" + nameProduct + ", price=" + price + "]";
		return nameProduct + ", price=" + price;
	}		
}

package edu.hometask.ClientServerShop.shop;

public class Order
{
	private Person person;
	private Product product;
	
	public Order(Person person, Product product)
	{
		this.person = person;
		this.product = product;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString()
	{
		return person.toString() + "\n"+ product.toString();
	}
	

}

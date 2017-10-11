package edu.hometask.ClientServerShop.shop;

import java.util.ArrayList;

public class InternetShop 
{
	private ArrayList<Product> products;
	
	public InternetShop()
	{
		products = new ArrayList<Product>();
	}
	
	public ArrayList<Product> getProducts()
	{
		return products;
	}

	public String menu()
	{
		String menu="";
		int i=0;
		for(Product pr : products) 
		{
			menu += i + ". " + pr.smallShow() + "\n";
			i++;
		}
		return menu;
	}
	
//	public String[] test()//return items for comboBox of client
//	{
//		String[] items;
//		products.add(new Product("TV LG M32T58",8650.75,"It is a cool TV. Buy now! It is will like you!"));
//		
//		products.add(new Product("Phone Meizu U10",2999.93,"It is not Iphone, but it is a strong phone. One phone - one button!"));
//		
//		products.add(new Product("Notebook Asus R510",6750.25,"CPU Core I5-4670, DDR3-4096Mb, HDD-500Gb. It is a very good choice for beginner programmer!"));
//		
//		items = new String[]{products.get(0).smallShow(),products.get(1).smallShow(),products.get(2).smallShow()};
//		
//		return items;
//	}
	
	public void test()
	{
		products.add(new Product("TV LG M32T58",8650.75,"It is a cool TV. Buy now! It is will like you!"));
		
		products.add(new Product("Phone Meizu U10",2999.93,"It is not Iphone, but it is a strong phone. One phone - one button!"));
		
		products.add(new Product("Notebook Asus R510",6750.25,"CPU Core I5-4670, DDR3-4096Mb, HDD-500Gb. It is a very good choice for beginner programmer!"));
	}

}


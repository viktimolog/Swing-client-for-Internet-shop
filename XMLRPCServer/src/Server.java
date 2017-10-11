import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.xmlrpc.WebServer;

import com.google.gson.Gson;

import edu.hometask.ClientServerShop.shop.InternetShop;
import edu.hometask.ClientServerShop.shop.Order;
import edu.hometask.ClientServerShop.shop.Person;
import edu.hometask.ClientServerShop.shop.Product;


public class Server
{
	private double kurs;
	
	private InternetShop iShop;
	
	private Product choiceProduct;
	
	
	public Server(double kurs, InternetShop iShop)
	{
		this.kurs = kurs;
		
		this.iShop = iShop;
		
		choiceProduct=null;
	}
	
	public String saveOrder(String person)
	{
		Person gotPerson = new Gson().fromJson(person, Person.class);
		
		Order order = new Order(gotPerson, choiceProduct);
		
        try
        {
            FileWriter writer = new FileWriter("./orders.txt", true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(order.toString()+"\r\n");
            writer.flush();
            bufferWriter.close();
        }
        catch (IOException e) 
        {
            System.out.println(e);
        }
        return "Thank you. We will phone you!";
	}
	
	public InternetShop getIShop()
	{
		return this.iShop;
	}
	
	public String getStr(String tmp)
	{
		return "Stroka";
	}
	
	public String getProductsJSON(String begin)
	{
		if(begin.equals("Begin"))
		 return new Gson().toJson(iShop.getProducts());
		return null;
	}

	public String getProduct(int i)
	{
		choiceProduct = iShop.getProducts().get(i);
		return choiceProduct.toString();
	}
	
	public static void main(String[] args)
	{
		InternetShop iShop = new InternetShop();
		
		iShop.test();
		
		WebServer ws = new WebServer(3060);
		Server s = new Server(26, iShop);
		
		ws.addHandler("InetShop", s);
		ws.start();
	}
}

import java.awt.EventQueue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import com.google.gson.Gson;

import edu.hometask.ClientServerShop.shop.Person;
import edu.hometask.ClientServerShop.shop.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClientFrame
{

	private JFrame frame;
	private JTextField txtYourName;
	private JTextField textField_phone;
	
	private Product[] products;
	
	private XmlRpcClient cl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 465, 251);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);//center screen
		
		JLabel lblChoiceProduct = new JLabel("Choice product:");
		lblChoiceProduct.setBounds(10, 11, 89, 14);
		frame.getContentPane().add(lblChoiceProduct);
		
		final JComboBox cmbProducts = new JComboBox();
		cmbProducts.setBounds(105, 9, 244, 20);
		frame.getContentPane().add(cmbProducts);
		
		final JTextArea textArea_more = new JTextArea();
		textArea_more.setBounds(10, 35, 438, 143);
		frame.getContentPane().add(textArea_more);
		textArea_more.setLineWrap(true);
		textArea_more.setWrapStyleWord(true);
		
		final JButton btn_Buy = new JButton("Buy");
		btn_Buy.setEnabled(false);
		btn_Buy.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				Person person = new Person();
				
				person.setName(txtYourName.getText());
				
				person.setPhone(textField_phone.getText());
				
				Gson gson = new Gson();
				String personToServer = gson.toJson(person);
				
				Vector<String> strVec = new Vector<>();
				strVec.add(personToServer);
				String str=null;
				try
				{
				str = (String)cl.execute("InetShop.saveOrder",strVec);
				} catch (XmlRpcException e) 
				{
					e.printStackTrace();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, str);
				System.exit(0);
			}
		});
		btn_Buy.setBounds(359, 189, 89, 23);
		frame.getContentPane().add(btn_Buy);
		
		JButton btnMore = new JButton("More");
		btnMore.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int i = cmbProducts.getSelectedIndex();
//				textArea_more.setText(products[i].toString());
				
				Vector<Integer> intVec = new Vector<>();
				intVec.add(i);
				String str=null;
				try
				{
				str = (String)cl.execute("InetShop.getProduct",intVec);
				} 
				catch (XmlRpcException e) 
				{
					e.printStackTrace();
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}			
				
				textArea_more.setText(str);
				
				txtYourName.setEnabled(true);
				textField_phone.setEnabled(true);
				btn_Buy.setEnabled(true);
				
			}
		});
		btnMore.setBounds(359, 7, 89, 23);
		frame.getContentPane().add(btnMore);
		
		txtYourName = new JTextField();
		txtYourName.setEnabled(false);
		txtYourName.setText("Your name");
		txtYourName.setBounds(10, 190, 165, 20);
		frame.getContentPane().add(txtYourName);
		txtYourName.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setEnabled(false);
		textField_phone.setText("Your phone");
		textField_phone.setColumns(10);
		textField_phone.setBounds(184, 190, 165, 20);
		frame.getContentPane().add(textField_phone);
		
		
//		XmlRpcClient cl=null;
		try
		{
			cl = new XmlRpcClient("http://localhost:3060");
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		Vector<String> strVec = new Vector<>();
		strVec.add("Begin");
		String str;
		try
		{
			str = (String)cl.execute("InetShop.getStr",strVec);
		} catch (XmlRpcException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		String productsJSON=null;
		try 
		{
			productsJSON = (String)cl.execute("InetShop.getProductsJSON",strVec);
		} 
		catch (XmlRpcException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		products = new Gson().fromJson(productsJSON, Product[].class);
		
		for(Product pr : products)
		{
			cmbProducts.addItem(pr.smallShow());			
		}
	}
}

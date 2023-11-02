package jdbc;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrudOperation 
{
	static Scanner sc=new Scanner(System.in);
	static void Insert(Connection con,Statement st) 
	{
		try
		{
		String sql="insert into product(name, category, price, brand, rating) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement pst=con.prepareStatement(sql);
		
		System.out.println("Enter no  of records:");
		int n=sc.nextInt();
		
		for(int i=1; i<=n;i++)
		{
			System.out.println("Enter the name: ");
			String name = sc.next();
			System.out.println("Enter the category: ");
			String category = sc.next();
			System.out.println("Enter the price: "); 
			int price = sc.nextInt();
			System.out.println("Enter the brand: ");
			String brand = sc.next();
			System.out.println("Enter the rating: ");
			float rating = sc.nextFloat();
			
			pst.setString(1, name);
			pst.setString(2, category);
			pst.setInt(3, price);
			pst.setString(4, brand);
			pst.setFloat(5, rating);
			
			int r=pst.executeUpdate();
			if(r>0)
			{
				System.out.println("product inserted successfully");
			}
			else
			{
				System.out.println("product not inserted successfully");
			}
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
    static void GetProductByPrice(Statement st) 
	{
    	try
    	{
		System.out.println("Enter the price: ");
		int maxprice=sc.nextInt();
		
		String sql="select * from product WHERE price <= "+maxprice;
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next())
		{
			System.out.println(rs.getInt("no"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("category"));
			System.out.println(rs.getInt("price"));
			System.out.println(rs.getString("brand"));
			System.out.println(rs.getFloat("rating"));
			System.out.println("===============");
		}
    	}
    	catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
	
	static void GetGadgetsByRating(Statement st)
	{
		try
		{
		System.out.println("Enter the rating:");
		float minrating=sc.nextFloat();
		
		String sql="select * from product WHERE rating > "+minrating;
		ResultSet rs=st.executeQuery(sql);
		
		while(rs.next())
		{
			System.out.println(rs.getInt("no"));
			System.out.println(rs.getString("name"));
			System.out.println(rs.getString("category"));
			System.out.println(rs.getInt("price"));
			System.out.println(rs.getString("brand"));
			System.out.println(rs.getFloat("rating"));
			System.out.println("===============");
		}
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
	
	static void Updata(Connection con, Statement st)
	{
		try
		{
		System.out.println("Enter new price for SmartWatch:");
		int newprice=sc.nextInt();
		
		String sql="update product set price = "+ newprice +" WHERE no = 9 ";
		int rs=st.executeUpdate(sql);
		
		if(rs > 0)
		{
			System.out.println("Smartwatch price updated successfully");
		}
		else
		{
			System.out.println("Smartwatch price not update");
		}
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
	
	static void Delete(Connection con, Statement st) 
	{
		try
		{
		System.out.println("Enter the price of product:");
		int maxprice=sc.nextInt();
		
		String sql="delete from product WHERE price < "+maxprice;
		int rs=st.executeUpdate(sql);
		
		if(rs>0)
		{
			System.out.println("product deleted sucessfully");
		}
		else
		{
			System.out.println("product not deleted");
		}
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ClassNotFoundException 
	{
		try 
		{
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  String url="jdbc:mysql://localhost:3306/database3"; 
		  String username="root";
		  String password="Chikki@14";
		  Connection con=DriverManager.getConnection(url, username, password);
		  Statement st=con.createStatement();
		  int choice;
		  do
		  {
			  System.out.println("1. Insert");
			  System.out.println("2. Get Product by Price");
			  System.out.println("3. Get Gadget by Rating");
			  System.out.println("4. Update");
			  System.out.println("5. Delete");
			  System.out.println("6. Exit");
			  System.out.println("Enter the choice:");
			  choice=sc.nextInt();
			  
			  switch(choice)
			  {
			  case 1:
			  {
				  Insert(con,st);
				  break;
			  }
			  case 2:
			  {
				  GetProductByPrice(st);
				  break;
			  }
			  case 3:
			  {
				  GetGadgetsByRating(st);
				  break;
			  }
			  case 4:
			  {
				  Updata(con,st);
				  break;
			  }
			  case 5:
			  {
				  Delete(con,st);
				  break;
			  }
			  case 6:
			  {
				  System.out.println("Exit");
				  break;
			  }
			  default:
				  System.out.println("Invalid choice");
				  break;
			  }
		  }
		  while(choice!=6);
		  st.close();
		  con.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}

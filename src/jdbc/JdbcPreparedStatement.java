package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcPreparedStatement
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		PreparedStatement pst=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try 
		{
		  Class.forName("com.mysql.cj.jdbc.Driver"); //loading the Driver
		  String url="jdbc:mysql://localhost:3306/database2"; 
		  String username="root";
		  String password="Chikki@14";
		  con=DriverManager.getConnection(url, username, password);//Creating connection
		  
		  String sql="insert into student1 (name, marks, address) values(?,?,?)";
		  pst=con.prepareStatement(sql);
		  
		  System.out.println("Enter no of records");
		  int n=sc.nextInt();
		  
		  for(int i=1;i<=n;i++)
		  {
			  System.out.println("Enter name:");
			  String name=sc.next();
			  System.out.println("Enter marks:");
			  float marks=sc.nextFloat();
			  System.out.println("Enter address:");
			  String address=sc.next();
			  
			  pst.setString(1,name);
			  pst.setFloat(2,marks);
			  pst.setString(3,address);
			  
			  int r=pst.executeUpdate();
			  
			  if(r!=0)
			  {
				  System.out.println("Record inserted successfully");
			  }
			  else
			  {
				 System.out.println("Record not inserted successfully");
			  }
			  
		  }
		  
		  st=con.createStatement();
		  String sql2="select * from student1";
		  rs=st.executeQuery(sql2);
		  
		  while(rs.next())
		  {
			   System.out.println(rs.getInt(1)); 
			   System.out.println(rs.getString(2));
			   System.out.println(rs.getFloat(3));
			   System.out.println(rs.getString(4));
			   System.out.println("============");
		  }
		  
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.toString();
		}
		finally 
		{
			try 
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
	}
	
}
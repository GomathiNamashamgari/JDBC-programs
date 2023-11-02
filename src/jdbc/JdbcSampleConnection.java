package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcSampleConnection 
{
    public static void main(String[] args) 
    {
    	Connection con=null;
    	Statement st=null;
    	ResultSet rs=null;
    	
    	try 
    	{
		  Class.forName("com.mysql.cj.jdbc.Driver"); //loading the Driver
		  String url="jdbc:mysql://localhost:3306/database2"; //Creating connection
		  String username="root";
		  String password="Chikki@14";
		  con=DriverManager.getConnection(url, username, password);
		  //System.out.println(con);
		  
		  st=con.createStatement();
		  String sql="select * from student1";
		  rs=st.executeQuery(sql);
		  
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
package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcSampleConnection1
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
          con.setAutoCommit(false); 
          
    	String sql1="insert into student1 (name, marks,address) values('abc',45.67,'usa')";
    	String sql2="insert into student1 (name, marks,address) values('pqr',67.67,'california')";	  
    	
    	PreparedStatement ps1=con.prepareStatement(sql1);
    	int r1= ps1.executeUpdate();
    	PreparedStatement ps2=con.prepareStatement(sql2);
    	int r2=ps2.executeUpdate();
    	if(r1>0 && r2>0)
    	{
    		con.commit();
    	}
    	else 
    	{
    		con.rollback();
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
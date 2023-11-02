package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcBatchProcessing 
{
	public static void main(String[] args) 
	{
		Connection con=null;
		Statement st=null;
		try 
		{
		  Class.forName("com.mysql.cj.jdbc.Driver"); //loading the Driver
		  String url="jdbc:mysql://localhost:3306/database2"; 
		  String username="root";
		  String password="Chikki@14";
		  con=DriverManager.getConnection(url, username, password);//Creating connection
		
		con.setAutoCommit(false);
		  
		String sql1="insert into student1(name, marks, address) values('aaa',67.81,'goa')";
		String sql2="update student1 set name='jack' where rollno=9";
		String sql3="delete from student1 where rollno=1";

		st=con.createStatement();
		st.addBatch(sql1);
		st.addBatch(sql2);
		st.addBatch(sql3);
		st.executeBatch();
		con.commit();
       System.out.println("Batch is executed");
		  
		  
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
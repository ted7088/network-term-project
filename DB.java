package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
	/*
	 * class to connect jdbc
	 */
   public static Connection getConnection()
   {
	   /*
	    * make a connection with jdbc
	    * */
      Connection con = null;
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/network?serverTimezone=UTC";
         String user = "root";
         String pw = "12345";
         
         con = DriverManager.getConnection(url,user,pw);
      }catch(ClassNotFoundException e) {
         e.printStackTrace();
      }catch(SQLException e) {
         e.printStackTrace();
      }
      return con;
   }
   /*
    * close methods for Connection, Statement, ResultSet objects
    * */
   public static void close(Connection con) {
      try {
         if(con != null && !con.isClosed()) con.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   public static void close(Statement stmt) {
      try {
         if(stmt != null && !stmt.isClosed()) stmt.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   public static void close(ResultSet rs) {
      try {
         if(rs != null && !rs.isClosed()) rs.close();
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
}

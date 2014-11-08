package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class SqlConn {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/hungry?allowMultiQueries =true";
	//static final String DB_URL = "jdbc:mysql://localhost:3306/hungry";
	//  Database credentials
	   static final String USER = "root";
	   static final String PASS = "";
	   Connection conn = null;
	   Statement stmt = null;
	   String sql;
	   
	   //public static void main(String[] args) {
	   public SqlConn(){
	   
	   System.out.println("in mysql code");
	   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
//		      sql = "SELECT id, first, last, age FROM Employees";
//		      ResultSet rs = stmt.executeQuery(sql);
//
//		      //STEP 5: Extract data from result set
//		      while(rs.next()){
//		         //Retrieve by column name
//		         int id  = rs.getInt("id");
//		         int age = rs.getInt("age");
//		         String first = rs.getString("first");
//		         String last = rs.getString("last");
//
//		         //Display values
//		         System.out.print("ID: " + id);
//		         System.out.print(", Age: " + age);
//		         System.out.print(", First: " + first);
//		         System.out.println(", Last: " + last);
//		      }
		      //STEP 6: Clean-up environment
//		      rs.close();
		     
		      
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{

		   }
		   System.out.println("Goodbye!");
		}
	   
	  
	   public void updateEvents(String description) throws SQLException{
		   sql = "UPDATE `test`.`projects` SET `description` = '" + description +"' WHERE `name` = 'cs378';";
		   stmt = conn.createStatement();
		   System.out.println("updating project for" + description);  
		   try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      try {
				conn.close(); 
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   
	  
	   
	   public HashMap<Integer, ArrayList<String>> getEvents() throws SQLException{ 
		   HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
		   
		   sql = "SELECT id, description, place, time, type FROM compsci";
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	 int id = rs.getInt("id");
		         String description  = rs.getString("description");
		         String place = rs.getString("place");
		         String time = rs.getString("time");
		         String type = rs.getString("type");
		         
		         ArrayList<String> arr = new ArrayList<String>();
		         
		         arr.add(description);
		         arr.add(place);
		         arr.add(time);
		         arr.add(type);
		         //arr.add(project);
 		         map.put(map.keySet().size(), arr);
		         
		         //Display values
//		         System.out.print("name: " + name);
//		         System.out.print(", link: " + link);
//		         System.out.print(", year: " + year);
//		         System.out.println(", project: " + project);
		         
		      }
		   System.out.println("getting meetings");  
		   try {
				conn.close(); 
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   return map;
	   }
	   
	   public void addEvent(Map <Integer, ArrayList<String>> map) throws SQLException{
		   Set<Integer> set = map.keySet();
		   conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   stmt = conn.createStatement();
		   for (int i : set){
			   ArrayList<String> arr = map.get(i);
			   sql = "INSERT INTO `hungry`.`compsci` ( `description`, `place`, `time`) VALUES ('" + arr.get(0) +"', '" + arr.get(1) + "', '" + arr.get(2) + "');";
		   
		   
		   
		      try {
				stmt.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		   }
		   try {
				conn.close(); 
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
	   }
}


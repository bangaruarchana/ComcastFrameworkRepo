package generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
       Connection con;

	public void getDBConnection(String url,String username, String password) throws SQLException {
		try{Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		con=DriverManager.getConnection(url,username,password);
		}catch (Exception e) {
			
		}
	}
	public void getDBConnection() throws SQLException {
		try{Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		con=DriverManager.getConnection("jdbc:mysql://localhost:3036/db_name","root","root");
		}catch (Exception e) {
			
		}
	}
	
		public void DbCloseConnection() throws Exception{
			try{
				con.close();
			}catch (Exception e) {
				
			}
		}
		
		public ResultSet executeNonselectQuery(String query) throws Exception {
			ResultSet result=null;
			try {
				Statement st = con.createStatement();
			result=	st.executeQuery(query);
			}catch (Exception e) {
				
			}
			return result;
		}
	public int executeselectQuery(String query) {
		int result=0;
		try {
			Statement st = con.createStatement();
			result=st.executeUpdate(query);
			
		}catch (Exception e) {
			
		}
		return result;
	}
		
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	
	public ConnectionDB() {
		// TODO Auto-generated constructor stub
		
		
	}
	public Statement connexion(){
		Statement st=null;
		try {
			Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost/parkingdb","root","");
			st=cnx.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
}

package ATM_CASESTUDY;
import java.sql.*;
import javax.swing.*;

public class DataConnection {
	
	// Connection to the database
	public static Connection dbConnector() {
		Connection dataConn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			dataConn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kishor\\eclipse-workspace\\MyProject\\src\\ATM_CASESTUDY\\atmdata.db");
			return dataConn;
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
